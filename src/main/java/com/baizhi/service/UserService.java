package com.baizhi.service;

import com.baizhi.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    //展示所有
    public Map<String, Object> showAllUser(Integer page, Integer rows);

    //修改状态
    public void editUser(User user);

    //获取所有用户
    public List<User> showAllUser();

    //获取最近三周男用户
    List<Integer> getMan();

    //获取最近三周女用户
    List<Integer> getWoman();
}
