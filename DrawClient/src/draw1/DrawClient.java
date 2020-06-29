/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw1;

/**
 *
 * @author MiDo
 */
import javax.swing.*;

public class DrawClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        JFrame frame = new JFrame("Client Panel");
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);
        Panel panel = new Panel();
        frame.add(panel);
        ObjectsReferences.panel = panel;
        frame.pack();
        frame.setVisible(true);
        Client.startReceiving("127.0.0.1", 4444);

    }
}
