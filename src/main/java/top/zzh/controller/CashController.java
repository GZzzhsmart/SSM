package top.zzh.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import top.zzh.bean.Cash;
import top.zzh.bean.User;
import top.zzh.common.Constants;
import top.zzh.common.Pager;
import top.zzh.enums.ControllerStatusEnum;
import top.zzh.query.CashQuery;
import top.zzh.service.CashService;
import top.zzh.vo.ControllerStatusVO;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/cash")
public class CashController {

    @Autowired
    private CashService cashService;

    @RequestMapping("page")
    public String page() {
        return "cash/cash";
    }

    //新增
    @RequestMapping("save")
    @ResponseBody
    public ControllerStatusVO save(HttpSession session, Cash cash) {
        ControllerStatusVO statusVO = null;
        try {
            User user = (User) session.getAttribute(Constants.USER_IN_SESSION);
            cash.setUserId(user.getId());
            cash.setCreateTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            cashService.save(cash);
        } catch (RuntimeException e) {
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_SAVE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_SAVE_SUCCESS);
        return statusVO;
    }

    //修改
    @RequestMapping("update")
    @ResponseBody
    public ControllerStatusVO update(Cash cash){
        ControllerStatusVO statusVO = null;
        try{
            cashService.update(cash);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_UPDATE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_UPDATE_SUCCESS);
        return statusVO;
    }

    //删除
    @RequestMapping("delete/{id}")
    @ResponseBody
    public ControllerStatusVO delete(@PathVariable("id") Long id){
        ControllerStatusVO statusVO = null;
        try{
            cashService.removeById(id);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_DELETE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_DELETE_SUCCESS);
        return statusVO;
    }

    //多条件查询+分页
    @RequestMapping("pager_criteria")
    @ResponseBody
    public Pager pagerCriteria(int page, int rows, CashQuery cashQuery) {
        return cashService.listPagerCriteria(page, rows, cashQuery);
    }

    //POI导出Excel表格
    @RequestMapping("export")
    public void exportExcel(HttpServletResponse response, CashQuery cashQuery) {
        Workbook workbook = cashService.export(cashQuery);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment; filename=cash.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //时间控制器
    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
}
