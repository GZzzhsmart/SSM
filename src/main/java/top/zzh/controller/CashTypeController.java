package top.zzh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zzh.common.Combobox;
import top.zzh.service.CashTypeService;
import top.zzh.vo.CashTypeVO;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/cash_type")
public class CashTypeController {

    @Autowired
    private CashTypeService cashTypeService;

    //列出所有支付方式到下拉选项框中
    @RequestMapping("get/{pid}")
    @ResponseBody
    public List<Combobox> getByPid(@PathVariable("pid") Long pid) {
        List<CashTypeVO> cashTypeVOList = cashTypeService.getByPid(pid);
        List<Combobox> comboboxes = new ArrayList<>();
        for (CashTypeVO cashTypeVO : cashTypeVOList) {
            comboboxes.add(new Combobox(cashTypeVO.getId() + "", cashTypeVO.getType(), false));
        }
        return comboboxes;
    }

}
