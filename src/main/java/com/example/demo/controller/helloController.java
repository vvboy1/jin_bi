package com.example.demo.controller;

import com.example.demo.common.ServerResponse;
import com.google.gson.Gson;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class helloController {
    @RequestMapping(value = "/")
    public ServerResponse<String> hello() {
        Gson gson = new Gson();
        User i = new User();
        i.setA(4);
        String a =  gson.toJson(i);
        System.out.println(a);

        User u = gson.fromJson(a,User.class);
        System.out.println(u.getA());
        return ServerResponse.createBySuccess();
    }
    @RequestMapping(value = "/hello")
    public ServerResponse<String> hello2() {
        Gson gson = new Gson();
        User i = new User();
        i.setA(4);
        String a =  gson.toJson(i);
        System.out.println(a);

        User u = gson.fromJson(a,User.class);
        System.out.println(u.getA());
        return ServerResponse.createBySuccess();
    }
}
