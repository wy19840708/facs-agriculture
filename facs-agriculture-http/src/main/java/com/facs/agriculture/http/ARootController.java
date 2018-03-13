package com.facs.agriculture.http;

import com.facs.agriculture.iservice.ILoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;

/**
 * @Description 登录，退出，首页
 * @Author luke
 * @Date 2018-01-30
 */
@Controller
@RequestMapping("/")
public class ARootController {

    @GetMapping
    public String index(ModelAndView modelAndView){
        return "login";
    }

    @Autowired
    private ILoginService loginService;

    @PostMapping("login")
    public String login(String code,String password,HttpSession session){
        if(!loginService.login(code,password))
            return "login";
        session.setAttribute("user",code);
        return "redirect:/index";
    }

    @RequestMapping("index")
    public String index(){
        return "index";
    }

    @RequestMapping("logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "login";
    }
    @RequestMapping("p404")
    public String p404(){
        return "404";
    }
}
