package utils;

import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;

import pages.Frame;

public class LoadedContent {
    public Image loadImage(String img) {
        Image backgroundImage = null;
        String imgPath = "resource/images/" + img;

        System.out.println("Image Path: " + imgPath);

        try (InputStream is = Frame.class.getClassLoader().getResourceAsStream(imgPath)) {
            if (is == null) {
                System.out.println("Image not found");
            } else {
                backgroundImage = ImageIO.read(is);
                return backgroundImage;
            }
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return backgroundImage;
    }
}
