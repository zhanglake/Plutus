package com.plutus.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/5/16.
 */
@Controller
@RequestMapping("/main")
public class MainController {

    @RequestMapping("")
    public String toMain() {
        return "main";
    }

}
