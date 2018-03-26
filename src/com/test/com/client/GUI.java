package com.test.com.client;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import javax.swing.*;

public class GUI extends JPanel implements ActionListener {
    protected JTextField textField;
    protected JTextArea textArea;
    private final static String newline = "\n";
    private final Socket clientSocket;
    private final User user;
   // private final GUI gui;

    public GUI(Socket clientSocket, User user) {
        super(new GridBagLayout());

        textField = new JTextField(50);
        textField.addActionListener(this);

        textArea = new JTextArea(30, 50);
        textArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(textArea);

        this.clientSocket=clientSocket;
       /* try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }*/

        this.user=user;

        //Add Components to this panel.
        GridBagConstraints c = new GridBagConstraints();
        c.gridwidth = GridBagConstraints.REMAINDER;

        c.fill = GridBagConstraints.HORIZONTAL;
        //add(textField, c);

        c.fill = GridBagConstraints.BOTH;
        c.weightx = 1.0;
        c.weighty = 1.0;
        //  add(scrollPane, c);
        add(scrollPane,c);
        add(textField,c);
    }

    public void actionPerformed(ActionEvent evt) {
        String text = textField.getText();
        try {
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);



            // textArea.append(text + newline);
            if (text.equals("/quit")) {
                out.println(text);
                return; //TODO: TERMINARE IL PROGRAMMA
            }
            out.println(user.getName() + ": " + text);
        }
        catch (java.io.IOException e){
            e.printStackTrace();
        }
        textField.selectAll();

        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        //textArea.setCaretPosition(textArea.getDocument().getLength());
    }

    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event dispatch thread.
     */
    public static GUI createAndShowGUI(Socket clientSocket, User user) {
        //Create and set up the window.
        JFrame frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GUI gui=new GUI(clientSocket, user);
        //Add contents to the window.
        frame.add(gui);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
        return gui;
    }

    public void receiveMessage(String message){
        textArea.append(message);
        //Make sure the new text is visible, even if there
        //was a selection in the text area.
        textArea.setCaretPosition(textArea.getDocument().getLength());
    }

   /* public static void main(String[] args) {
        //Schedule a job for the event dispatch thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }*/
}