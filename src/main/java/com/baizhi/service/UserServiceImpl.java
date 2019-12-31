package com.baizhi.service;

import com.baizhi.dao.UserDao;
import com.baizhi.entity.User;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Discription
 * @Acthor
 * @Date 2019/12/22  10:15
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public Map<String, Object> showAllUser(Integer page, Integer rows) {
        User user = new User();
        //offset:起始的下标
        //limit:每页展示条数
        RowBounds row = new RowBounds((page - 1) * rows, rows);
        List<User> users = userDao.selectByRowBounds(user, row);
        if (users.size() == 0 && page > 1) {
            page--;
            row = new RowBounds((page - 1) * rows, rows);
            users = userDao.selectByRowBounds(user, row);
        }
        Map<String, Object> maps = new HashMap<>();
        maps.put("rows", users);
        //添加将要展示的页码
        maps.put("page", page);
        //添加总页数
        Integer records = userDao.selectCount(user);
        Integer total = records % rows == 0 ? records / rows : records / rows + 1;
        maps.put("total", total);
        //添加总条数
        maps.put("records", records);
        return maps;
    }

    @Override
    public void editUser(User user) {
        int i = userDao.updateByPrimaryKeySelective(user);
        if (i == 0) throw new RuntimeException("修改失败");
    }

    @Override
    public List<User> showAllUser() {
        List<User> users = userDao.selectAll();
        return users;
    }

    @Override
    public List<Integer> getMan() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(userDao.getMan1());
        list.add(userDao.getMan2());
        list.add(userDao.getMan3());
        return list;
    }

    @Override
    public List<Integer> getWoman() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(userDao.getWoman1());
        list.add(userDao.getWoman2());
        list.add(userDao.getWoman3());
        return list;
    }
}
