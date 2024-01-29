import java.awt.Color;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Runigram {

    public static Color[][] read(String filename) {
        // Existing implementation for reading a PPM file
    }

    public static void print(Color[][] image) {
        // Existing implementation for printing image details
    }

    public static Color[][] flippedHorizontally(Color[][] image) {
        // Implementation of horizontal flipping
    }

    public static Color[][] flippedVertically(Color[][] image) {
        // Implementation of vertical flipping
    }

    public static Color[][] grayScaled(Color[][] image) {
        // Implementation of grayscale conversion
    }

    public static Color luminance(Color color) {
        // Implementation of luminance calculation
    }

    public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
        // Implementation of blending two images
    }

    public static Color[][] scaled(Color[][] image, int newWidth, int newHeight) {
        int width = image[0].length;
        int height = image.length;
        Color[][] newImage = new Color[newHeight][newWidth];

        for (int y = 0; y < newHeight; y++) {
            for (int x = 0; x < newWidth; x++) {
                int srcX = x * width / newWidth;
                int srcY = y * height / newHeight;
                newImage[y][x] = image[srcY][srcX];
            }
        }

        return newImage;
    }

    public static void main(String[] args) {
        // Testing the functions as per your requirements
    }
}
