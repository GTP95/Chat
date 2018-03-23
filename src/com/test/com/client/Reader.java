package com.test.com.client;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Reader implements Runnable{

    private Socket clientSocket;
    private User user;

    public Reader(Socket clientSocket, User user){
        this.clientSocket=clientSocket;
        this.user=user;
    }
    public void run() {
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
            Scanner scanner = new Scanner(System.in);
            String userInput;
            while (true) {
                if (scanner.hasNextLine()) {
                    userInput = scanner.nextLine();
                    if (userInput.equals("/quit")) {
                        out.println(userInput);
                        break;
                    }
                    out.println(user.getName() + ": " + userInput);
                    out.flush();
                }
            }
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }
    }
}
