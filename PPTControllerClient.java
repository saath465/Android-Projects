/* This is the client part of the Ppt Controller app/ program. 
**
 * Created by Saathvik Prasad 
 
**The client creates a socket connection to the server counter part on the main system and 
	connects to the server using the port number provided. Once the connnection is established the device sends a welcome message to the server and 
	listens to the activity of it's end to send the same actions to the server.*/



package com.thelogicalcoder.pptcontroller;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;

public class PPTControllerClient {
    static Socket socket;

    public static void main(String[] args) {
        System.out.println("Starting client...");
        try {
            socket = new Socket("192.168.1.71", 9878);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JFrame jFrame = new JFrame("Client");
        Button button = new Button("Send");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {		//listen to action and send the information to the server.
                doThis();
            }
        });
        jFrame.add(button);
        jFrame.setSize(400, 600);
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    static void doThis() {
        try {
            OutputStream outputStream = socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            // BufferedReader keyRead = new BufferedReader(new InputStreamReader(System.in));
            String sendMessage;
            //char c = 'y';
            //while (true) {
            sendMessage = "Hello From the Client : Device Op3";
            printWriter.println(sendMessage);
            printWriter.flush();
          

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
