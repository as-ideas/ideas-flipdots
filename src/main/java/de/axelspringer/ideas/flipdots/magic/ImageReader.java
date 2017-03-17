package de.axelspringer.ideas.flipdots.magic;

import de.axelspringer.ideas.flipdots.magic.frames.FlipdotFull2CFrame;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

public class ImageReader {

    public static FlipdotFull2CFrame readImage(String path) {
        try {
            BufferedImage image = ImageIO.read(ImageReader.class.getClassLoader().getResourceAsStream(path));
            if (image.getHeight() != 14) {
                throw new IllegalArgumentException("Height of image must be 14 but was " + image.getHeight());
            }
            if (image.getWidth() != 56) {
                throw new IllegalArgumentException("Width of image must be 56 but was " + image.getWidth());
            }
            FlipdotFull2CFrame result = new FlipdotFull2CFrame();

            for (int x = 0; x < 28; x++) {
                // 12 rows per CHAR with 2 Bytes
                int upperByte = 0;
                for (int y = 0; y < 7; y++) {
                    int rgb = image.getRGB(x, y);
                    int isSelected = rgb != -1 ? 1 : 0;
                    upperByte = upperByte | (isSelected << y);
                }

                int lowerByte = 0;
                for (int y = 7; y < 14; y++) {
                    int rgb = image.getRGB(x, y);
                    int isSelected = rgb != -1 ? 1 : 0;
                    lowerByte = lowerByte | (isSelected << (y - 7));
                }

                result.upperLeftFrame[x + 3] = upperByte;
                result.lowerLeftFrame[x + 3] = lowerByte;
            }


            for (int x = 28; x < 56; x++) {
                // 12 rows per CHAR with 2 Bytes
                int upperByte = 0;
                for (int y = 0; y < 7; y++) {
                    int rgb = image.getRGB(x, y);
                    int isSelected = rgb != -1 ? 1 : 0;
                    upperByte = upperByte | (isSelected << y);
                }

                int lowerByte = 0;
                for (int y = 7; y < 14; y++) {
                    int rgb = image.getRGB(x, y);
                    int isSelected = rgb != -1 ? 1 : 0;
                    lowerByte = lowerByte | (isSelected << (y - 7));
                }

                result.upperRightFrame[x - 28 + 3] = upperByte;
                result.lowerRightFrame[x - 28 + 3] = lowerByte;
            }

            return result;

            // FIXME Remove System.out
            //System.out.println("bigFont.put('" + c + "', bigFontArray(intA(" + toText(upper) + "), intA(" + toText(lower) + ")));");
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage(), e);
        }

    }
}
