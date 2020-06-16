package com.shop.dubbo.service;

import com.alibaba.fastjson.JSONObject;
import com.shop.dubbo.vo.User;

public interface UserService {
    public void addUser(User user);
    public JSONObject userLoad(String username, String password);
}

