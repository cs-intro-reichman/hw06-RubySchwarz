import java.awt.Color;

/**
 * Demonstrates three basic image processing operations that are featured by Runigram.java.
 * The program receives two command-line arguments: a string representing the name of the PPM file
 * of a source image, and one of the strings "fh", "fv", or "gs". The program creates and displays
 * a new image which is either the horizontally flipped version of the source image ("fh"),
 * or the vertically flipped version of the source image ("fv"), or the grayscaled version of the
 * source image ("gs"). For example, to create a grayscale version of thor.ppm, use:
 * java Editor1 thor.ppm gs
 */
public class Editor1 {

    public static void main(String[] args) {
        String fileName = args[0];
        String action = args[1];

        // Reads the input image
        Color[][] imageIn = Runigram.read(fileName);
        Color[][] imageOut = null;

        // Applies the specified image processing function
        if (action.equals("fh")) {
            imageOut = Runigram.flipHorizontally(imageIn);
        } else if (action.equals("fv")) {
            imageOut = Runigram.flipVertically(imageIn);
        } else if (action.equals("gs")) {
            imageOut = Runigram.toGrayscale(imageIn);
        }

        // Display the output image
        Runigram.display(imageOut);
    }
}
