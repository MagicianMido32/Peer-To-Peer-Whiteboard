/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author MiDo
 */
public class Client {

    static Socket socket;

  
    public static void startReceiving(String host, int port) {
        new Thread() {
            @Override
            public void run() {
                try {
                    socket = new Socket(host, port);
                    InputStream input = socket.getInputStream();
                    BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                    String stateMessage = reader.readLine();
                    ObjectsReferences.panel.redrawFromState(stateMessage);
                    
                    while (true) {
                        String serverMessage = reader.readLine();
                        //System.out.println("Message From server : " + serverMessage);
                        String[] coords = serverMessage.split(":");
                        int xx1 = Integer.parseInt(coords[0]);
                        int yy1 = Integer.parseInt(coords[1]);
                        int xx2 = Integer.parseInt(coords[2]);
                        int yy2 = Integer.parseInt(coords[3]);
                        ObjectsReferences.panel.drawLine(xx1, yy1, xx2, yy2);
                    }
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public static void sendToServer(String message) {
        new Thread() {
            @Override
            public void run() {
                OutputStream output;
                try {
                    output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output);
                    writer.println(message);
                    writer.flush();
                } catch (IOException ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }.start();
    }
}
