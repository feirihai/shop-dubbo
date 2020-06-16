
package com.shop.dubbo.app.api.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.fastjson.JSONObject;
import com.shop.dubbo.service.UserService;
import com.shop.dubbo.vo.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.UUID;


@Controller
public class UserController {

    @Reference
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);
    @RequestMapping("/register")
    public String load(@RequestParam("username") String username,
                       @RequestParam("password")String password,
                       @RequestParam("sex") String sex,
                       @RequestParam("phone") String phone,
                       @RequestParam("card")String card,
                       @RequestParam("file") MultipartFile file,
                       HttpSession session,
                       HttpServletResponse response) {
        User user = new User();
        user.setPassword(password);
        user.setUsername(username);
        user.setPhone(phone);
        user.setId_card(card);
        user.setSex(sex);
        if (file.getSize() > 0 && file != null) {
            String originalFilename = file.getOriginalFilename();
            String dirPath = session.getServletContext().getRealPath("/") + "resource/static/head";
            // String dirPath="C:\\Users\\17149\\IdeaProjects\\ssm_app\\src\\main\\webapp\\resource\\image\\";
            File file1 = new File(dirPath);
            if (!file1.exists()) {
                file1.mkdirs();
            }
            String newFilename = UUID.randomUUID() + originalFilename.substring(originalFilename.lastIndexOf("."));
            user.setFile(newFilename);
            userService.addUser(user);
        }
        return "user/main";
    }

/**
     * 用户登录服务
     * @return
     */

    @RequestMapping("/load")
    public String load(@RequestParam("username")String username,
                       @RequestParam("password")String password){
        JSONObject result = userService.userLoad(username,password);
        boolean flag = (boolean)result.get("results");
        if (flag){
            return "user/main";
        }
        return "err/loadEorr";
    }
    @RequestMapping("/getLoad")
    public String getLoadUrl(){
        return "lacs";
    }

    @RequestMapping("/getRegister")
    public String getRegister(){
        return "register";
    }

}

