package top.zzh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/")
public class IndexController {

    //添加首页控制器
    @RequestMapping("")
    public String index() {
        return "user/login";
    }

}
