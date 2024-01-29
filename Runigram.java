import java.awt.*;

/**
 * A library of image processing functions.
 */
public class Instush {
    
    public static void main(String[] args) {
        // Can be used for testing, as needed.
    //println(flippedHorizontally(read("/Users/tltnynyb/Desktop/instush/instush/src/images/tinypic.ppm")));
    //
        //println(greyscaled(read("/Users/tltnynyb/Desktop/instush/instush/src/images/tinypic.ppm")));
   // System.out.println(luminance(new Color(100,125,200)));
    //println(scaled(read("/Users/tltnynyb/Desktop/instush/instush/src/images/tinypic.ppm"),3,5));
     //println(show(read("/Users/tltnynyb/Desktop/instush/instush/src/images/ironman.ppm")));
        System.out.println(blend(new Color(100,40,100),new Color(200,20,40),0.25));
    }

    /**
     * Returns an image created from a given PPM file.
     * SIDE EFFECT: Sets standard input to the given file.
     * @return the image, as a 2D array of Color values
     */
    public static Color[][] read(String filename) {
        StdIn.setInput(filename);
        // Reads the PPM file header (ignoring some items)
        StdIn.readString();
        int numCols = StdIn.readInt();
        int numRows = StdIn.readInt();
        StdIn.readInt();
        // Creates the image
        Color[][] image = new Color[numRows][numCols];
        // Reads the RGB values from the file, into the image. 
        // For each pixel (i,j), reads 3 values from the file,
        // creates from the 3 colors a new Color object, and 
        // makes pixel (i,j) refer to that object.      
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                Color pixel = new Color(StdIn.readInt(),StdIn.readInt(),StdIn.readInt());
                image[i][j] = pixel;
            }
        }
        return image;
    }

    /**
     * Prints the pixels of a given image.
     * Each pixel is printed as a triplet of (r,g,b) values.
     * For debugging purposes.
     * @param image - the image to be printed
     */
    public static void println(Color[][] image) {
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                System.out.print("(");
                System.out.printf("%4s", image[i][j].getRed() + ",");
                System.out.printf("%4s", image[i][j].getGreen() + ",");
                System.out.printf("%3s", image[i][j].getBlue());
                System.out.print(") ");
            }
            System.out.println();
        }
    }
    
    /**
     * Returns an image which is the horizontally flipped version of the given image. 
     * @param image - the image to flip
     * @return the horizontally flipped image
     */
    public static Color[][] flippedHorizontally(Color[][] image) {
        Color[][] newImage = new Color[image.length][image[0].length];
        for (int i = 0; i < newImage.length; i++) {
            for (int j = 0; j < newImage[0].length; j++) {
                newImage[i][j] = image[i][image[i].length - j - 1];
            }
        }
        return newImage;
    }
    
    /**
     * Returns an image which is the vertically flipped version of the given image. 
     * @param image - the image to flip
     * @return the vertically flipped image
     */
    public static Color[][] flippedVertically(Color[][] image){
        Color[][] newImage = new Color[image.length][image[0].length];
        for (int i = 0; i < newImage.length; i++) {
            for (int j = 0; j < newImage[i].length; j++) {
                newImage[i][j] = image[image.length - i - 1][j];
            }
        }
        return newImage;
    }
    
    /**
     * Returns the average of the RGB values of all the pixels in a given image.
     * @param image - the image
     * @return the average of all the RGB values of the image
     */
    public static double average(Color[][] image) {
        int sumPixel = 0;
        for (int i = 0; i < image.length; i++) {
            for (int j = 0; j < image[i].length; j++) {
                sumPixel += image[i][j].getRed();
                sumPixel += image[i][j].getGreen();
                sumPixel += image[i][j].getBlue();
            }
        }
        double ans = (double) sumPixel / (image.length * image[0].length * 3);
        return ans;
    }

    /**
     * Returns the luminance value of a given pixel. Luminance is a weighted average
     * of the RGB values of the pixel, given by 0.299 * r + 0.587 * g + 0.114 * b.
     * Used as a shade of grey, as part of the greyscaling process.
     * @param pixel - the pixel
     * @return the greyscale value of the pixel, as a Color object
     *         (r = g = b = the greyscale value)
     */
    public static Color luminance(Color pixel) {
        int r = pixel.getRed();
        int g = pixel.getGreen();
        int b = pixel.getBlue();
        int lum = (int) (0.299 * r + 0.587 * g + 0.114 * b);
        Color luminance = new Color(lum,lum,lum);
        return luminance;
    }
    
    /**
     * Returns an image which is the greyscaled version of the given image.
     * @param image - the image
     * @return rhe greyscaled version of the image
     */
    public static Color[][] greyscaled(Color[][] image) {
        Color[][] newImage = new Color[image.length][image[0].length];
        for (int i = 0; i < newImage.length; i++) {
            for (int j = 0; j < newImage[i].length; j++) {
                newImage[i][j] = luminance(image[i][j]);
            }
        }
        return newImage;
    }   
    
    /**
     * Returns an umage which is the scaled version of the given image. 
     * The image is scaled (resized) to be of the given width and height.
     * @param image - the image
     * @param width - the width of the scaled image
     * @param height - the height of the scaled image
     * @return - the scaled image
     */
    public static Color[][] scaled(Color[][] image, int width, int height) {
        Color[][] newImage = new Color[height][width];
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                newImage[i][j] = image[(int) (i * (double)image.length / height)][(int) (j * (double)image[0].length / width)];
            }
            }
        return newImage;
    }
    
    /**
     * Returns a blended color which is the linear combination of two colors.
     * Each r, g, b, value v is calculated using v = (1 - alpha) * v1 + alpha * v2.
     * 
     * @param c1 - the first color
     * @param c2 - the second color
     * @param alpha - the linear combination parameter
     * @return the blended color
     */
    public static Color blend(Color c1, Color c2, double alpha) {
        int newRed = (int) ((alpha * c1.getRed()) + ((1 - alpha) * c2.getRed()));
        int newGreen = (int) ((alpha * c1.getGreen()) + ((1 - alpha) * c2.getGreen()));
        int newBlue = (int) ((alpha * c1.getBlue()) + ((1 - alpha) * c2.getBlue()));
        Color newColor = new Color(newRed,newGreen,newBlue);
        return newColor;
    }
    
    /**
     * Returns an image which is the blending of the two given images.
     * The blending is the linear combination of (1 - alpha) parts the
     * first image and (alpha) parts the second image.
     * The two images must have the same dimensions. 
     * @param image1 - the first image
     * @param image2 - the second image
     * @param alpha - the linear combination parameter
     * @return - the blended image
     */
    public static Color[][] blend(Color[][] image1, Color[][] image2, double alpha) {
        if (image1.length == image2.length && image1[0].length == image2[0].length) {
            Color[][] newBlend = new Color[image1.length][image1[0].length];
            for (int i = 0; i < newBlend.length; i++) {
                for (int j = 0; j < newBlend[i].length; j++) {
                    newBlend[i][j] = (blend(image1[i][j],image2[i][j],alpha));
                }
            }
            return newBlend;
        } else {
            scaled(image2,image1[0].length,image1.length);
                Color[][] newBlend = new Color[image1.length][image1[0].length];
                for (int i = 0; i < newBlend.length; i++) {
                    for (int j = 0; j < newBlend[i].length; j++) {
                        newBlend[i][j] = (blend(image1[i][j],image2[i][j],alpha));
                    }
                }
                return newBlend;
            }
    }
    
    /**
     * Morphs the source image into the target image, gradually, in n steps.
     * Animates the morphing process by displaying the morphed image in each step.
     * The target image is an image which is scaled to be a version of the target
     * image, scaled to have the width and height of the source image.
     * @param source - source image
     * @param target - target image
     * @param n - number of morphing steps
     */
    public static void morph(Color[][] source, Color[][] target, int n) {
        if (source.length == target.length && source[0].length == target[0].length){
            for (int i = 0; i <= n; i++) {
                double alpha = (double) (n - i)/n;
                Color[][] morph = blend(source,target,alpha);
                show(morph);
            }
        } else {
            target = scaled(target,source[0].length,source.length);
                for (int i = 0; i <= n; i++) {
                    double alpha = (double) (n - i)/n;
                    Color[][] morph = blend(source,target,alpha);
                    show(morph);
                }
            }
        }

    
     /**
     * Renders (displays) an image on the screen, using StdDraw.
     *
     * @param image - the image to show
     */
     public static void show(Color[][] image) {
         StdDraw.setCanvasSize(image[0].length, image.length);
         int width = image[0].length;
         int height = image.length;
         StdDraw.setXscale(0, width);
         StdDraw.setYscale(0, height);
         StdDraw.show(25);
         for (int i = 0; i < height; i++) {
             for (int j = 0; j < width; j++) {
                 // Sets the pen color to the color of the pixel
                 StdDraw.setPenColor( image[i][j].getRed(),
                         image[i][j].getGreen(),
                         image[i][j].getBlue() );
                 // Draws the pixel as a tiny filled square of size 1
                 StdDraw.filledSquare(j + 0.5, height - i - 0.5, 0.5);
             }
         }
         StdDraw.show();
     }
}

