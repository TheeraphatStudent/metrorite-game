package pages;

import java.awt.Dimension;
import javax.swing.JPanel;
import components.CreateMeteorite;

public class MeteoriteZone extends JPanel {
    private int meteoriteNumbers = 10;

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
            meteoriteControl.start();

            this.add(meteorite);
        }

        repaint();
    }
}

class MeteoriteThread extends Thread {
    private JPanel meteoriteRef;
    private int parentWidth;
    private int parentHeight;

    public MeteoriteThread(JPanel meteorite, int frameWidth, int frameHeight) {
        this.meteoriteRef = meteorite;
        this.parentWidth = frameWidth;
        this.parentHeight = frameHeight;
    }

    @Override
    public void run() {
        while (true) {
            try {
                int width = this.meteoriteRef.getWidth();
                int height = this.meteoriteRef.getHeight();

                int x = this.meteoriteRef.getX();
                int y = this.meteoriteRef.getY();

                // Movement speed
                x += (int) (Math.random() * 5);
                y += (int) (Math.random() * 5);

                // Wrap around the edges
                if (x > parentWidth) {
                    x = -width;
                }
                if (y > parentHeight) {
                    y = -height;
                }

                this.meteoriteRef.setBounds(x, y, width, height);
                this.meteoriteRef.repaint();

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
