package de.axelspringer.ideas.flipdots.magic;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

public class BigFontConverter {
    public static void main(String[] args) throws Exception {


//        BufferedImage bufferedImage = new BufferedImage(dimension, dimension, BufferedImage.TYPE_INT_ARGB);

        BufferedImage image = ImageIO.read(BigFontConverter.class.getClassLoader().getResourceAsStream("bigFont.png"));
// FIXME Remove System.out
        System.out.println(image.getWidth() + " x " + image.getHeight());

        char[] abc = "ABCDEFGHIJKLMNOPQRSTUVWXYZ abcdefghijklmnopqrstuvwxyz 0123456789".toCharArray();

        for (int i = 0; i < abc.length; i++) {
            char c = abc[i];

            BufferedImage subimage = image.getSubimage(i * 12, 0, 12, 14);

            // 12x14 Image to Upper Byte and Lower Byte
            int[] upper = new int[12];
            int[] lower = new int[12];

            for (int x = 0; x < 12; x++) {
                // 12 rows per CHAR with 2 Bytes
                int upperByte = 0;
                for (int y = 0; y < 7; y++) {
                    int rgb = subimage.getRGB(x, y);
                    int isSelected = rgb != -1 ? 1 : 0;
                    upperByte = upperByte | (isSelected << y);
                }

                int lowerByte = 0;
                for (int y = 7; y < 14; y++) {
                    int rgb = subimage.getRGB(x, y);
                    int isSelected = rgb != -1 ? 1 : 0;
                    lowerByte = lowerByte | (isSelected << (y - 7));
                }

                upper[x] = upperByte;
                lower[x] = lowerByte;
            }


            // FIXME Remove System.out
            System.out.println("bigFont.put('" + c + "', bigFontArray(intA(" + toText(upper) + "), intA(" + toText(lower) + ")));");

            ImageIO.write(subimage, "png", new File("/Users/swaschni/Projekte/ideas-flipdots/src/main/resources/abc/" + c + ".png"));
        }
    }

    private static String toText(int[] a) {
        String result = "";
        for (int i = 0; i < a.length; i++) {
            result += a[i];
            if (i != a.length - 1) {
                result += ",";
            }

        }
        return result;
    }

}
