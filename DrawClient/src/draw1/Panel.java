/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package draw1;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel {

    private int x1, y1, x2, y2;
    private MyMouseHandler handler;
    private Graphics g;

    public Panel() {
        setBackground(Color.white);
        setPreferredSize(new Dimension(500, 500));

        handler = new MyMouseHandler();

        this.addMouseListener(handler);
        this.addMouseMotionListener(handler);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    private void initializeGraphics() {
        g = getGraphics();
    }

    private class MyMouseHandler extends MouseAdapter {

        @Override
        public void mousePressed(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            initializeGraphics();
            x2 = x1;
            y2 = y1;
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            x1 = e.getX();
            y1 = e.getY();
            String msg = x1 + ":" + y1 + ":" + x2 + ":" + y2;
            Client.sendToServer(msg);
            drawLine(x1, y1, x2, y2);
            x2 = x1;
            y2 = y1;
        }
    }
public void redrawFromState( String stateStr) {

        if (stateStr == null || stateStr.isEmpty()) {
            return;
        }
        String[] lines = stateStr.split(",");
        for (String line : lines) {
            String[] coords = line.split(":");
            int xx1 = Integer.parseInt(coords[0]);
            int yy1 = Integer.parseInt(coords[1]);
            int xx2 = Integer.parseInt(coords[2]);
            int yy2 = Integer.parseInt(coords[3]);
            drawLine(xx1, yy1, xx2, yy2);
        }

    }

    public  void drawLine(int xx1, int yy1, int xx2, int yy2) {
        initializeGraphics();
        g.drawLine(xx1, yy1, xx2, yy2);
    }
}
