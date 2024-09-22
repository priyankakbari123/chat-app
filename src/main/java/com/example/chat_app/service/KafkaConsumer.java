package com.example.chat_app.service;

import com.example.chat_app.config.KafkaConstants;
import com.example.chat_app.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
    @Autowired
    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println(message.getSender() + ":" + message.getContent());
        template.convertAndSend("/topic/group", message);//send message to all subscriber of /topic/group websocket url
    }

}
