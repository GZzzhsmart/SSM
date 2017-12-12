package top.zzh.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zzh.bean.PayType;
import top.zzh.common.Combobox;
import top.zzh.service.PayTypeService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/pay_type")
public class PayTypeController {

    @Autowired
    private PayTypeService payTypeService;

    @RequestMapping("all")
    @ResponseBody
    public List<Combobox> all() {
        List<Object> objectList = payTypeService.listAll();
        List<Combobox> comboboxes = new ArrayList<>();
        for (Object obj : objectList) {
            PayType payType = (PayType) obj;
            comboboxes.add(new Combobox(payType.getId() + "", payType.getType(), false));
        }
        return comboboxes;
    }

}
