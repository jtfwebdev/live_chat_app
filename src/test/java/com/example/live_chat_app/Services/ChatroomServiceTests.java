package com.example.live_chat_app.Services;

import com.example.live_chat_app.DTO.ChatroomDTO;
import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Repositories.ChatroomRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class ChatroomServiceTests {

    @Mock
    private ChatroomRepository chatroomRepository;

    @InjectMocks
    private ChatroomService chatroomService;

    @Test
    public void ChatroomService_CreateChatroom_AddsNewChatroom() {

        ChatroomDTO newChatroomDto = ChatroomDTO.builder().name("Testing Mockito Chatroom").build();

        chatroomService.createNewChatroom(newChatroomDto);

        ArgumentCaptor<Chatroom> chatroomCaptor = ArgumentCaptor.forClass(Chatroom.class);
        verify(chatroomRepository, times(1)).save(chatroomCaptor.capture());
        verify(chatroomRepository, times(1)).flush();

        Chatroom savedChatroom = chatroomCaptor.getValue();
        Assertions.assertThat(savedChatroom.getName()).isEqualTo("Testing Mockito Chatroom");

    }

}
