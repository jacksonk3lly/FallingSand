import javax.swing.JFrame;

public class FallingSand {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Falling Sand");
        SandPanel panel = new SandPanel();
        frame.add(panel);
        frame.pack();
        frame.setBackground(java.awt.Color.BLACK);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}