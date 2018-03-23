package com.server;

import java.util.ArrayList;

public class Observer {
    private static ArrayList<User> userArrayList=new ArrayList<User>();

    public static void attach(User user) throws UserNotAttachedExeption{
        if(!userArrayList.add(user)) throw new UserNotAttachedExeption();
    }

    public static boolean detach(User user){
        return userArrayList.remove(user);
    }

    public static void sendMessage(String message){
        for(User user : userArrayList){
            user.receiveMessage(message);
        }
    }
}
