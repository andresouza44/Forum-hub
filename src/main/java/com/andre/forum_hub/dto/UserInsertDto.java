package com.andre.forum_hub.dto;

public class UserInsertDto extends UserDto {
    private String password;

    private UserInsertDto(){}


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
