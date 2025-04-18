package com.example.live_chat_app.Repositories;

import com.example.live_chat_app.Models.Chatroom;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

@DataJpaTest
@ActiveProfiles("test")
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class ChatroomRepositoryTests {

    private ChatroomRepository chatroomRepository;

    @Autowired
    public ChatroomRepositoryTests(ChatroomRepository chatroomRepository) {
        this.chatroomRepository = chatroomRepository;
    }

    @Test
    public void ChatroomRepo_CreateChatroom_ReturnSavedChatroom() {

        Chatroom chatroom = Chatroom.builder().name("Test chatroom").build();

        Chatroom savedChatroom = chatroomRepository.save(chatroom);

        Assertions.assertThat(savedChatroom).isNotNull();
        Assertions.assertThat(savedChatroom.getId()).isGreaterThan(0);

    }

    @Test
    public void ChatroomRepo_FindAll_ReturnAllChatrooms() {

        Chatroom chatroom1 = Chatroom.builder().name("Test chatroom 1").build();
        Chatroom chatroom2 = Chatroom.builder().name("Test chatroom 2").build();

        chatroomRepository.save(chatroom1);
        chatroomRepository.save(chatroom2);

        List<Chatroom> chatrooms = chatroomRepository.findAll();

        Assertions.assertThat(chatrooms).isNotNull();
        Assertions.assertThat(chatrooms).size().isEqualTo(2);

    }

    @Test
    public void ChatroomRepo_FindByName_ReturnCorrectChatroom() {

        Chatroom chatroom = Chatroom.builder().name("Test chatroom").build();

        chatroomRepository.save(chatroom);

        Chatroom namedChatroom = chatroomRepository.findByName("Test chatroom");

        Assertions.assertThat(namedChatroom).isNotNull();
        Assertions.assertThat(namedChatroom.getName()).isEqualTo("Test chatroom");

    }

}
