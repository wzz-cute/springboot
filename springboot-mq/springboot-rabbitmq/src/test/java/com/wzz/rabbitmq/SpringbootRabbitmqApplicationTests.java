package com.wzz.rabbitmq;

import com.alibaba.fastjson.JSON;
import com.wzz.rabbitmq.entity.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
class SpringbootRabbitmqApplicationTests {

    @Autowired
    private AmqpAdmin amqpAdmin;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void sendMessage() throws InterruptedException {
        User user = new User();
        user.setUid("1");
        user.setName("aaa");
        user.setSex("男");
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello.java"
                , user);
//        rabbitTemplate.convertAndSend("hello-java-exchange","hello.java"
//        ,"hello");
//        Thread.sleep(1000);
    }

    @Test
    public void createExchanges() {
        //DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)
        DirectExchange directExchange = new DirectExchange("hello-java-exchange", true, false);
        amqpAdmin.declareExchange(directExchange);
    }

    @Test
    public void createExchanges2() {
        //DirectExchange(String name, boolean durable, boolean autoDelete, Map<String, Object> arguments)

        DirectExchange directExchange = new DirectExchange("hello-java-exchange3", true, false);
//        new CustomExchange();
//        new DirectExchange()
//        new TopicExchange()
//        new HeadersExchange()
//        new FanoutExchange()
        amqpAdmin.declareExchange(directExchange);
        amqpAdmin.declareExchange(directExchange);
    }

    @Test
    public void createQueue() {
//        Queue(String name, boolean durable, boolean exclusive, boolean autoDelete,
//        @Nullable Map<String, Object> arguments)

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 60000);
        Queue queue = new Queue("hello-java-queue", true, false, false);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void createQueue2() {
//        Queue(String name, boolean durable, boolean exclusive, boolean autoDelete,
//        @Nullable Map<String, Object> arguments)

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 60000);
        Queue queue = new Queue("hello-java-queue2", true, false, false, arguments);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void createQueue3() {
//        Queue(String name, boolean durable, boolean exclusive, boolean autoDelete,
//        @Nullable Map<String, Object> arguments)

        Map<String, Object> arguments = new HashMap<>();
        arguments.put("x-message-ttl", 60000);
        Queue queue = new Queue("hello-java-queue2", true, false, false, arguments);
        amqpAdmin.declareQueue(queue);
    }

    @Test
    public void sendRabbitMsg() {
        User user = new User();
        user.setUid("1");
        user.setName("aaa");
        user.setSex("男");
        rabbitTemplate.convertAndSend("hello-java-exchange", "hello2.java"
                , user);
    }

    @Test
    public void createBinding() {
        //(String destination, DestinationType destinationType, String exchange, String routingKey,
        //			@Nullable Map<String, Object> arguments)
        Binding binding = new Binding("hello-java-queue",
                Binding.DestinationType.QUEUE,
                "hello-java-exchange",
                "hello.java", null);
        amqpAdmin.declareBinding(binding);
    }

    @Test
    public void createBinding2() {
        //(String destination, DestinationType destinationType, String exchange, String routingKey,
        //			@Nullable Map<String, Object> arguments)
        Binding binding = new Binding("hello-java-queue2",
                Binding.DestinationType.QUEUE,
                "hello-java-exchange",
                "hello2.java", null);
        amqpAdmin.declareBinding(binding);
    }

}
