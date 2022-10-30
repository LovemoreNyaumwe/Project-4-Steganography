
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.File;
//original example from
// w w w  . jav  a 2s  . com
import javax.imageio.ImageIO;
import java.lang.StringBuffer; 
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Queue;
import java.util.Iterator;
import java.lang.Integer;

public class ImageDetector {

    private static ArrayList<String> header = new ArrayList<String>();

    public static void main(String[] args) throws Exception {
        // BufferedImage img = colorImage(ImageIO.read(new File("hide_image.png")));
        // ImageIO.write(img, "png", new File("altered_java.png"));
        int[] sizeOfEncodedImage = getSizeEncodedImage(ImageIO.read(new File("hide_image.png")));
        System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        BufferedImage img = getHiddenImage(ImageIO.read(new File("hide_image.png")), sizeOfEncodedImage[1], sizeOfEncodedImage[0]);
        ImageIO.write(img, "png", new File("altered_java2.png"));
    }

    private static int[] getSizeEncodedImage(BufferedImage image){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < 1; yy++) {
            for (int xx = 0; xx < 22; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(buffer.length() < 64){
                    buffer.append(pixels[0] & 1);
                }

                if(buffer.length() < 64){
                    buffer.append(pixels[1] & 1);
                }

                if(buffer.length() < 64){
                    buffer.append(pixels[2] & 1);
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    private static BufferedImage getHiddenImage(BufferedImage image, int width, int height){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        StringBuffer pixelBuffer = new StringBuffer();
        StringBuffer buffer = new StringBuffer();

        for (int yy = 0; yy < originalHeight; yy++) {
            for (int xx = 0; xx < originalWidth; xx++) {
                int[] pixels = originalraster.getPixel(xx, yy, (int[]) null);
                buffer.append(pixels[0] & 1);
                buffer.append(pixels[1] & 1);
                buffer.append(pixels[2] & 1);
            }
        }

        buffer.delete(0, 64);

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster newraster = img.getRaster();

        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = newraster.getPixel(xx, yy, (int[]) null);

                pixels[0] = Integer.parseInt(buffer.substring(0, 8), 2);
                buffer.delete(0, 8);

                pixels[1] = Integer.parseInt(buffer.substring(0, 8), 2);
                buffer.delete(0, 8);

                pixels[2] = Integer.parseInt(buffer.substring(0, 8), 2);
                buffer.delete(0, 8);
                
                newraster.setPixel(xx, yy, pixels);
            }
        }

        return img;
    }

    private static BufferedImage colorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();

        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = 0;
                pixels[1] = pixels[1];
                pixels[2] = 128;
                System.out.print((pixels[0] & 1) + "" +  (pixels[1] & 1) + "" +  (pixels[2] & 1) + "|");
                raster.setPixel(xx, yy, pixels);
            }
        }
        System.out.println();
        System.out.println(header.get(0));
        System.out.println(header.get(1));
        System.out.println("width: " + Integer.parseInt(header.get(0), 2));
        System.out.println("height: " + Integer.parseInt(header.get(1), 2));
        return null;
    }
}