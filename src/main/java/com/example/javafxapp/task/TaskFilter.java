package com.example.javafxapp.task;

import javafx.concurrent.Task;

import java.awt.*;
import java.awt.image.BufferedImage;


public class TaskFilter extends Task<BufferedImage> {

    private BufferedImage image;
    public TaskFilter(BufferedImage image) {
        this.image = image;
    }



    @Override
    protected BufferedImage call() throws Exception {



        for (int y = 0; y < image.getHeight(); y++) {


            for (int x = 0; x < image.getWidth(); x++) {

                Color color = new Color(image.getRGB(x, y));

                int brightnessFactor = 50;
                int red = color.getRed();
                int green = color.getGreen();
                int blue = color.getBlue();

                int newRed = red + brightnessFactor;
                int newGreen = green + brightnessFactor;
                int newBlue = blue + brightnessFactor;

                red = Math.min(255, red + brightnessFactor);
                green = Math.min(255, green + brightnessFactor);
                blue = Math.min(255, blue + brightnessFactor);

                Color newColor = new Color(red, green, blue);

                image.setRGB(x,y, newColor.getRGB());
            }
        }


        return image;
    }
}

