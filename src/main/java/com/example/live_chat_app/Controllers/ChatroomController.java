package com.example.live_chat_app.Controllers;

import com.example.live_chat_app.DTO.ChatroomDTO;
import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Services.ChatroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.List;

@RestController
public class ChatroomController {

    @Autowired
    ChatroomService chatroomService;

    @GetMapping("/get-chatrooms")
    public ResponseEntity<List<Chatroom>> getChatrooms() {

        System.out.println(fetchChatrooms());
        return new ResponseEntity<>(fetchChatrooms(), HttpStatus.OK);

    }

    @PostMapping("create-chatroom")
    public ResponseEntity<List<Chatroom>> createChatroom(@RequestBody ChatroomDTO chatroomRequest) {

        chatroomService.createNewChatroom(chatroomRequest);

        return new ResponseEntity<>(fetchChatrooms(), HttpStatus.OK);

    }

    private List<Chatroom> fetchChatrooms() {

        return chatroomService.getAllChatrooms();

    }

}
