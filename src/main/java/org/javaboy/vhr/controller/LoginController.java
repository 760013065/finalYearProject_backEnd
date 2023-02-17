package org.javaboy.vhr.controller;


import org.javaboy.vhr.config.VerificationCode;
import org.javaboy.vhr.mapper.EmpLoginMapper;
import org.javaboy.vhr.model.EmpLogin;
import org.javaboy.vhr.model.RespBean;
import org.javaboy.vhr.utils.MD5;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.IOException;


@RestController
public class LoginController {
    @Resource
    EmpLoginMapper empLoginMapper;
    @GetMapping("/login")
    public RespBean login() {
        return RespBean.error("You haven't logged in yet");
    }

    @GetMapping("/verifyCode")
    public void verifyCode(HttpServletRequest request, HttpServletResponse resp) throws IOException {
        VerificationCode code = new VerificationCode();
        BufferedImage image = code.getImage();
        String text = code.getText();
        HttpSession session = request.getSession(true);
        session.setAttribute("verify_code", text);
        VerificationCode.output(image,resp.getOutputStream());
    }
    @GetMapping("/empLogin")
    EmpLogin getEmpLogin(String username,String password){
        EmpLogin empLogin = empLoginMapper.getEmpLogin(username);
        String s = MD5.md5(password + empLogin.getSalt());
        if(s.equals(empLogin.getPassword())){
            return empLogin;
        }else {
            return null;
        }
    }


}
