package com.example.live_chat_app.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ChatroomDTO {

    @NotBlank(message = "Chatroom name is required")
    private String name;

}
