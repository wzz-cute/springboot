package com.wzz.rabbitmq.service.impl;

import com.rabbitmq.client.Channel;
import com.wzz.rabbitmq.entity.Student;
import com.wzz.rabbitmq.entity.User;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RabbitListener(queues = "hello-java-queue")
public class RabbitMQImpl {

    /**
     * org.springframework.amqp.core.Message
     *
     * @param message
     */
//    @RabbitListener(queues = "hello-java-queue")
    @RabbitHandler
    public void listenerRabbitQueue(Message message, User user,//此处对象可以根据rabbitmq 发送对象自动转换
                                    Channel channel) {
        System.out.println(message.getBody());
        System.out.println("监听：接受消息:" + user + "  类型:" + message.getClass());
    }

    @RabbitHandler
    public void listenerRabbitQueue(Message message, Student student,//此处对象可以根据rabbitmq 发送对象自动转换
                                    Channel channel) {
        System.out.println(message.getBody());
        System.out.println("监听：接受消息:" + student + "  类型:" + message.getClass());
    }
}
