package top.zzh.controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import top.zzh.bean.LoginLog;
import top.zzh.bean.User;
import top.zzh.common.Constants;
import top.zzh.common.ControllerResult;
import top.zzh.common.EncryptUtils;
import top.zzh.common.Pager;
import top.zzh.enums.ControllerStatusEnum;
import top.zzh.query.CashQuery;
import top.zzh.service.LoginLogService;
import top.zzh.service.UserService;
import top.zzh.vo.ControllerStatusVO;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private LoginLogService loginLogService;
    
    /*
    *简单的登录权限验证+登录日志
    */
    @PostMapping("login")
    @ResponseBody
    public ControllerStatusVO login(HttpServletRequest request,HttpSession session, String phone, String pwd, String code) {
        Object obj = session.getAttribute(Constants.CODE_IN_SESSION);
        ControllerStatusVO statusVO = null;
        if (obj != null) {
            String checkCode = (String) obj;
            if (checkCode.equalsIgnoreCase(code)) {
                Object userObj = session.getAttribute(Constants.USER_IN_SESSION);
                if (userObj == null) {
                    User user = userService.getByPhonePwd(phone, EncryptUtils.md5(pwd));
                    if (user != null) {
                        session.setAttribute(Constants.USER_IN_SESSION, user);
                        LoginLog log = new LoginLog();
                        log.setUserId(user.getId());
                        log.setLoginIp(request.getRemoteHost());
                        loginLogService.save(log);
                        statusVO = ControllerStatusVO.status(ControllerStatusEnum.USER_LOGIN_SUCCESS);
                    } else {
                        statusVO = ControllerStatusVO.status(ControllerStatusEnum.USER_LOGIN_FAIL);
                    }
                } else {
                    statusVO = ControllerStatusVO.status(ControllerStatusEnum.USER_ALREADY_LOGIN);
                }
            } else {
                statusVO = ControllerStatusVO.status(ControllerStatusEnum.USER_LOGIN_ERROR_CODE);
            }
        }
        return statusVO;
    }
    
    //用户的注册
    @RequestMapping("save")
    @ResponseBody
    public ControllerStatusVO save(User user,HttpSession session) {
        ControllerStatusVO statusVO = null;
        try{
            session.getAttribute(Constants.USER_IN_SESSION);
            user.setPwd(EncryptUtils.md5(user.getPwd()));
            user.setRegTime(new Timestamp(Calendar.getInstance().getTime().getTime()));
            userService.save(user);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_SAVE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_SAVE_SUCCESS);
        return statusVO;
    }

    //用户的修改
    @RequestMapping("update")
    @ResponseBody
    public ControllerStatusVO update(User user,HttpSession session) {
        ControllerStatusVO statusVO = null;
        try{
            userService.update(user);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_UPDATE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_UPDATE_SUCCESS);
        return statusVO;
    }

    //用户的删除
    @RequestMapping("delete/{id}")
    @ResponseBody
    public ControllerStatusVO delete(@PathVariable("id") Long id){
        ControllerStatusVO statusVO = null;
        try{
            userService.removeById(id);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_DELETE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_DELETE_SUCCESS);
        return statusVO;
    }

    
    //检查用户密码
//    @RequestMapping("checkPwd")
//    @ResponseBody
//    public ControllerStatusVO checkPwd(String oldpwd,HttpSession session){
//        ControllerStatusVO statusVO = null;
//        User user = (User)session.getAttribute(Constants.USER_IN_SESSION);
//        String pwd = userService.checkPwd(user.getPhone());
//        if(!pwd.equals(EncryptUtils.md5(oldpwd))){
//            statusVO =  ControllerStatusVO.status(ControllerStatusEnum.CHECK_PASSWORD_FAIL);
//        }else {
//            statusVO =  ControllerStatusVO.status(ControllerStatusEnum.CHECK_PASSWORD_SUCCESS);
//        }
//        return statusVO;
//    }
    
   //修改用户密码
    @RequestMapping("updatePwd")
    @ResponseBody
    public ControllerStatusVO updatePwd(String oldpwd,String newPwd,HttpSession session){
        ControllerStatusVO statusVO = null;
        try{
            User user = (User)session.getAttribute(Constants.USER_IN_SESSION);
            String pwd = userService.checkPwd(user.getPhone());
            if(pwd.equals(EncryptUtils.md5(oldpwd))){
                userService.updatePwd(user.getId(),EncryptUtils.md5(newPwd));
                statusVO =  ControllerStatusVO.status(ControllerStatusEnum.CHECK_PASSWORD_SUCCESS);
            }else{
                statusVO =  ControllerStatusVO.status(ControllerStatusEnum.CHECK_PASSWORD_FAIL);
            }
        }catch (RuntimeException e){
            statusVO =  ControllerStatusVO.status(ControllerStatusEnum.CASH_PASSWORD_FAIL);
        }
        statusVO =  ControllerStatusVO.status(ControllerStatusEnum.CASH_PASSWORD_SUCCESS);
        return statusVO;
    }

    //分页
    @RequestMapping("pager_criteria")
    @ResponseBody
    public Pager pagerCriteria(int page, int rows, User user) {
        return userService.listPagerCriteria(page, rows, user);
    }

    @RequestMapping("home")
    public String home() {
        return "home";
    }

    @RequestMapping("page")
    public String page() {
        return "user/user";
    }

    //退出+登录日志（退出时间的添加）
    @RequestMapping("logout")
    public String logout(HttpSession session) {
        User user = (User) session.getAttribute(Constants.USER_IN_SESSION);
        loginLogService.updateByUserId(user.getId());
        session.invalidate();
        return "user/login";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }

}
