package com.example.chat_app.service;

import com.example.chat_app.config.KafkaConstants;
import com.example.chat_app.domain.Message;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaConsumer {
//    @Autowired
//    SimpMessagingTemplate template;

    @KafkaListener(
            topics = KafkaConstants.KAFKA_TOPIC,
            groupId = KafkaConstants.GROUP_ID
    )
    public void listen(Message message) {
        System.out.println(message.getSender() + ":" + message.getContent());
//            template.co("/topic/group", message);
    }

}
