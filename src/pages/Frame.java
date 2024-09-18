package pages;

import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import components.CreateMeteorite;
import resource.colors.Configs;

public class Frame extends JFrame {
    int WIDTH = 500;
    int HEIGHT = 500;

    public Frame() {
        this.setTitle("Meteorite Game");
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setSize(new Dimension(WIDTH, HEIGHT));

        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Configs().primary());

        JLayeredPane layers = new JLayeredPane();
        layers.add(new MeteoriteZone(WIDTH, HEIGHT), JLayeredPane.PALETTE_LAYER);

        this.add(layers);
        this.pack();

    }

    private void reload() {
        this.revalidate();
        this.repaint();

    }

}
