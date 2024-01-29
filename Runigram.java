import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runigram {

    public static Color[][] read(String filename) {
        // Implementation remains the same as in the previous snippet
    }

    public static void print(Color[][] image) {
        // Implementation remains the same as in the previous snippet
    }

    public static Color[][] flipHorizontally(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] flipped = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                flipped[i][j] = image[i][width - 1 - j];
            }
        }
        return flipped;
    }

    public static Color[][] flipVertically(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] flipped = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                flipped[i][j] = image[height - 1 - i][j];
            }
        }
        return flipped;
    }

    public static Color[][] greyscaled(Color[][] image) {
        int height = image.length;
        int width = image[0].length;
        Color[][] grey = new Color[height][width];

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                Color c = image[i][j];
                int greyValue = (c.getRed() + c.getGreen() + c.getBlue()) / 3;
                grey[i][j] = new Color(greyValue, greyValue, greyValue);
            }
        }
        return grey;
    }

    public static Color[][] scaled(Color[][] image, int newWidth, int newHeight) {
        // Implementation of image scaling
        // This can be a complex task and might require interpolation techniques
    }

    public static Color[][] morph(Color[][] startImage, Color[][] endImage, int steps) {
        // Implementation of image morphing
        // This involves a blend of startImage and endImage based on the step count
    }

    public static void main(String[] args) {
        // Testing the functions
        Color[][] tinypic = read("tinypic.ppm");
        print(tinypic);
        Color[][] flippedH = flipHorizontally(tinypic);
        Color[][] flippedV = flipVertically(tinypic);
        Color[][] grey = greyscaled(tinypic);
        // More tests for scaling and morphing
    }
}
