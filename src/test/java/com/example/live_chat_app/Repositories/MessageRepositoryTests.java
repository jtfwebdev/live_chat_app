package com.example.live_chat_app.Repositories;

import com.example.live_chat_app.Models.Chatroom;
import com.example.live_chat_app.Models.Message;
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
public class MessageRepositoryTests {

    private MessageRepository messageRepository;

    private ChatroomRepository chatroomRepository;

    @Autowired
    public MessageRepositoryTests(MessageRepository messageRepository, ChatroomRepository chatroomRepository) {
        this.messageRepository = messageRepository;
        this.chatroomRepository = chatroomRepository;
    }

    @Test
    public void MessageRepo_FindByChatroomName_ReturnsMessagesBelongingToChatroom() {

        Chatroom chatroom1 = Chatroom.builder().name("Chatroom 1").build();
        Chatroom chatroom2 = Chatroom.builder().name("Chatroom 2").build();

        chatroomRepository.save(chatroom1);
        chatroomRepository.save(chatroom2);

        Message message1 = new Message("User 1", "Message 1", chatroom1);
        Message message2 = new Message("User 2", "Message 2", chatroom1);
        Message message3 = new Message("User 1", "Message 3", chatroom2);

        messageRepository.save(message1);
        messageRepository.save(message2);
        messageRepository.save(message3);

        List<Message> messages = messageRepository.findAllByChatroomName("Chatroom 1");
        List<Message> noMessages = messageRepository.findAllByChatroomName("No Chatroom");

        Assertions.assertThat(messages).size().isEqualTo(2);
        Assertions.assertThat(messages.get(1).getMessage()).isEqualTo("Message 2");
        Assertions.assertThat(noMessages).isEmpty();

    }
}
