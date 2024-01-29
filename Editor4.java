import java.awt.Color;

public class Editor4 {

    public static void main(String[] args) {
        // Extracting command-line arguments
        String fileName = args[0];
        int steps = Integer.parseInt(args[1]);

        // Reading the color image from the file
        Color[][] colorImg = Runigram.read(fileName);

        // Creating the greyscale version of the image
        Color[][] colorGrey = Runigram.greyscaled(Runigram.read(fileName));

        // Performing the morphing operation from color to greyscale
        Runigram.morph(colorImg, colorGrey, steps);
    }
}
