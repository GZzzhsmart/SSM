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

    @RequestMapping("update")
    @ResponseBody
    public ControllerStatusVO update(User user,HttpSession session) {
        ControllerStatusVO statusVO = null;
        try{
            userService.update(user);
//            session.setAttribute(Constants.USER_IN_SESSION,user);
        }catch (RuntimeException e){
            statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_UPDATE_FAIL);
        }
        statusVO = ControllerStatusVO.status(ControllerStatusEnum.CASH_UPDATE_SUCCESS);
        return statusVO;
    }

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

    //页面还没有实现修改密码的功能，只实现了用户的crud(增删改查)
    @RequestMapping(value = "updatePassword",method = RequestMethod.POST)
    @ResponseBody
    public ControllerStatusVO updatePassword(@Param("pwd")String pwd, @Param("newPwd")String newPwd, @Param("conPwd")String conPwd, @Param("user") User user,HttpSession session){
//        ControllerStatusVO statusVO = null;
//        User user = (User)session.getAttribute(Constants.USER_IN_SESSION);
        if (user.getPwd().equals(EncryptUtils.md5(pwd)) && newPwd != null && conPwd != null && newPwd.equals(conPwd)) {
            System.out.println(user.getPwd());
            System.out.println(EncryptUtils.md5(pwd));
            System.out.println(newPwd+""+conPwd);
            user.setPwd(EncryptUtils.md5(newPwd));
            userService.updatePwd(user);
//            session.removeAttribute(Constants.USER_IN_SESSION);
            return ControllerStatusVO.status(ControllerStatusEnum.CASH_PASSWORD_SUCCESS);
        } else {
            return ControllerStatusVO.status(ControllerStatusEnum.CASH_PASSWORD_FAIL);
        }

    }

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
