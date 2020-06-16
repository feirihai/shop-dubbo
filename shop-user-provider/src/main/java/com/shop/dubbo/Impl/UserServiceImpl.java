package com.shop.dubbo.Impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.alibaba.fastjson.JSONObject;

import com.shop.dubbo.dao.UserDao;
import com.shop.dubbo.service.UserService;

import com.shop.dubbo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

@Service
public class UserServiceImpl implements UserService {
    private Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserDao userDao;
    @Override
    public void addUser(User user) {
        System.out.println("UserServiceImpl..2......");
        userDao.insertUser(user);
    }

    @Override
    public JSONObject userLoad(String username, String password) {
        //用于返回信息
        JSONObject result = new JSONObject();
        result.put("results",false);
        //通过username，找到用户信息
        User user = null;
        if(!StringUtils.isEmpty(username)){
            user = userDao.selectUserByName(username);
        }
        logger.debug(user.toString());
        //比对密码
        if ((!StringUtils.isEmpty(user))&&(!StringUtils.isEmpty(password))&&password.equals(user.getPassword())){
            result.put("results",true);
        }
        return result;
    }

}
