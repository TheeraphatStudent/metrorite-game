package components;

import java.awt.Container;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import utils.PlaySounds;

public class MeteoriteThread extends Thread {
    private int countClicked = 0;
    private int parentWidth;
    private int parentHeight;

    // Ref
    private CreateMeteorite meteoriteRef;
    private List<CreateMeteorite> meteoriteContainRef;

    // Speed control
    private double speed = 1.5;
    private double MAX_SPEED = 10.0;
    private double angle;
    private double dx, dy;

    // Collision counts
    private int xCollisionCount = 0;
    private int yCollisionCount = 0;

    private int MAX_COLLISIONS = 7;

    public MeteoriteThread(
            CreateMeteorite meteorite,
            int frameWidth,
            int frameHeight,
            List<CreateMeteorite> meteoriteContain,
            LinkedHashMap<String, Double> globalOption
    ) {
        this.meteoriteRef = meteorite;
        meteoriteRef.setDoubleBuffered(true);

        this.parentWidth = frameWidth;
        this.parentHeight = frameHeight;
        this.meteoriteContainRef = meteoriteContain;

        this.speed = globalOption.get("Speed");

        this.angle = Math.random() * 2 * Math.PI;

        initialSpeed();
    }

    private void initialSpeed() {
        this.dx = (speed * Math.cos(angle));
        System.out.println("Dx: " + dx);
        
        this.dy = (speed * Math.sin(angle));
        System.out.println("Dy: " + dy);
    }

    // Collision Control
    private boolean checkCollision(JPanel meteoriteTarget) {
        return this.meteoriteRef.getBounds().intersects(meteoriteTarget.getBounds());
    }

    private void handleCollision(CreateMeteorite otherMeteorite) {
        double deltaX = meteoriteRef.getX() - otherMeteorite.getX();
        double deltaY = meteoriteRef.getY() - otherMeteorite.getY();

        // Angle Base
        double newAngle = Math.atan2(deltaY, deltaX);

        newAngle += (Math.random() - 0.5) * Math.PI / 4;
    
        // Update angle
        this.angle = newAngle;
    
        initialSpeed();
    }
    
    private void randomizeSpeed() {
        this.speed = this.speed + Math.random() * (MAX_SPEED);
        System.out.println("Current Speed: " + this.speed);
    }

    // Diagonal direction change
    private void changeToDiagonal() {
        this.angle = Math.PI / 4 + (Math.random() * Math.PI / 2);
        initialSpeed();

        xCollisionCount = 0;
        yCollisionCount = 0;
    }

    private boolean checkStatus() {
        if (meteoriteRef.getStatus()) {
            return true;
        }

        return false;
    }

    private void removeMeteoriteFromMeteoriteZone() {
        SwingUtilities.invokeLater(() -> {
            meteoriteContainRef.remove(meteoriteRef);
            Container parent = meteoriteRef.getParent();

            if (parent != null) {
                parent.remove(meteoriteRef);
                parent.revalidate();
                parent.repaint();
            }
        });
    }

    @Override
    public void run() {

        if (checkStatus()) {
            removeMeteoriteFromMeteoriteZone();

        }

        while (!checkStatus()) {
            try {
                int width = this.meteoriteRef.getWidth();
                int height = this.meteoriteRef.getHeight();

                int x = this.meteoriteRef.getX();
                int y = this.meteoriteRef.getY();

                // Update position
                x += dx;
                y += dy;

                for (CreateMeteorite getRef : new ArrayList<>(this.meteoriteContainRef)) {
                    if (getRef != this.meteoriteRef && checkCollision(getRef)) {
                        handleCollision(getRef);
                        break;

                    }
                }

                double randomizeSpeed = ((Math.random() * (this.MAX_SPEED - this.MAX_COLLISIONS)) - this.speed) + 0.2;
                System.out.println("Rand Speed: " + randomizeSpeed);

                // Reverse direction
                if (x + width > (parentWidth - (width / 2.75)) || x < 0) {
                    dx = -dx * randomizeSpeed;
                    this.xCollisionCount++;

                } else {
                    this.xCollisionCount = 0;
                }

                if (y + height > (parentHeight - (height / 2.5)) || y < 0) {
                    dy = -dy * randomizeSpeed;
                    this.yCollisionCount++;

                } else {
                    this.yCollisionCount = 0;

                }

                if (this.xCollisionCount >= MAX_COLLISIONS || this.yCollisionCount >= MAX_COLLISIONS) {
                    changeToDiagonal();
                    randomizeSpeed();
                    handleCollision(this.meteoriteRef);

                }

                // Update meteorite position
                this.meteoriteRef.setBounds(x, y, width, height);
                this.meteoriteRef.repaint();

                Thread.sleep(20);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
