package com.dalaoyang.controller;

import com.dalaoyang.annotation.Log;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
* Description  主页
* @Author junwei
* @Date 15:57 2019/12/27
**/
@RestController
public class IndexController {

    @PostMapping("/index")
    @Log(param1= "参数1" ,param2 = "参数2")
    public String index(@RequestBody Map<String, Object> obj){
        System.out.println(obj);
        return "hello dalaoyang";
    }

    @GetMapping("/index2")
    public String index2(){
        System.out.println("方法2执行");
        return "hello dalaoyang";
    }
}
