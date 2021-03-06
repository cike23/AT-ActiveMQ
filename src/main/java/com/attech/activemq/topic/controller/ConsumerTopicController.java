package com.attech.activemq.topic.controller;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.jms.*;

/**
 * 消费者Controller
 *
 * @author Aaron
 * @version v1.0
 * @create 2018-08-07 17:19
 **/
@Controller
public class ConsumerTopicController {

    @RequestMapping("/topicGetMessage1")
    public void topicGetMessage() throws Exception {
        //1. 创建一个连接工厂，需要传入TCP协议的ActiveMQ服务地址
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2. 创建连接
        Connection connection = connectionFactory.createConnection();
        //3. 开启连接
        connection.start(); //注意不是open。。。
        //4. 获取session
        //里面有两个参数，参数1为是否开启事务，参数2为消息确认模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5. 创建一对多的消息广播
        Topic topic = session.createTopic("test_topic");

        //------------前5步都是相同的，以下为不同----------------
        //6. 创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7. 使用监听器监听队列中的消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println("收到消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //由于设置监听器后不能马上结束方法，要在这里加一个等待点
        System.in.read();
        //8. 关闭连接
        consumer.close();
        session.close();
        connection.close();
    }



    @RequestMapping("/topicGetMessage2")
    public void topicGetMessage2() throws Exception{
        //1. 创建一个连接工厂，需要传入TCP协议的ActiveMQ服务地址
        ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //2. 创建连接
        Connection connection = connectionFactory.createConnection();
        //3. 开启连接
        connection.start(); //注意不是open。。。
        //4. 获取session
        //里面有两个参数，参数1为是否开启事务，参数2为消息确认模式
        Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        //5. 创建一对多的消息广播
        Topic topic = session.createTopic("test_topic");

        //------------前5步都是相同的，以下为不同----------------
        //6. 创建消费者
        MessageConsumer consumer = session.createConsumer(topic);
        //7. 使用监听器监听队列中的消息
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage textMessage = (TextMessage) message;
                try {
                    String text = textMessage.getText();
                    System.out.println("收到消息：" + text);
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        });

        //由于设置监听器后不能马上结束方法，要在这里加一个等待点
        System.in.read();
        //8. 关闭连接
        consumer.close();
        session.close();
        connection.close();
    }
}