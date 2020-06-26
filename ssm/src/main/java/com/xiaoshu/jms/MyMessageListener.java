package com.xiaoshu.jms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.w3c.dom.Text;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

public class MyMessageListener implements MessageListener {
    @Autowired
    private RedisTemplate redisTemplate;
    @Override
    public void onMessage(Message message) {
        try {
            String text = ((TextMessage) message).getText();
            System.out.println(text);
            String[] split = text.split("-");
            redisTemplate.boundValueOps(split[0]+"").set(split[1]);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
