package com.andre.forum_hub.service.exceptions;

public class ResourceNotFoundException extends RuntimeException{

    public ResourceNotFoundException (String message){
        super(message);
    }

}
