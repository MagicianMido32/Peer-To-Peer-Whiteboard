/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author MiDo
 */
public class ReverseServer {

    public static void startServer(int port) {

        try {
            ServerSocket serverSocket = new ServerSocket(port);
            ObjectsReferences.clients = new ArrayList<>();

            int i = 0;
            while (true) {
                Socket socket = serverSocket.accept();
                ClientHandler client = new ClientHandler(socket, i);
                client.sendToClient(ObjectsReferences.state.toString());
                System.out.println("New client connected " + i);
                i++;
                ObjectsReferences.clients.add(client);
                client.startReceiving();
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
        }
    }

    public static void sendToAllClients(String message) {
        for (ClientHandler client : ObjectsReferences.clients) {
            try {
                client.sendToClient(message);
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
                ex.printStackTrace();
            }

        }
    }
}
