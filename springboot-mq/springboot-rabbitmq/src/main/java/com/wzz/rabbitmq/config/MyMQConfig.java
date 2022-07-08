package com.wzz.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wzz
 */
//@Configuration
//public class MyMQConfig {
//
//
//    /**
//     * 容器中的 Queue、Exchange、Binding 都会自动创建（RabbitMq没有的情况）
//     *
//     * @return
//     */
//    @Bean
//    public Queue orderDelayQueue() {
//        //String name, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
//        Map<String, Object> arguments = new HashMap<>();
//        arguments.put("x-dead-letter-exchange","order-event-exchange");
//        arguments.put("x-dead-letter-routing-key","order.release.order");
//        arguments.put("x-message-ttl",60000);
//
//        Queue queue = new Queue("order.delay.queue", true, false, false, arguments);
//        return queue;
//    }
//
//    @Bean
//    public Queue ordeReleaseQueue() {
//        Queue queue = new Queue("order.release.order.queue", true, false, false);
//        return queue;
//    }
//
//    @Bean
//    public Exchange ordeEventExchange() {
//        return new TopicExchange("order-event-exchange",true,false);
//    }
//
//    @Bean
//    public Binding ordeCreateOrderBinding() {
//        Binding binding = new Binding("order.delay.queue",
//                Binding.DestinationType.QUEUE,
//                "order-event-exchange",
//                "order.create.order",null);
//        return binding;
//    }
//
//    @Bean
//    public Binding ordeReleaseOrderBinding() {
//        Binding binding = new Binding("order.release.order.queue",
//                Binding.DestinationType.QUEUE,
//                "order-event-exchange",
//                "order.release.order",null);
//        return binding;
//    }
//
//    /**
//     * 订单释放直接和库存释放进行绑定
//     * @return
//     */
//    @Bean
//    public Binding ordeReleaseOtherBinding() {
//        Binding binding = new Binding("stock.release.stock.queue",
//                Binding.DestinationType.QUEUE,
//                "order-event-exchange",
//                "order.release.other.#",null);
//        return binding;
//    }
//
//
//    /**
//     * 秒杀服务的消息队列
//     */
//    @Bean
//    public Queue ordeSeckillOrderQueue() {
//        Queue queue = new Queue("order.seckill.order.queue", true, false, false);
//        return queue;
//    }
//    @Bean
//    public Binding ordeSeckillOrderQueueBinding() {
//        Binding binding = new Binding("order.seckill.order.queue",
//                Binding.DestinationType.QUEUE,
//                "order-event-exchange",
//                "order.seckill.order",null);
//        return binding;
//    }
//
//
//}
