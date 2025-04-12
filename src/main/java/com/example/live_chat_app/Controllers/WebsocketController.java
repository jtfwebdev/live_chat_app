package com.example.live_chat_app.Controllers;

import com.example.live_chat_app.DTO.IncomingMessageDTO;
import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Models.Message;
import com.example.live_chat_app.Services.ChatroomService;
import com.example.live_chat_app.Services.MessageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class WebsocketController {

    private final SimpMessagingTemplate messagingTemplate;

    @Autowired
    public WebsocketController(SimpMessagingTemplate messagingTemplate) {
        this.messagingTemplate = messagingTemplate;
    }

    @Autowired
    private MessageService messageService;

    @Autowired
    private ChatroomService chatroomService;

    @MessageMapping("/message") // binds method to /app/message route - /app prefix is dictated by config class
    public void handleMessage(IncomingMessageDTO messageRequest) {

        Chatroom chatroom = chatroomService.searchChatroomByName(messageRequest.getChatroomName());

        messageService.saveAndSendMessage(messageRequest, chatroom, messagingTemplate);

    }
}
