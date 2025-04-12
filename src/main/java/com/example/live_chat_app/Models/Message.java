package com.example.live_chat_app.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDateTime;

@Entity
@Data
@Table(name="messages")
@AllArgsConstructor
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user", nullable = false)
    private String user;

    @Column(name="message", nullable = false)
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id", nullable = false)
    @JsonIgnore
    @ToString.Exclude
    private Chatroom chatroom;

    @Column(name="timestamp")
    private LocalDateTime timestamp;

    public Message(String user, String message, Chatroom chatroom) {
        this.user = user;
        this.message = message;
        this.chatroom = chatroom;
    }

    @PrePersist
    protected void onCreate() {
        this.timestamp = LocalDateTime.now();
    }
}
