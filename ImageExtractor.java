import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Objects;
import java.util.Scanner;

public class ImageExtractor {
    static int count = 0;

    public static void main(String[] args) throws Exception {
//        File dir = new File("Images");
////        showFiles(dir.listFiles());
//        for (File file : Objects.requireNonNull(dir.listFiles())) {
//            BufferedImage img = extractImage(ImageIO.read(new File(file.getAbsolutePath())));
//            System.out.print(img == null);
//            System.out.println(file.getName());
//            if (img != null) {
//                ImageIO.write(img, "png", new File("ThreeBits/" + "ReverseTBLRNormal" + file.getName()));
//            }
//            count = 0;
////            extractText(file.getAbsolutePath(), file.getName());
//        }

//        BufferedImage img = magnifyImage(ImageIO.read(new File("hide_image.png")));
//        ImageIO.write(img, "png", new File("magnifiedImage.png"));

        BufferedImage img = extractImage(ImageIO.read(new File("/Users/lovemorenyaumwe/Desktop/security/Project-4-Steganography/Images/Lurking.png")));
        ImageIO.write(img, "png", new File("extractedThreeBitsLurking.png"));


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

    public static BufferedImage extractImage(BufferedImage image) throws FileNotFoundException, UnsupportedEncodingException {
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalRaster = image.getRaster();

        StringBuilder header = new StringBuilder();
        StringBuilder message = new StringBuilder();

        processImageHeader(originalHeight, originalWidth, originalRaster, header);
        ++count;
        processImageMessage(originalHeight, originalWidth, originalRaster, message);

        String [] imageInfo = header.toString().split("(?<=\\G.{32})");
        int newHeight = 0;
        int newWidth = 0;
        if (Integer.parseInt(String.valueOf(imageInfo[0].charAt(0))) != 1 &&
                Integer.parseInt(String.valueOf(imageInfo[1].charAt(0))) != 1) {
            newHeight = Integer.parseInt(imageInfo[0], 2);
            newWidth = Integer.parseInt(imageInfo[1], 2);
        } else return null;
        if (newHeight >= 2048 || newWidth >= 2048 || newHeight <= 0 || newWidth <= 0) {
            return null;
        }

        System.out.println("newHeight:" + newHeight + " newWidth:" + newWidth);

        PrintWriter writer = new PrintWriter("ExtractedImageMessage.txt", "UTF-8");
        String[] binaryText = message.toString().split("(?<=\\G.{8})");
        for (String messages: binaryText) {
            writer.println(messages);
        }
        writer.close();

        BufferedImage img = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_RGB);
        WritableRaster newraster = img.getRaster();

        try {
            File bufferFile = new File("ExtractedImageMessage.txt");
            Scanner scn = new Scanner(bufferFile);
            for(int i = 0; i < 8; i +=1){
                scn.next();
            }
            for (int yy = 0; yy < newHeight; yy++) {
                for (int xx = 0; xx < newWidth; xx++) {
                    int[] pixels = newraster.getPixel(xx, yy, (int[]) null);
                    pixels[0] = Integer.parseInt(scn.next(), 2);
                    pixels[1] = Integer.parseInt(scn.next(), 2);
                    pixels[2] = Integer.parseInt(scn.next(), 2);
                    newraster.setPixel(xx, yy, pixels);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        return img;

    }

    public static void processImageMessage(int height, int width, WritableRaster raster, StringBuilder message) {
        for(int r = 0; r < height; ++r) {
            for(int c = 0; c < width; ++c) {
                int[] pixels = raster.getPixel(c, r, (int[])null);
                if (count > 64) {
                    message.append((pixels[0] & 4)>>2);
                    ++count;
                }

                if (count > 64) {
                    message.append((pixels[1] & 4)>>2);
                    ++count;
                }

                if (count > 64) {
                    message.append((pixels[2] & 4)>>2);
                    ++count;
                }
            }
        }

    }

    public static void processImageHeader(int height, int width, WritableRaster raster, StringBuilder header) {
        for(int r = 0; r < height; ++r) {
            for(int c = 0; c < width; ++c) {
                int[] pixels = raster.getPixel(c, r, (int[])null);
                if (count < 64) {
                    header.append((pixels[0] & 4)>>2);
                    ++count;
                }

                if (count < 64) {
                    header.append((pixels[1] & 4)>>2);
                    ++count;
                }

                if (count < 64) {
                    header.append((pixels[2] & 4)>>2);
                    ++count;
                }
            }
        }

    }
}
