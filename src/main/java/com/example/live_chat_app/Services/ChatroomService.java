package com.example.live_chat_app.Services;

import com.example.live_chat_app.DTO.ChatroomDTO;
import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Repositories.ChatroomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatroomService {

    @Autowired
    ChatroomRepository chatroomRepo;

    public List<Chatroom> getAllChatrooms() {

        return chatroomRepo.findAll();

    }

    public void createNewChatroom(ChatroomDTO chatroom) {

        Chatroom newChatroom = new Chatroom(chatroom.getName());

        chatroomRepo.save(newChatroom);

        chatroomRepo.flush();

    }

}
