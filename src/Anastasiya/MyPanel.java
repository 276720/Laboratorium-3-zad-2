package Anastasiya;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MyPanel extends JPanel {
    private Square square;
    private int startX, startY, endX, endY;
    private boolean isDrawing = false;
    private boolean isMoving = false;
    private boolean isDodaj = false;

    public MyPanel() {
        square = new Square(50,50,50,Color.cyan);
        setBackground(Color.LIGHT_GRAY);
        setBounds(10, 7, 365, 150);
        addListeners();

    }

    private void addListeners() {
        JButton rysuj = Main.getRysuj();
        JButton przeniesc = Main.getPrzeniesc();
        JButton dodac = Main.getDodac();

        rysuj.addActionListener(e -> {
            rysuj.setEnabled(false);
            przeniesc.setEnabled(true);
            dodac.setEnabled(true);
            isDrawing = true;
            isMoving = false;
            isDodaj = false;
            repaint();
        });

        przeniesc.addActionListener(e -> {
            if(isDodaj) {
                przeniesc.setEnabled(false);
                rysuj.setEnabled(true);
                dodac.setEnabled(true);
                isMoving = true;
                isDrawing = false;
            }
            else{
                przeniesc.setEnabled(false);
                rysuj.setEnabled(true);
                dodac.setEnabled(true);
                isMoving = false;
                isDrawing = false;
            }
            repaint();
        });

        dodac.addActionListener(e -> {
            if(!isDodaj) {
                dodac.setText("Ukryj");
                przeniesc.setEnabled(true);
                rysuj.setEnabled(true);
                isDodaj = true;
                isMoving = false;
                isDrawing = false;

            }
            else{
                dodac.setText("Dodaj");
                przeniesc.setEnabled(false);
                rysuj.setEnabled(true);
                isDodaj = false;
                isMoving = false;
                isDrawing = false;

            }
            repaint();
        });

        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (isDrawing) {
                    startX = e.getX();
                    startY = e.getY();
                } else if (isMoving) {
                    if (square.getX() <= e.getX() && e.getX() <= square.getX() + square.getSideLength() &&
                            square.getY() <= e.getY() && e.getY() <= square.getY() + square.getSideLength()){
                        startX = e.getX() - square.getX();
                        startY = e.getY()- square.getY();
                    }
                }
            }

            public void mouseReleased(MouseEvent e) {
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                if (isDrawing) {
                    endX = e.getX();
                    endY = e.getY();
                    Graphics g = getGraphics();
                    g.drawLine(startX, startY, endX, endY);
                    startX = endX;
                    startY = endY;
                } else if (isMoving) {
                        int deltaX = e.getX() - startX - square.getX();
                        int deltaY = e.getY() - startY - square.getY();
                        square.move(deltaX, deltaY);
                        repaint();
                }
            }
        });
    }
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if(isDodaj) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(square.getColor());
            g2d.fillRect(square.getX(), square.getY(), square.getSideLength(), square.getSideLength());
            g2d.dispose();
        }
    }
}
