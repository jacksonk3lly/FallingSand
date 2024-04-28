import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class SandPanel extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    private World world = new World(50, 50);
    private int pixelSize = WIDTH / world.width;

    public SandPanel() {
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        world.addSand(10, 10);
        Timer timer = new Timer(80, e -> {
            world.step();
            repaint();
        });
        timer.start();

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                // Record the click
                int x = e.getX();
                int y = e.getY();
                int column = x / pixelSize;
                int row = y / pixelSize;
                System.out.println("Mouse clicked at " + row + ", " + column);
                world.addSand(row, column);

            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                // Record the click
                int x = e.getX();
                int y = e.getY();
                int column = x / pixelSize;
                int row = y / pixelSize;
                System.out.println("Mouse clicked at " + row + ", " + column);
                world.addSand(row, column);

            }
        });
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < world.width; i++) {
            for (int j = 0; j < world.height; j++) {
                g.setColor(Color.WHITE);
                g.drawRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
                if (world.get(i, j) != 0) {
                    g.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                }
            }
        }
    }

}