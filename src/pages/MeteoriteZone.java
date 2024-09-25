package pages;

import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JPanel;
import components.CreateMeteorite;
import components.MeteoriteThread;
import utils.PlaySounds;

public class MeteoriteZone extends JPanel implements MouseListener, MouseMotionListener {
    // private int meteoriteNumbers = 3;

    // private List<MeteoriteThread> meteoriteControls = new ArrayList<>();
    private List<CreateMeteorite> meteoriteContain = new ArrayList<>();
    private MeteoriteThread thread;

    public MeteoriteZone(int parentWidth, int parentHeight, LinkedHashMap<String, Double> globalOption) {
        this.setLayout(null);
        this.setOpaque(false);
        this.setSize(new Dimension(parentWidth, parentHeight));


        int meteoriteNumbers = (int) Math.floor(globalOption.get("Meteorites"));

        for (int i = 0; i < meteoriteNumbers; i++) {
            CreateMeteorite meteorite = new CreateMeteorite();
            meteoriteContain.add(meteorite);

            int initialX = (int) (Math.random() * (parentWidth - meteorite.getWidth()));
            int initialY = (int) (Math.random() * (parentHeight - meteorite.getHeight()));
            meteorite.setBounds(initialX, initialY, meteorite.getWidth(), meteorite.getHeight());

            for (CreateMeteorite getMeteorite : meteoriteContain) {
                this.thread = new MeteoriteThread(getMeteorite, parentWidth, parentHeight, meteoriteContain,
                        globalOption);
            }

            this.thread.start();

            this.add(meteorite);
        }

        addMouseListener(this);
        addMouseMotionListener(this);
        repaint();
    }

    // Contain
    // public List<MeteoriteThread> getThreadContain() {
    // return this.meteoriteControls;

    // }

    public List<CreateMeteorite> getMeteoriteContain() {
        return this.meteoriteContain;

    }

    // @Override
    // public void paint(Graphics g) {

    // }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        new PlaySounds("laser-gun.wav");
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
