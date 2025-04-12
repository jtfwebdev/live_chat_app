package com.example.live_chat_app.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class IncomingMessageDTO {

    private String user;
    private String message;
    private String chatroomName;

}
