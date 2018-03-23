package com.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Main {
    public static void main(String[] args) {
        try {
           int portNumber = Integer.parseInt(args[0]);
            ServerSocket serverSocket=new ServerSocket(portNumber);
            while (true) {
                Socket clientSocket = serverSocket.accept();

                new Thread(new User(clientSocket)).start();
            }
        }
        catch (NumberFormatException e) {
            System.out.println("Please provide a valid port number");
        }
        catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Usage: ");
            System.out.println("java Main portNumber");
        }
        catch(IOException e){
            e.getCause();
        }



    }
}