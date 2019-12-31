package com.baizhi.service;

import com.baizhi.dao.AdminDao;
import com.baizhi.entity.Admin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName AdminServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/17  16:25
 */

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    AdminDao adminDao;

    @Override
    public void adminLogin(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(password);
        List<Admin> admins = adminDao.select(admin);
        if (admins.size() == 0) {
            throw new RuntimeException("用户名或密码错误");
        }
    }
}
