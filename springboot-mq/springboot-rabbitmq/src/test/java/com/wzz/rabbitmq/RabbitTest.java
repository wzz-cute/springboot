package com.wzz.rabbitmq;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitTest {
    //主要发送消息
    @Autowired
    private RabbitTemplate rabbitTemplate;

    //主要创建交换机 ，队列 ，绑定
    @Autowired
    private AmqpAdmin amqpAdmin;

    //创建队列
    @Test
    public void createQueue() {
        Queue queue = new Queue("test-jiajia", true, false, false);
        String s = amqpAdmin.declareQueue(queue);
        System.out.println(s);
    }

    //创建交换机
    @Test
    public void createExchanges() {
        Exchange directExchange = new DirectExchange("ex-jiajia", true, false);
        amqpAdmin.declareExchange(directExchange);
    }

    //创建绑定关系
    @Test
    public void createBinding() {
        Binding binding = new Binding("test-jiajia", Binding.DestinationType.QUEUE,
                "ex-jiajia", "hello.jiajia", null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void sendMsg(){
        rabbitTemplate.convertAndSend("ex-jiajia","hello.jiajia", "hello3 ====>> abcdefg");
    }


}
