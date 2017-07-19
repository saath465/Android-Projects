/* this is the server side program to control the ppt 
*** program created by Saathvik Prasad *** 
The program is used to connect a mobiel device with the the computer side program using a
socket and send in the details over the connection to control the slide show.*/


package com.thelogicalcoder.pptcontroller;

import org.apache.poi.hslf.usermodel.*;
import org.apache.poi.sl.usermodel.SlideShow;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.plaf.FileChooserUI;
import java.awt.*;
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class PPTControllerServer {

    static Robot robot;
    static int slideCount = 0;
    static File ppt;


//Main Function
    
    public static void main(String[] args) {
        System.out.println("Starting server...");


        try {
            robot = new Robot();
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFileChooser jFileChooser = new JFileChooser();
        jFileChooser.setDialogType(JFileChooser.OPEN_DIALOG);
        jFileChooser.setFileFilter(new FileNameExtensionFilter("Powerpoint Presentations", "ppt", "pptx"));
        int returnVal = jFileChooser.showOpenDialog(null);
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            ppt = new File(jFileChooser.getSelectedFile().getAbsolutePath());
            try {
                SlideShow slideShow = new HSLFSlideShow(new FileInputStream(ppt));
                slideCount = slideShow.getSlides().size();
                Desktop.getDesktop().open(ppt);
                startServer();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

       
    }

 /* function to initiate the start of the server and accept the connection from the device*/

    static void startServer() {
        try {
            ServerSocket serverSocket = new ServerSocket(9878);
            Socket sock = serverSocket.accept();
            System.out.println("Client connected");
            InputStream inputStream = sock.getInputStream();
            OutputStream outputStream = sock.getOutputStream();
            PrintWriter printWriter = new PrintWriter(outputStream, true);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
            String receiveMessage;

            printWriter.println("slideCount" + slideCount);
            printWriter.flush();
            while (true) {
                if ((receiveMessage = bufferedReader.readLine()) != null) {
                    System.out.println(receiveMessage);
                    if (receiveMessage.equalsIgnoreCase("n")) {
                        nextSlide();
                    } else if (receiveMessage.equalsIgnoreCase("p")) {
                        previousSlide();
                    } else if (receiveMessage.equalsIgnoreCase("s")) {
                        startPresentation();
                    } else if (receiveMessage.equalsIgnoreCase("e")) {
                        endPresentation();
                    } else if (receiveMessage.contains("number")) {
                        gotoSlide(receiveMessage);
                    }
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

/* Function to monitor the key actions from the app and jump to the slide number
	desired by the user*/
    .

    static void gotoSlide(String slideNumber) {
        slideNumber = slideNumber.replace("number", "");
        char number[] = slideNumber.toCharArray();
        for (int i = 0; i < number.length; i++) {
            switch (number[i]) {
                case '0':
                    robot.keyPress(java.awt.event.KeyEvent.VK_0);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_0);
                    break;
                case '1':
                    robot.keyPress(java.awt.event.KeyEvent.VK_1);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_1);
                    break;
                case '2':
                    robot.keyPress(java.awt.event.KeyEvent.VK_2);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_2);
                    break;
                case '3':
                    robot.keyPress(java.awt.event.KeyEvent.VK_3);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_3);
                    break;
                case '4':
                    robot.keyPress(java.awt.event.KeyEvent.VK_4);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_4);
                    break;
                case '5':
                    robot.keyPress(java.awt.event.KeyEvent.VK_5);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_5);
                    break;
                case '6':
                    robot.keyPress(java.awt.event.KeyEvent.VK_6);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_6);
                    break;
                case '7':
                    robot.keyPress(java.awt.event.KeyEvent.VK_7);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_7);
                    break;
                case '8':
                    robot.keyPress(java.awt.event.KeyEvent.VK_8);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_8);
                    break;
                case '9':
                    robot.keyPress(java.awt.event.KeyEvent.VK_9);
                    robot.keyRelease(java.awt.event.KeyEvent.VK_9);
                    break;
            }
        }
        robot.keyPress(java.awt.event.KeyEvent.VK_ENTER);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ENTER);

    }

    //functions for slide controllers

    static void nextSlide() {
        robot.keyPress(java.awt.event.KeyEvent.VK_N);
        robot.keyRelease(java.awt.event.KeyEvent.VK_N);
    }

    static void previousSlide() {
        robot.keyPress(java.awt.event.KeyEvent.VK_P);
        robot.keyRelease(java.awt.event.KeyEvent.VK_P);
    }

    static void startPresentation() {
        robot.keyPress(java.awt.event.KeyEvent.VK_F5);
        robot.keyRelease(java.awt.event.KeyEvent.VK_F5);
    }

    static void endPresentation() {
        robot.keyPress(java.awt.event.KeyEvent.VK_ESCAPE);
        robot.keyRelease(java.awt.event.KeyEvent.VK_ESCAPE);
    }
}
