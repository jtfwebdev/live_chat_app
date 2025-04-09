package com.example.live_chat_app.Controllers;

import com.example.live_chat_app.Models.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    private static final Logger logger = LoggerFactory.getLogger(WebsocketController.class);
    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebsocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/message") // binds method to /app/message route - /app prefix is dictated by config class
    public void handleMessage(Message message) {
        logger.info("Received message from user: {}: {}", message.getUser(), message.getMessage());
        messagingTemplate.convertAndSend("/topic/messages", message);
        logger.info("Sent message to /topic/messages: {}: {}", message.getUser(), message.getMessage());
    }
}
