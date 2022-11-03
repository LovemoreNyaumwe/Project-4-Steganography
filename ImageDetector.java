import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.FileNotFoundException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
//original example from
// w w w  . jav  a 2s  . com
import java.lang.StringBuffer; 
import java.lang.Integer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.BitSet;
import java.util.Queue;
import java.util.Iterator;
import java.util.Scanner;
import javax.imageio.ImageIO;

public class ImageDetector {

    public static void main(String[] args) throws Exception {
        
        int[] sizeOfEncodedImage = getSizeEncodedImage(ImageIO.read(new File(args[0])));
        System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        BufferedImage imgToSee = getInfo(ImageIO.read(new File(args[0])), 0);
        ImageIO.write(imgToSee, "png", new File("look0.png"));
        BufferedImage img = getHiddenImage(ImageIO.read(new File(args[0])), sizeOfEncodedImage[1], sizeOfEncodedImage[0], 0);
        ImageIO.write(img, "png", new File("hiddenImage.png"));

    }

    public static void get4PixelDistribution(BufferedImage image, int bit){
        File file = null;
        ArrayList<int[]> distribution = new ArrayList<int[]>();
        try{
            file = new File("p.txt");
            if(file.createNewFile()){
                System.out.println("File created: " + file.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }


        try {
            Scanner scn = new Scanner(file);
            int i = 0;
            while(scn.hasNextLine()){
                System.out.println("i: " + i + " " + scn.nextLine());
                i += 1;
            }
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }


    }

    public static BufferedImage getInfo(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster newraster = img.getRaster();

        // int[] distribution = new int[8];
        // int[] d0 = new int[]{0, 0, 0};
        // int[] d1 = new int[]{0, 0, 255};
        // int[] d2 = new int[]{0, 255, 0};
        // int[] d3 = new int[]{0, 255, 255};
        // int[] d4 = new int[]{255, 0, 0};
        // int[] d5 = new int[]{255, 0, 255};
        // int[] d6 = new int[]{255, 255, 0};
        // int[] d7 = new int[]{255, 255, 255};
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = signExtendBit(pixels1[0], bit);
                pixels2[1] = signExtendBit(pixels1[1], bit);
                pixels2[2] = signExtendBit(pixels1[2], bit);
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                // if(Arrays.equals(rgbPixels, d0)){
                //     distribution[0] += 1;
                // } else if (Arrays.equals(rgbPixels, d1)){
                //     distribution[1] += 1;
                // } else if (Arrays.equals(rgbPixels, d2)){
                //     distribution[2] += 1;
                // } else if (Arrays.equals(rgbPixels, d3)){
                //     distribution[3] += 1;
                // } else if (Arrays.equals(rgbPixels, d4)){
                //     distribution[4] += 1;
                // } else if (Arrays.equals(rgbPixels, d5)){
                //     distribution[5] += 1;
                // } else if (Arrays.equals(rgbPixels, d6)){
                //     distribution[6] += 1;
                // } else if (Arrays.equals(rgbPixels, d7)){
                //     distribution[7] += 1;
                // }
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }

    public static int[] getSizeEncodedImage(BufferedImage image){
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

    public static BufferedImage getHiddenImage(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("buffer.txt");
            if(bufferFile.createNewFile()){
                System.out.println("File created: " + bufferFile.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        try {
            FileWriter bufferFileWriter = new FileWriter("buffer.txt");
            int i = 0;
            for (int yy = 0; yy < originalHeight; yy++) {
                for (int xx = 0; xx < originalWidth; xx++) {
                    int[] pixels = originalraster.getPixel(xx, yy, (int[]) null);
                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[0], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[0], bit) + "\n");
                    }

                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[1], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[1], bit) + "\n");
                    }

                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[2], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[2], bit) + "\n");
                    }
                }
            }
            bufferFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster newraster = img.getRaster();

        try {
            Scanner scn = new Scanner(bufferFile);
            for(int i = 0; i < 8; i +=1){
                scn.next();
            }
            for (int yy = 0; yy < height; yy++) {
                for (int xx = 0; xx < width; xx++) {
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

    private static int getBit(int x, int k){
        return (x >> k) & 0x1;
    }

    private static int signExtendBit(int x, int k){
        if(getBit(x, k) != 0){
            return 0xFF;
        }
        return 0;
    }

    private static void printBits(int x, int k){
        for(int i = 7; i >= 0; i -= 1){
            System.out.print(getBit(x, i));
        }
        System.out.println();
    }

    private static void makeHistogram(){
    }

    private static BufferedImage colorImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

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
        return null;
    }
}