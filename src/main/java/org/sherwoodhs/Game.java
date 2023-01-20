package org.sherwoodhs;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.util.Objects;

public interface Game {
    void start();
    void stop();
    String getName();
    String getDescription();
    default BufferedImage getThumbnail() {
        String s = Thread.currentThread().getStackTrace()[1].getClassName();
        final String path = "/default.png";
        System.out.println("Please override method getThumbnail() in Game. If this method is not overridden, the thumbnail returned by the game will be default.\n" + s + "\n");
        return checkThumbnail(path);
    }
    /*
     * Do not override the below method.
     */
    default BufferedImage checkThumbnail(String path) {
        BufferedImage thumbnail = null;
        try {
            thumbnail = ImageIO.read(Objects.requireNonNull(getClass().getResourceAsStream(path)));
        } catch (Exception e) {
            System.err.println("Thumbnail at path " + path + " could not be verified or doesn't exist.");
            System.exit(0);
        }
        if (thumbnail.getWidth() != 360 || thumbnail.getHeight() != 240)
            System.out.println("Thumbnail at path " + path + " is not 360px x 240px. The menu may not display as intended.");
        return thumbnail;
    }
}