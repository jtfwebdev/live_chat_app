package com.example.live_chat_app.Controllers;

import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Services.ChatroomService;
import com.example.live_chat_app.Services.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class ChatroomControllerTests {

    private MockMvc mvc;

    @Mock
    private ChatroomService chatroomService;

    @Mock
    private MessageService messageService;

    @InjectMocks
    private ChatroomController chatroomController;

    private JacksonTester<Chatroom> jsonChatroom;

    @BeforeEach
    public void setup() {

        JacksonTester.initFields(this, new ObjectMapper());

        mvc = MockMvcBuilders
                .standaloneSetup(chatroomController)
                .build();

    }

    @Test
    public void ChatroomController_GetChatrooms_ReturnsAllChatrooms() throws Exception {

        List<Chatroom> chatrooms = new ArrayList<>();
        Chatroom chatroom1 = new Chatroom("Test chatroom 1");
        Chatroom chatroom2 = new Chatroom("Test chatroom 2");
        chatrooms.add(chatroom1);
        chatrooms.add(chatroom2);

        given(chatroomService.getAllChatrooms()).willReturn(chatrooms);

        mvc.perform(get("/get-chatrooms"))  // Adjust path if needed
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(2)))
                .andExpect(jsonPath("$[0].name", is("Test chatroom 1")))
                .andExpect(jsonPath("$[1].name", is("Test chatroom 2")));

    }



}
