package com.example.live_chat_app.Services;

import com.example.live_chat_app.DTO.IncomingMessageDTO;
import com.example.live_chat_app.DTO.MessageDTO;
import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Models.Message;
import com.example.live_chat_app.Repositories.MessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);

    @Autowired
    private MessageRepository messageRepo;

    public void saveAndSendMessage(IncomingMessageDTO messageRequest, Chatroom chatroom, SimpMessagingTemplate messagingTemplate) {

        String destination = "/topic/chatroom/" + messageRequest.getChatroomName();

        Message newMessage = new Message(messageRequest.getUser(), messageRequest.getMessage(), chatroom);

        messageRepo.save(newMessage);

        MessageDTO dispatchMessage = new MessageDTO(newMessage.getUser(), newMessage.getMessage(), newMessage.getTimestamp());

        logger.info("Received message from user: {}: {}", newMessage.getUser(), newMessage.getMessage());
        messagingTemplate.convertAndSend( destination, dispatchMessage);
        logger.info("Sent message to {}: {}: {}", destination, newMessage.getUser(), newMessage.getMessage());

    }

    public List<Message> loadMessages(String chatroomName) {

        return messageRepo.findAllByChatroomName(chatroomName);

    }
}
