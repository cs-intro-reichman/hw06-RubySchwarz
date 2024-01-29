import java.awt.Color;


public class Editor4 {

    public static void main (String[] args){
        String fileName = args[0];
        int steps = Integer.parseInt(args[1]);
        Color[][] colorImg = Instush.read(fileName);
        Color[][] colorGrey = Instush.greyscaled(Instush.read(fileName));
        Instush.morph(colorImg,colorGrey,steps);
    }
}
