package com.baizhi.dao;

import com.baizhi.entity.User;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface UserDao extends Mapper<User> {
    public Integer getMan1();

    public Integer getWoman1();

    public Integer getMan2();

    public Integer getWoman2();

    public Integer getMan3();

    public Integer getWoman3();
}
