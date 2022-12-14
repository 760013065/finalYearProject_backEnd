package org.javaboy.vhr.controller;


import org.javaboy.vhr.model.RespBean;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@RestController
public class LoginController {
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("You haven't logged in yet");
    }


}
