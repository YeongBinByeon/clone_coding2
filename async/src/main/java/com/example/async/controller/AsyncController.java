package com.example.async.controller;

import com.example.async.service.AsyncService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AsyncController {

    private final AsyncService asyncService;

    @GetMapping("/1")
    public String asyncCall_1(){
        asyncService.asyncCall_1();
        System.out.println("finish");
        return "success";
    }

    @GetMapping("/2")
    public String asyncCall_2(){
        asyncService.asyncCall_2();
        System.out.println("finish");
        return "success";
    }

    @GetMapping("/3")
    public String asyncCall_3(){
        asyncService.asyncCall_3();
        System.out.println("finish");
        return "success";
    }

}