package pages;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import components.CreateMeteorite;
import components.MeteoriteThread;

public class MeteoriteZone extends JPanel implements MouseListener, MouseMotionListener {
    private int meteoriteNumbers = 10;

    private List<MeteoriteThread> meteoriteContain = new ArrayList<>();

    public MeteoriteZone(int parentWidth, int parentHeight) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setSize(new Dimension(parentWidth, parentHeight));

        for (int i = 0; i < meteoriteNumbers; i++) {
            CreateMeteorite meteorite = new CreateMeteorite();

            int initialX = (int) (Math.random() * (parentWidth - meteorite.getWidth()));
            int initialY = (int) (Math.random() * (parentHeight - meteorite.getHeight()));
            meteorite.setBounds(initialX, initialY, meteorite.getWidth(), meteorite.getHeight());

            MeteoriteThread meteoriteControl = new MeteoriteThread(meteorite, parentWidth, parentHeight);
            meteoriteContain.add(meteoriteControl);

            meteoriteControl.start();

            this.add(meteorite);
        }

        addMouseListener(this);
        addMouseMotionListener(this);
        repaint();
    }

    // @Override
    // public void paint(Graphics g) {

    // }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        // System.out.println("Mouse Move Work!");
        // System.out.println(e.getX());
        // System.out.println(e.getY());
        // System.err.println();

    }
}
