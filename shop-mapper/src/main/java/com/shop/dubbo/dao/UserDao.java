package com.shop.dubbo.dao;

import com.shop.dubbo.vo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserDao {
    public void insertUser(User user);
    public User selectUserByName(String username);
}
