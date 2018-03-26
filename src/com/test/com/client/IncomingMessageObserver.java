package com.test.com.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

public class IncomingMessageObserver implements Runnable {
    Socket clientSocket;
    GUI gui;


    public IncomingMessageObserver(Socket clientSocket, GUI gui){
        this.clientSocket=clientSocket;
        this.gui=gui;
    }
    @Override
    public void run() {
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            while (true){   //TODO: terminare il thread quando termina il thread principale
                if(in.ready()) gui.receiveMessage(in.readLine());

            }
        }
        catch(java.io.IOException e){
            e.printStackTrace();
        }
    }
}
