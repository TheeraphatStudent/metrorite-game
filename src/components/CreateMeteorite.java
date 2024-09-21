package components;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import javax.swing.JPanel;


import utils.LoadedContent;

public class CreateMeteorite extends JPanel {
    private int clickCount = 0;
    private boolean destroyMeteorite = true;

    private int WIDTH = 50;
    private int HEIGHT = 50;

    private int meteoriteImagesNumbers = 10;
    private Image meteoriteUsage;

    public CreateMeteorite() {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // this.setBackground(Color.GRAY);
        this.setOpaque(false);

        int usage = (int) (Math.random() * meteoriteImagesNumbers) + 1;
        this.meteoriteUsage = new LoadedContent().loadImage(usage + ".png");

        this.setSize(new Dimension(WIDTH, HEIGHT));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                clickCount++;
                System.out.println("Panel clicked " + clickCount + " times.");
            }
        });
    }

    // @Override
    // public Rectangle getBounds() {
    //     // new Rectangle(this.getPositionX(), this.getPositionY(), 30, 20);
    //     return new Rectangle(this.getX(), this.getY(), WIDTH + 10, HEIGHT + 10);

    // }

    // public boolean intersects(Enemy enemy) {
    //     return getBounds().intersects(enemy.getBounds());
    // }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        if (meteoriteUsage != null) {
            g.drawImage(meteoriteUsage, 0, 0, getWidth(), getHeight(), this);
        }
    }

}
