import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.*;

public class SandPanel extends JPanel {

    private final int WIDTH = 500;
    private final int HEIGHT = 500;
    float hue = 5f;
    private World world = new World(WIDTH / 5, HEIGHT / 5);
    private int pixelSize = WIDTH / world.width;

    public SandPanel() {
        JRadioButton concreteButton = new JRadioButton("");
        JRadioButton waterButton = new JRadioButton("");
        // concreteButton.setText("concrete");
        concreteButton.setBackground(Color.gray);
        // add(waterButton);

        add(concreteButton);
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setBackground(Color.BLACK);
        Timer timer = new Timer(10, e -> {
            world.step();
            repaint();
        });
        timer.start();

        Timer pressed = new Timer(10, e -> {
            Point p = MouseInfo.getPointerInfo().getLocation();
            SwingUtilities.convertPointFromScreen(p, this);
            int column = (int) p.getX() / pixelSize;
            int row = (int) p.getY() / pixelSize;
            if (concreteButton.isSelected()) {
                world.addConcrete(row, column);
                world.addConcrete(row - 1, column);
                world.addConcrete(row, column - 1);
                world.addConcrete(row - 1, column - 1);
            } else if (waterButton.isSelected()) {

                // world.addWater(row, column, .7f);

            } else {
                world.addSand(row, column, hue);
                world.addSand(row - 1, column, hue);
                world.addSand(row + 1, column, hue);
                world.addSand(row, column - 1, hue);
                world.addSand(row, column + 1, hue);
                hue += 0.002;

            }
        });
        timer.start();

        // record mouse position

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                pressed.start();
            }
        });
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                pressed.stop();
            }
        });
    }

    public void paintComponent(Graphics g) {
        for (int i = 0; i < world.width; i++) {
            for (int j = 0; j < world.height; j++) {
                g.setColor(new Color(10, 10, 10));
                g.drawRect(i * pixelSize, j * pixelSize, pixelSize, pixelSize);
                if (!world.get(i, j).getType().equals("empty")) {
                    g.setColor(world.get(i, j).getColor());
                    g.fillRect(j * pixelSize, i * pixelSize, pixelSize, pixelSize);
                }
            }
        }
    }

}