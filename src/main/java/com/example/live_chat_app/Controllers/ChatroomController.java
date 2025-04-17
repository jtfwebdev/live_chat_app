package com.example.live_chat_app.Controllers;

import com.example.live_chat_app.CustomExceptions.ResourceNotFoundException;
import com.example.live_chat_app.DTO.ChatroomDTO;
import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Models.Message;
import com.example.live_chat_app.Services.ChatroomService;
import com.example.live_chat_app.Services.MessageService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ChatroomController {

    @Autowired
    ChatroomService chatroomService;

    @Autowired
    MessageService messageService;

    @GetMapping("/get-chatrooms")
    public ResponseEntity<List<Chatroom>> getChatrooms() throws ResourceNotFoundException {

        List<Chatroom> chatrooms = chatroomService.getAllChatrooms();

        if (chatrooms.isEmpty()) {
            throw new ResourceNotFoundException("No chatrooms found");
        }

        return new ResponseEntity<>(chatroomService.getAllChatrooms(), HttpStatus.OK);

    }

    @PostMapping("create-chatroom")
    public ResponseEntity<List<Chatroom>> createChatroom(@Valid @RequestBody ChatroomDTO chatroomRequest) {

        chatroomService.createNewChatroom(chatroomRequest);

        return new ResponseEntity<>(chatroomService.getAllChatrooms(), HttpStatus.OK);

    }

    @GetMapping("join-chatroom/{chatroomName}")
    public ResponseEntity<List<Message>> fetchChatroomHistory(@PathVariable String chatroomName) {

        return new ResponseEntity<>(messageService.loadMessages(chatroomName), HttpStatus.OK);

    }

}
