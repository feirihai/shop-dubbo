package com.shop.dubbo.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.alibaba.dubbo.rpc.RpcContext;
import com.shop.dubbo.service.UserConsumerService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller

@RequestMapping(value = "router")
public class RouterController {


    private String userPort = "8602";


    @Reference
    private UserConsumerService userConsumerService;


    @RequestMapping(value = "user", method = RequestMethod.GET)
    public String user(String path) {
        // 远程调用
        userConsumerService.info();
        return getRequest(userPort,path);
    }




    /**
     * 获取请求地址
     *
     * @param serverPort 服务器端口
     * @param path       访问路径
     * @return
     */
    private String getRequest(String serverPort, String path) {
        // 本端是否为消费端，这里会返回true
        boolean isConsumerSide = RpcContext.getContext().isConsumerSide();
        if (isConsumerSide) {
            // 获取最后一次调用的提供方 IP 地址
            String serverIP = RpcContext.getContext().getRemoteHost();
            return String.format("redirect:http://%s:%s%s", serverIP, serverPort, path);
        }

        return null;
    }
}