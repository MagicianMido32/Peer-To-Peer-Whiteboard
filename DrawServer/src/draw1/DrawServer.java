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

public class DrawServer {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws Exception {

        JFrame frame = new JFrame("Server Panel");
        frame.setDefaultCloseOperation(3);
        frame.setResizable(false);

        Panel panel = new Panel();
        frame.add(panel);
        frame.pack();
        frame.setVisible(true);

        ObjectsReferences.panel = panel;
        ObjectsReferences.state = new StringBuilder("");

        ReverseServer.startServer(4444);

    }
}
