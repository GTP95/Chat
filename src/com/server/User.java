package com.server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class User implements Runnable {
    private Socket clientSocket;
    private String userName;
  //  private PrintWriter out;

    public User(Socket clientSocket) {
        this.clientSocket = clientSocket;
      /* try {
            out=new PrintWriter(clientSocket.getOutputStream(),true);
        } catch (IOException e) {
            e.printStackTrace();
        }*/
    }

    private void sendToUser(String text){
       /* out.println(text);
        out.flush();
        */
    }

    public void receiveMessage(String message){
        try {
           PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
            out.println(message);
            out.flush();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void run(){
        try {
            BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            //PrintWriter out=new PrintWriter(clientSocket.getOutputStream(),true);
            String inputLine;
            try{Observer.attach(this);}
            catch (UserNotAttachedExeption e){System.out.println(e.getMessage());}

            while(true){
                inputLine=in.readLine();
                if(inputLine.equals("/quit")) break;
               // System.out.println(inputLine);
                Observer.sendMessage(inputLine);
            }
            System.out.println("User quit");
        } catch (IOException e) {
            e.printStackTrace();

        }
        finally {
            try {
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
