package components;

import javax.swing.JPanel;

public class MeteoriteThread extends Thread {
    private int countClicked = 0;
    private JPanel meteoriteRef;
    private int parentWidth;
    private int parentHeight;

    // Speed control
    private double speed = 3.0;
    private double angle;
    private double dx, dy;

    public MeteoriteThread(JPanel meteorite, int frameWidth, int frameHeight) {
        this.meteoriteRef = meteorite;
        this.parentWidth = frameWidth;
        this.parentHeight = frameHeight;

        // Initialize random direction
        this.angle = Math.random() * 2 * Math.PI;
        updateVelocity();
    }

    private void updateVelocity() {
        this.dx = speed * Math.cos(angle);
        this.dy = speed * Math.sin(angle);
    }

    public void setSpeed(double newSpeed) {
        this.speed = newSpeed;
        updateVelocity();
    }

    public void changeDirection(double newAngle) {
        this.angle = newAngle;
        updateVelocity();
    }

    @Override
    public void run() {
        while (true) {
            try {
                int width = this.meteoriteRef.getWidth();
                int height = this.meteoriteRef.getHeight();

                int x = this.meteoriteRef.getX();
                int y = this.meteoriteRef.getY();

                // Update position
                x += dx;
                y += dy;

                // Reverse direction on edge collision
                if ((x + width > (parentWidth - (width / 2.75))) || (x < 0)) {
                    angle = Math.PI - angle;
                    updateVelocity();
                }

                if ((y + height > (parentHeight - (height / 2.4))) || (y < 0)) {
                    angle = -angle;
                    updateVelocity();
                }

                // Update meteorite position
                this.meteoriteRef.setBounds((int) x, (int) y, width, height);
                this.meteoriteRef.repaint();

                Thread.sleep(10);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}