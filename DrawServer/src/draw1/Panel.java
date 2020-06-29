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
    
    public void initializeGraphics() {
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
            drawLine(x1, y1, x2, y2);
            String msg = x1 + ":" + y1 + ":" + x2 + ":" + y2;
            ReverseServer.sendToAllClients(msg);
            x2 = x1;
            y2 = y1;
        }
    }

    
    public void drawLine(int xx1, int yy1, int xx2, int yy2) {
        initializeGraphics();
        String msg = xx1 + ":" + yy1 + ":" + xx2 + ":" + yy2;
        ObjectsReferences.state.append(msg).append(",");
        g.drawLine(xx1, yy1, xx2, yy2);

    }
}
