import java.awt.Color;

/**
 * Demonstrates the morphing operation featured by Runigram.java.
 * The program receives three command-line arguments: a string representing the name
 * of the PPM file of a source image, a string representing the name of the PPM file
 * of a target image, and the number of morphing steps (an int).
 * For example, to morph the cake into ironman in 50 steps, use:
 * java Editor3 cake.ppm ironman.ppm 50
 */
public class Editor3 {

    public static void main(String[] args) {
        // Getting command-line arguments
        String sourceFileName = args[0];
        String targetFileName = args[1];
        int steps = Integer.parseInt(args[2]);

        // Creating source and target images
        Color[][] sourceImage = Runigram.read(sourceFileName);
        Color[][] targetImage = Runigram.read(targetFileName);

        // Creating a canvas for displaying the images
        Runigram.setCanvas(sourceImage);

        // Performing the morphing operation
        Runigram.morph(sourceImage, targetImage, steps);
    }
}
