package com.test.com.client;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    private static GUI gui;

    public static void setGui(GUI gui) {
        gui = gui;
    }

    private void send(String text, PrintWriter out){
        out.println(text);
        out.flush();
    }

    private void receive(BufferedReader in) throws java.io.IOException{
        System.out.println(in.readLine());
    }

    public static void main(String[] args) {
       try{ //prova a caricare le impostazioni dal file settings
           FileReader fileReader=new FileReader("settings");
           BufferedReader bufferedReader=new BufferedReader(fileReader);
           User user=new User(bufferedReader.readLine());
           String address=bufferedReader.readLine();
           String portNumberString=bufferedReader.readLine();
           int portNumber=Integer.parseInt(portNumberString);
           bufferedReader.close();

           Socket clientSocket=new Socket(address, portNumber);
           //PrintWriter out=new PrintWriter(clientSocket.getOutputStream(), true);
           //BufferedReader in=new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
          // Scanner scanner=new Scanner(System.in);
          // String userInput;
          /* while(true){
               if(scanner.hasNextLine()){
                   userInput=scanner.nextLine();
                   if (userInput.equals("/quit")){
                       out.println(userInput);
                       break;
                   }
                   out.println(user.getName()+": "+userInput);
               }
               System.out.println(in.readLine());
           }*/
           javax.swing.SwingUtilities.invokeLater(new Runnable() {
               public void run() {
                  GUI gui=GUI.createAndShowGUI(clientSocket, user);
                    Main.setGui(gui);
               }
           });
          Thread incomingMessageObserver=new Thread(new IncomingMessageObserver(clientSocket,gui));
         try {
             incomingMessageObserver.join();
         }
         catch(InterruptedException e){
             e.printStackTrace();
         }

         gui.receiveMessage("READY");

       }
       catch (FileNotFoundException e) {
           try {    //Se il file non esiste lo crea e ci scrive dentro le impostazioni
               FileWriter fileWriter = new FileWriter("settings");
               BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
               Scanner scanner = new Scanner(System.in);
               System.out.print("Please input your name: ");
               bufferedWriter.write(scanner.nextLine());
               bufferedWriter.newLine();
               System.out.print("Please input default server address (IP or URL): ");
               bufferedWriter.write(scanner.nextLine());
               bufferedWriter.newLine();
               System.out.print("Please input default server port: ");
               bufferedWriter.write(scanner.nextLine());
               bufferedWriter.newLine();
               bufferedWriter.close();
           }
           catch (IOException ex) {
               ex.printStackTrace();
           }
       }
       catch (IOException e){
           e.printStackTrace();
       }
    }
}
