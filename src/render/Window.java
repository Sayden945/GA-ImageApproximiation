package render;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class Window extends JFrame {
    String PATH_OUTPUT = "results/";

    public Window(int w, int h) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(w, h));
        System.out.println("Canvas created with dimensions: \nWidth: " + w + "\nHeight: " + h);
        this.add(panel);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.pack();
        this.setVisible(true);
    }

    public void updateCanvas(BufferedImage img) {
        this.getContentPane().getGraphics().drawImage(img, 0, 0, null);
    }

    public void savePanelAsImage(JPanel panel, String filename) throws IOException {
        // Implement saving the current canvas to a file
        BufferedImage img = new BufferedImage(panel.getWidth(), panel.getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = img.createGraphics();
        panel.paint(g2d);
        g2d.dispose();
        ImageIO.write(img, "png", new java.io.File(filename));
    }

}
