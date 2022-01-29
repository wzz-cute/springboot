package com.wzz.rabbitmq.controller;

import com.wzz.rabbitmq.entity.Student;
import com.wzz.rabbitmq.entity.User;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rabbitCon")
public class RabbitController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/testRabbit")
    public void sendRabbitMsg() {
        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                User user = new User();
                user.setName("userClass" + i);
                rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java"
                        , user);
                continue;
            }
            Student student = new Student();
            student.setName("studentClass" + i);
            rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java"
                    , student);
        }
    }

}
