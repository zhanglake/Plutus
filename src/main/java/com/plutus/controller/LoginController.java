package com.plutus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhanglake on 2017/5/9.
 * 用户登录
 */
@Controller
public class LoginController {

    @RequestMapping("/login")
    public String toLogin(HttpServletRequest request) {
        return "login";
    }
}
