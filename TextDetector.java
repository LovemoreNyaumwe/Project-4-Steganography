import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Objects;

public class TextDetector {
    static int count = 0;

    public static void main(String[] args) throws Exception {
        File dir = new File("FlippedTwoBits");
//        showFiles(dir.listFiles());
        for (File file : Objects.requireNonNull(dir.listFiles())) {
//            BufferedImage img = magnifyImage(ImageIO.read(new File(file.getAbsolutePath())));
//            ImageIO.write(img, "png", new File("Magnify/" + file.getName()));
            extractText(file.getAbsolutePath(), file.getName());
        }

//        BufferedImage img = magnifyImage(ImageIO.read(new File("hide_image.png")));
//        ImageIO.write(img, "png", new File("magnifiedImage.png"));


    }

    public static void extractText(String imageInput, String imageOutput) throws IOException {
        BufferedImage image = ImageIO.read(new File(imageInput));
        int width = image.getWidth();
        int height = image.getHeight();
        System.out.println("Height: " + height + " Width: " + width);

        WritableRaster raster = image.getRaster();
        StringBuilder header = new StringBuilder();
        StringBuilder message = new StringBuilder();

        processHeader(height, width, raster, header);
        ++count;
        processMessage(height, width, raster, message);

        //int charCount = Integer.parseInt(header.toString(), 2);
        //System.out.println(charCount);
        String hiddenText = binaryToText(message.substring(32));
        String[] imageInfo = imageOutput.split("\\.");
        PrintWriter writer = new PrintWriter("ExtractedText/" + "TBLRBlueFlippedTwoBitsBits" + imageInfo[0] + ".txt", "UTF-8");
        writer.println(hiddenText);
        writer.close();
    }

    public static BufferedImage magnifyImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = (pixels[0] & 1) * 0xFF;
                pixels[1] = (pixels[1] & 1) * 0xFF;
                pixels[2] = (pixels[2] & 1) * 0xFF;
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;

    }

    public static void processMessage(int height, int width, WritableRaster raster, StringBuilder message) {
        for(int r = 0; r < width; ++r) {
            for(int c = 0; c < height; ++c) {
                int[] pixels = raster.getPixel(r, c, (int[])null);
//                if (count > 32) {
//                    message.append(pixels[0] & 1);
//                    ++count;
//                }

//                if (count > 32) {
//                    message.append(pixels[1] & 1);
//                    ++count;
//                }
//
                if (count > 32) {
                    message.append(pixels[2] & 1);
                    ++count;
                }
            }
        }

    }

    public static void processHeader(int height, int width, WritableRaster raster, StringBuilder header) {
        for(int r = 0; r < width; ++r) {
            for(int c = 0; c < height; ++c) {
                int[] pixels = raster.getPixel(r, c, (int[])null);
//                if (count < 32) {
//                    header.append(pixels[0] & 1);
//                    ++count;
//                }

//                if (count < 32) {
//                    header.append(pixels[1] & 1);
//                    ++count;
//                }
//
                if (count < 32) {
                    header.append(pixels[2] & 1);
                    ++count;
                }
            }
        }

    }

    public static String binaryToText(String binary) {
        String[] binaryText = binary.split("(?<=\\G.{8})");
        StringBuilder text = new StringBuilder();
        for (String message: binaryText) {
            text.append((char)Integer.parseInt(message, 2));
        }
        return text.toString();
    }
}
