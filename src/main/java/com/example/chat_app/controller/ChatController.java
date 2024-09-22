package com.example.chat_app.controller;

import com.example.chat_app.config.KafkaConstants;
import com.example.chat_app.domain.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ChatController {

    @Autowired
    KafkaTemplate<String, Message> kafkaTemplate;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/message/send", produces = "application/json")
    public void sendMessage(@RequestBody Message message) throws Exception {
        try {
            message.setTimestamp(LocalDateTime.now().toString());
            kafkaTemplate.send(KafkaConstants.KAFKA_TOPIC, message);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }


    //    -------------- WebSocket API ----------------
    @MessageMapping("/sendMessage") //send message by client (app/sendMessage)
    @SendTo("/topic/group") //pass this message to all subscriber of /topic/group url
    public Message broadcastGroupMessage(@Payload Message message) {
        return message;
    }

}
