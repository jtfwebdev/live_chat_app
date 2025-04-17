package com.example.live_chat_app.CustomExceptions;

import lombok.Getter;

@Getter
public class ResourceNotFoundException extends Exception{

    private String message;

    public ResourceNotFoundException(String message) {

        super(message);

    }
}
