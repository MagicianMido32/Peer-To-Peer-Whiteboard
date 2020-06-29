/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw1;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

/**
 *
 * @author MiDo
 */
public class ClientHandler {

    private Socket socket;
    public int ClientId;

    public ClientHandler(Socket socket, int ClientId) {
        this.socket = socket;
        this.ClientId = ClientId;
    }

    public void startReceiving() {
        new Thread() {
            @Override
            public void run() {
                try {

                    while (true) {
                        try {
                            InputStream input = socket.getInputStream();
                            BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                            String clientMessage = reader.readLine();
                            System.out.println("Message from client " + ClientId + " : " + clientMessage);
                            String[] coords = clientMessage.split(":");
                            int xx1 = Integer.parseInt(coords[0]);
                            int yy1 = Integer.parseInt(coords[1]);
                            int xx2 = Integer.parseInt(coords[2]);
                            int yy2 = Integer.parseInt(coords[3]);
                            ObjectsReferences.panel.drawLine(xx1, yy1, xx2, yy2);
                            sendToAllClientsExceptThis(clientMessage);

                        } catch (Exception ex) {
                            System.out.println(ex.getMessage());
                            ex.printStackTrace();
                        }
                    }

                } catch (Exception ex) {

                }
            }
        }.start();
    }

    public void sendToClient(String message) {
        new Thread() {
            @Override
            public void run() {
                try {
                    OutputStream output = socket.getOutputStream();
                    PrintWriter writer = new PrintWriter(output);
                    writer.println(message);
                    writer.flush();
                } catch (Exception ex) {
                    System.out.println(ex.getMessage());
                    ex.printStackTrace();
                }
            }
        }.start();
    }

    public void sendToAllClientsExceptThis(String message) {
        for (ClientHandler client : ObjectsReferences.clients) {
            if (client.ClientId != ClientId) {
                System.out.println("From client " + ClientId + " sending to " + client.ClientId);
                client.sendToClient(message);
            }
        }
    }
}
