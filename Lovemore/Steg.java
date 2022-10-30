package Lovemore;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.Arrays;

public class Steg {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageIO.read(new File("Lovemore/sampleImages/hide_text.png"));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height: " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        StringBuilder header = new StringBuilder();
        StringBuilder message = new StringBuilder();
        processHeader(height, width, raster, header);
        int charCount = Integer.parseInt(header.toString(), 2);
        ++count;
        processMessage(height, width, raster, message);
        String hiddenText = binaryToText(message.substring(32));
        System.out.println(hiddenText.substring(0, charCount + 1));
    }

    public static void processMessage(int height, int width, WritableRaster raster, StringBuilder message) {
        for(int r = 0; r < height; ++r) {
            for(int c = 0; c < width; ++c) {
                int[] pixels = raster.getPixel(c, r, (int[])null);
                if (count > 32) {
                    message.append(pixels[0] & 1);
                    ++count;
                }

                if (count > 32) {
                    message.append(pixels[1] & 1);
                    ++count;
                }

                if (count > 32) {
                    message.append(pixels[2] & 1);
                    ++count;
                }
            }
        }

    }

    public static void processHeader(int height, int width, WritableRaster raster, StringBuilder header) {
        for(int r = 0; r < height; ++r) {
            for(int c = 0; c < width; ++c) {
                int[] pixels = raster.getPixel(c, r, (int[])null);
                if (count < 32) {
                    header.append(pixels[0] & 1);
                    ++count;
                }

                if (count < 32) {
                    header.append(pixels[1] & 1);
                    ++count;
                }

                if (count < 32) {
                    header.append(pixels[2] & 1);
                    ++count;
                }
            }
        }

    }

    public static String binaryToText(String binary) {
        return Arrays.stream(binary.split("(?<=\\G.{8})"))/* regex to split the bits array by 8*/
                .parallel()
                .map(eightBits -> (char)Integer.parseInt(eightBits, 2))
                .collect(
                        StringBuilder::new,
                        StringBuilder::append,
                        StringBuilder::append
                ).toString();
    }
}
