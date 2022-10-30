package Lovemore;

import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.util.stream.Stream;
import javax.imageio.ImageIO;

public class StegImage {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        BufferedImage image = ImageIO.read(new File("Lovemore/sampleImages/hide_image.png"));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height: " + height + " Width: " + width);
        WritableRaster raster = image.getRaster();
        StringBuilder header = new StringBuilder();
        StringBuilder message = new StringBuilder();
        processHeader(height, width, raster, header);
        ++count;
        processMessage(height, width, raster, message);
        String[] messages = message.toString().split("(?<=\\G.{8})");
        System.out.println(messages.length);
        int[] messagePixels = Stream.of(messages).mapToInt(Integer::parseInt).toArray();
        int charHeight = Integer.parseInt(header.substring(0, 32), 2);
        int charWidth = Integer.parseInt(header.substring(32), 2);
        System.out.println(charHeight + " x " + charWidth);
        BufferedImage img = colorImage(charWidth, charHeight, messagePixels);
        ImageIO.write(img, "png", new File("altered/new_altered_java.png"));
    }

    public static BufferedImage colorImage(int width, int height, int[] message) {
        BufferedImage image = new BufferedImage(width, height, 1);
        WritableRaster raster = image.getRaster();
        raster.setDataElements(0, 0, width, height, message);
        return image;
    }

    public static void processMessage(int height, int width, WritableRaster raster, StringBuilder message) {
        for(int r = 0; r < height; ++r) {
            for(int c = 0; c < width; ++c) {
                int[] pixels = raster.getPixel(c, r, (int[])null);
                if (count > 64) {
                    message.append(pixels[0] & 1);
                    ++count;
                }

                if (count > 64) {
                    message.append(pixels[1] & 1);
                    ++count;
                }

                if (count > 64) {
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
                if (count < 64) {
                    header.append(pixels[0] & 1);
                    ++count;
                }

                if (count < 64) {
                    header.append(pixels[1] & 1);
                    ++count;
                }

                if (count < 64) {
                    header.append(pixels[2] & 1);
                    ++count;
                }
            }
        }

    }
}
