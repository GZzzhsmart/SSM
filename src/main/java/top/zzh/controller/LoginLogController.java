package top.zzh.controller;

import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import top.zzh.common.Pager;
import top.zzh.enums.ControllerStatusEnum;
import top.zzh.query.LoginLogQuery;
import top.zzh.service.LoginLogService;
import top.zzh.vo.ControllerStatusVO;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;

@Controller
@RequestMapping("/login_log")
public class LoginLogController {

    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping("page")
    public String page() {
        return "log/login_log";
    }

    //多条件查询支持分页
    @RequestMapping("pager_criteria")
    @ResponseBody
    public Pager pagerCriteria(int page, int rows, LoginLogQuery loginLogQuery) {
        return loginLogService.listPagerCriteria(page, rows, loginLogQuery);
    }

    //删除数据
    @RequestMapping("delete/{id}")
    @ResponseBody
    public ControllerStatusVO delete(@PathVariable("id") Long id){
        ControllerStatusVO statusVO = null;
        try{
            loginLogService.removeById(id);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_DELETE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_DELETE_SUCCESS);
        return statusVO;
    }

    //导出Excel表格。如果退出时间为空，导出会报错，这个问题有待解决
    @RequestMapping("export")
    public void exportExcel(HttpServletResponse response, LoginLogQuery loginLogQuery) {
        Workbook workbook = loginLogService.export(loginLogQuery);
        response.setCharacterEncoding("utf-8");
        response.setHeader("Content-disposition", "attachment; filename=log.xlsx");
        response.setContentType("application/vnd.ms-excel;charset=utf-8");
        try {
            OutputStream out = response.getOutputStream();
            workbook.write(out);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
