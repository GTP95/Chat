package com.server;

public class UserNotAttachedExeption extends Exception {
    @Override
    public String getMessage(){
        return "User not attached to observer!!!";
    }
}
