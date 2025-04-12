package com.example.live_chat_app.Repositories;

import com.example.live_chat_app.Models.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatroomId(Long chatroomId);

    List<Message> findAllByChatroomName(String chatroomName);

}
