package pages;

import java.awt.Color;
import java.awt.Dimension;
import java.util.LinkedHashMap;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import resource.colors.Configs;
import utils.UseGlobal;

public class Frame extends JFrame {
    public Frame(UseGlobal globalState) {
        this.setTitle("Meteorite Game");

        LinkedHashMap<String, Double> options = globalState.getOptions();
        int width = (int) Math.floor(options.get("Width"));
        int height = (int) Math.floor(options.get("Height"));

        this.setPreferredSize(new Dimension(width, height));
        this.setSize(new Dimension(width, height));
        this.setMinimumSize(new Dimension(width, height));

        this.setLocationRelativeTo(null);
        this.getContentPane().setBackground(new Configs().primary());

        // Meteorite
        JLayeredPane layers = new JLayeredPane();
        layers.add(new MeteoriteZone(width, height, options), JLayeredPane.PALETTE_LAYER);

        this.add(layers);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

    }

}
