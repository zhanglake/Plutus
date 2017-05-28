package com.plutus.controller;

import com.plutus.dao.UserDao;
import com.plutus.dto.UserDto;
import com.plutus.entity.User;
import com.plutus.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by zhanglake on 2017/5/9.
 * 用户登录
 */
@Controller
public class LoginController {

    private static Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String toLogin() {
        return "login";
    }

    @RequestMapping("/tologin")
    public String toIndex(UserDto userDto, Model model) {
        String name = userDto.getUserName();
        String password = userDto.getPassword();
        User user = userService.findByUserNameAndPassword(name, password);
        if (null == user) {
            logger.info("Login: user {} login fail !", name);
            return "login_failed";
        } else {
            model.addAttribute("user", user);
            logger.info("Login: user {} login success !", user.getUserName());
        }
        return "order";
    }

}
