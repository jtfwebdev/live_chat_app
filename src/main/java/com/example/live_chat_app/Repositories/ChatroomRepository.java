package com.example.live_chat_app.Repositories;

import com.example.live_chat_app.Models.Chatroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

    Chatroom findByName(String name);

}
