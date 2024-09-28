package components;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import utils.LoadedContent;
import utils.PlaySounds;

public class CreateMeteorite extends JPanel {
    private int clickCount = 0;
    private boolean isDestroy = false;

    private int WIDTH = 50;
    private int HEIGHT = 50;

    private int meteoriteImagesNumbers = 10;
    private Image meteoriteImage;
    // private Image bombMeteoriteImage = new LoadedContent().loadImage("bomb.gif");

    public CreateMeteorite() {
        this.setSize(new Dimension(WIDTH, HEIGHT));
        this.setPreferredSize(new Dimension(WIDTH, HEIGHT));
        // this.setBackground(Color.GRAY);
        this.setOpaque(false);

        int usage = (int) (Math.random() * meteoriteImagesNumbers) + 1;
        // this.meteoriteImage = new LoadedContent().loadImage(usage + ".png");
        this.meteoriteImage = new LoadedContent().loadImage(usage + ".png");

        this.setSize(new Dimension(WIDTH, HEIGHT));

        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                new PlaySounds("laser-gun.wav");

                clickCount++;
                System.out.println("Panel clicked " + clickCount + " times.");

            }
        });
    }

    // @Override
    // public Rectangle getBounds() {
    // // new Rectangle(this.getPositionX(), this.getPositionY(), 30, 20);
    // return new Rectangle(this.getX(), this.getY(), WIDTH + 10, HEIGHT + 10);

    // }

    // public boolean intersects(Enemy enemy) {
    // return getBounds().intersects(enemy.getBounds());

    // }

    private void destroyMeteorite() {
        this.isDestroy = true;

        // Remove Meteorite
        Timer timer = new Timer(1500, event -> {
            SwingUtilities.invokeLater(() -> {
                Container parent = getParent();
                if (parent != null) {
                    parent.remove(CreateMeteorite.this);
                    parent.revalidate();
                    parent.repaint();
                }
            });
        });

        timer.setRepeats(false);
        timer.start();

        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.red);
        g.drawRect(0, 0, getWidth() - 1, getHeight() - 1);

        if (this.meteoriteImage != null && this.clickCount < 2) {
            g.drawImage(this.meteoriteImage, 0, 0, getWidth(), getHeight(), this);

        } else {
            try {

                if (!this.isDestroy) {
                    new PlaySounds("bomb.wav");

                    g.drawImage(new LoadedContent().loadImage("bomb.gif"), 0, 0, getWidth(), getHeight(), this);
                    // Thread.sleep(500);
                    this.destroyMeteorite();

                    // g.dispose();

                }
            } catch (Exception e) {
                // TODO: handle exception
                System.out.println("Something went wrong!");
                System.err.println(e);
            }

        }
    }

    public boolean getStatus() {
        return this.isDestroy;

    }

}
