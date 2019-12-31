package com.baizhi.controller;

import com.baizhi.entity.Admin;
import com.baizhi.service.AdminService;
import com.baizhi.util.SecurityCode;
import com.baizhi.util.SecurityImage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.io.OutputStream;

/**
 * @ClassName AdminController
 * @Discription
 * @Acthor
 * @Date 2019/12/17  16:39
 */

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/adminLogin")
    @ResponseBody
    public String adminLogin(HttpServletRequest request, @RequestBody Admin admin) {
        adminService.adminLogin(admin.getUsername(), admin.getPassword());
        request.getSession().setAttribute("admin", admin);
        return "Ok";
    }

    @RequestMapping("/adminTuiChu")
    @ResponseBody
    public String adminTuiChu(HttpServletRequest request) {
        request.getSession().removeAttribute("admin");
        return "/login/login.jsp";
    }

    @RequestMapping("/getCode")
    public String getCode1(HttpServletRequest request, HttpServletResponse response) {
        String code = SecurityCode.getSecurityCode();
        HttpSession session = request.getSession();
        session.setAttribute("code", code);
        BufferedImage img = SecurityImage.createImage(code);
        OutputStream os = null;
        try {
            os = response.getOutputStream();
            ImageIO.write(img, "png", os);
        } catch (Exception e) {
        }
        return null;
    }

    @RequestMapping("/checkCode")
    @ResponseBody
    public String checkCode(HttpServletRequest request, @RequestBody String code) {
        String c = (String) request.getSession().getAttribute("code");
        if (!c.equals(code)) {
            throw new RuntimeException("验证码输入错误");
        }
        return "登陆成功";
    }
}
