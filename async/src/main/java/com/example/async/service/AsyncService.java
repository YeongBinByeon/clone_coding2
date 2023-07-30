package com.example.async.service;

import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AsyncService {

    private final EmailService emailService;

    /*
    [asyncCall_1] :: http-nio-8080-exec-1
    finish
    [messagingTaskExecutor] :: messagingTaskExecutor-1
    [sendMail] :: defaultTaskExecutor-1
     */
    /*
    @Async 메서드가 포함되어 있는 클래스는 비동기 기능을 지원할 수 있도록 스프링 프레임워크가 Wrapping해서 Proxy 객체로
    만들어서 가지고 있기 때문에  asyncCall_1 만 비동기로 하위 스레드에서 실행된 것을 확인 가능하다.
    즉, 반드시 bean 주입을 받아야 한다.
     */
    public void asyncCall_1(){
        System.out.println("[asyncCall_1] :: " + Thread.currentThread().getName());
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
        /*
        - 비동기로 동작할 수 있게 Sub Thread에게 위임
        - emailService.sendMail();
         */
    }

    /*
    [asyncCall_2] :: http-nio-8080-exec-4
    [sendMail] :: http-nio-8080-exec-4
    [messagingTaskExecutor] :: http-nio-8080-exec-4
    finish
     */
    public void asyncCall_2(){
        System.out.println("[asyncCall_2] :: " + Thread.currentThread().getName());
        EmailService emailService = new EmailService();
        emailService.sendMail();
        emailService.sendMailWithCustomThreadPool();
    }

    /*
    [asyncCall_3] :: http-nio-8080-exec-5
    [sendMail] :: http-nio-8080-exec-5
    finish
     */
    public void asyncCall_3(){
        System.out.println("[asyncCall_3] :: " + Thread.currentThread().getName());
        sendMail();
    }

    @Async
    public void sendMail(){
        System.out.println("[sendMail] :: " + Thread.currentThread().getName());
    }
}
