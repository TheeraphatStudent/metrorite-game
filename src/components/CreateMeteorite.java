package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

import utils.LoadedContent;

public class CreateMeteorite extends JPanel implements ActionListener {
    private int WIDTH = 50;
    private int HEIGHT = 50;

    private int meteoriteImagesNumbers = 10;
    private Image meteoriteUsage;

    public CreateMeteorite() {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        this.setBackground(Color.GRAY);

        int usage = (int) (Math.random() * meteoriteImagesNumbers) + 1;
        this.meteoriteUsage = new LoadedContent().loadImage(usage + ".png");

        this.setSize(new Dimension(WIDTH, HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        if (meteoriteUsage != null) {
            g.drawImage(meteoriteUsage, 0, 0, getWidth(), getHeight(), this);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("Clicked Meteorite!");
    }
}
