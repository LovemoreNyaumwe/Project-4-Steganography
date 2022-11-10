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

        BufferedImage imgToSee = getInfo(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        ImageIO.write(imgToSee, "png", new File(args[0].substring(0, args[0].length() - 4) + "" + args[1] + ".png"));

        // BufferedImage seeRed = getInfoFromRed(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        // ImageIO.write(seeRed, "png", new File(args[0].substring(0, args[0].length() - 4) + "red" + args[1] + ".png"));

        // BufferedImage seeGreen = getInfoFromGreen(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        // ImageIO.write(seeGreen, "png", new File(args[0].substring(0, args[0].length() - 4) + "green" + args[1] + ".png"));

        // BufferedImage seeBlue = getInfoFromBlue(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        // ImageIO.write(seeBlue, "png", new File(args[0].substring(0, args[0].length() - 4) + "blue" + args[1] + ".png"));

        // BufferedImage seeAlpha = getInfoFromAlpha(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        // ImageIO.write(seeAlpha, "png", new File(args[0].substring(0, args[0].length() - 4) + "alpha" + args[1] + ".png"));

        // BufferedImage seeAll = getInfoFromAll(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        // ImageIO.write(seeAll, "png", new File(args[0].substring(0, args[0].length() - 4) + "all" + args[1] + ".png"));
        // int[] sizeOfEncodedImage = null;
        // try{
        //     sizeOfEncodedImage = getSizeEncodedImage(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from all");
        // }

        // // boolean validSize = true;
        // // int[] sizeOfEncodedImageFromRed = null, sizeOfEncodedImageFromGreen = null, sizeOfEncodedImageFromBlue = null;

        // try{
        //     sizeOfEncodedImage = getSizeEncodedImageFromRed(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImageFromRed: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from red");
        // }

        // try{
        //     sizeOfEncodedImage = getSizeEncodedImageFromGreen(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImageFromGreen: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from green");
        // }

        // try{
        //     sizeOfEncodedImage = getSizeEncodedImageFromBlue(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImageFromBlue: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from blue");
        // }

        // try{
        //     sizeOfEncodedImage = getSizeEncodedImageFromAlpha(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImageFromAlpha: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from alpha");
        // }

        // try{
        //     sizeOfEncodedImage = getSizeEncodedImageFromAll(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImageFromAll: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from all");
        // }

        
        // try{
        //     int[] sizeOfEncodedImage = getSizeEncodedImageFromMoreThanOneBit(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from more than one bit all");
        // }

        // try{
        //     int[] sizeOfEncodedImage = getSizeEncodedImageFromMoreThanOneBitRed(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from more than one bit all red");
        // }

        // try{
        //     int[] sizeOfEncodedImage = getSizeEncodedImageFromMoreThanOneBitGreen(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from more than one bit all green");
        // }

        // try{
        //     int[] sizeOfEncodedImage = getSizeEncodedImageFromMoreThanOneBitBlue(ImageIO.read(new File(args[0])), Integer.parseInt(args[1]));
        //     System.out.println("sizeOfEncodedImage: " + sizeOfEncodedImage[0] + " " + sizeOfEncodedImage[1]);
        // } catch (Exception e) {
        //     System.out.println("Didn't get a valid size from more than one bit all blue");
        // }
        
        // BufferedImage img2 = getHiddenImageWithFlip1(ImageIO.read(new File(args[0])), sizeOfEncodedImage[0], sizeOfEncodedImage[1], Integer.parseInt(args[1]));
        // ImageIO.write(img2, "png", new File(args[0].substring(0, args[0].length() - 4) + "h" + args[1] + ".png"));

        // BufferedImage img2MoreThan1Bit = getHiddenImageFromAllMoreThanOneBit(ImageIO.read(new File(args[0])), sizeOfEncodedImage[1], sizeOfEncodedImage[0], Integer.parseInt(args[1]));
        // ImageIO.write(img2MoreThan1Bit, "png", new File(args[0].substring(0, args[0].length() - 4) + "hall" + args[1] + ".png"));

        // BufferedImage imgFromRed2 = getHiddenImageFromRedWithFlip(ImageIO.read(new File(args[0])), sizeOfEncodedImage[1], sizeOfEncodedImage[0], Integer.parseInt(args[1]));
        // ImageIO.write(imgFromRed2, "png", new File(args[0].substring(0, args[0].length() - 4) + "hred" + args[1] + ".png"));

        // BufferedImage imgFromGreen2 = getHiddenImageFromGreenWithFlip(ImageIO.read(new File(args[0])), sizeOfEncodedImage[1], sizeOfEncodedImage[0], Integer.parseInt(args[1]));
        // ImageIO.write(imgFromGreen2, "png", new File(args[0].substring(0, args[0].length() - 4) + "hgreen" + args[1] + ".png"));

        // BufferedImage imgFromBlue2 = getHiddenImageFromBlueWithFlip(ImageIO.read(new File(args[0])), sizeOfEncodedImage[1], sizeOfEncodedImage[0], Integer.parseInt(args[1]));
        // ImageIO.write(imgFromBlue2, "png", new File(args[0].substring(0, args[0].length() - 4) + "hblue" + args[1] + ".png"));

        // BufferedImage imgFromAll1 = getHiddenImageFromAllWithFlip2(ImageIO.read(new File(args[0])), sizeOfEncodedImage[0], sizeOfEncodedImage[1], Integer.parseInt(args[1]));
        // ImageIO.write(imgFromAll1, "png", new File(args[0].substring(0, args[0].length() - 4) + "hall" + args[1] + ".png"));  



    }

    public static BufferedImage getInfo(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = signExtendBit(pixels1[0], bit);
                pixels2[1] = signExtendBit(pixels1[1], bit);
                pixels2[2] = signExtendBit(pixels1[2], bit);
                if(pixels1.length == 4){
                    pixels2[3] = signExtendBit(pixels1[3], bit);
                }
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }


    public static BufferedImage getInfoFromRed(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = signExtendBit(pixels1[0], bit);
                pixels2[1] = pixels1[1];
                pixels2[2] = pixels1[2];
                if(pixels1.length == 4){
                    pixels2[3] = pixels1[3];
                }
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }

    public static BufferedImage getInfoFromGreen(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = pixels1[0];
                pixels2[1] = signExtendBit(pixels1[1], bit);
                pixels2[2] = pixels1[2];
                if(pixels1.length == 4){
                    pixels2[3] = pixels1[3];
                }
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }

    public static BufferedImage getInfoFromBlue(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = pixels1[0];
                pixels2[1] = pixels1[1];
                pixels2[2] = signExtendBit(pixels1[2], bit);
                if(pixels1.length == 4){
                    pixels2[3] = pixels1[3];
                }
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }

    public static BufferedImage getInfoFromAlpha(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = pixels1[0];
                pixels2[1] = pixels1[1];
                pixels2[2] = pixels1[2];
                if(pixels1.length == 4){
                    pixels2[3] = signExtendBit(pixels1[3], bit);
                }
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }


    public static BufferedImage getInfoFromAll(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();
        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels1 = raster.getPixel(xx, yy, (int[]) null);
                int[] pixels2 = newraster.getPixel(xx, yy, (int[]) null);
                pixels2[0] = signExtendBit(pixels1[0], bit);
                pixels2[1] = signExtendBit(pixels1[1], bit);
                pixels2[2] = signExtendBit(pixels1[2], bit);
                if(pixels1.length == 4){
                    pixels2[3] = signExtendBit(pixels1[3], bit);
                }
                int[] rgbPixels = new int[]{pixels2[0], pixels2[1], pixels2[2]};
                newraster.setPixel(xx, yy, pixels2);
            }
        }
        return img;
    }


















    public static int[] getSizeEncodedImage(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[0], bit));
                }

                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[1], bit));
                }

                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[2], bit));
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromMoreThanOneBit(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for(int i = 0; i <= bit; i += 1){
                    if(buffer.length() < 64){
                        buffer.append(getBit(pixels[0], i));
                    }
                }
                
                for(int i = 0; i <= bit; i += 1){
                    if(buffer.length() < 64){
                        buffer.append(getBit(pixels[1], i));
                    }
                }

                for(int i = 0; i <= bit; i += 1){
                    if(buffer.length() < 64){
                        buffer.append(getBit(pixels[2], i));
                    }
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }


    public static int[] getSizeEncodedImageFromRed(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[0], bit));
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromMoreThanOneBitRed(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for(int i = 0; i <= bit; i += 1){
                    if(buffer.length() < 64){
                        buffer.append(getBit(pixels[0], i));
                    }
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromMoreThanOneBitGreen(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for(int i = 0; i <= bit; i += 1){
                    if(buffer.length() < 64){
                        buffer.append(getBit(pixels[1], i));
                    }
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromMoreThanOneBitBlue(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                for(int i = 0; i <= bit; i += 1){
                    if(buffer.length() < 64){
                        buffer.append(getBit(pixels[2], i));
                    }
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromGreen(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[1], bit));
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromBlue(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[2], bit));
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }


    public static int[] getSizeEncodedImageFromAlpha(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(pixels.length == 4 && buffer.length() < 64){
                    buffer.append(getBit(pixels[3], bit));
                }
            }
        }

        int[] size = new int[2];
        size[0] = Integer.parseInt(buffer.substring(0, 32), 2);
        size[1] = Integer.parseInt(buffer.substring(32), 2);
        return size;
    }

    public static int[] getSizeEncodedImageFromAll(BufferedImage image, int bit){
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        StringBuffer buffer = new StringBuffer();
        int count = 0;
        for (int yy = 0; yy < height; yy++) {
            for (int xx = 0; xx < width; xx++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[0], bit));
                }

                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[1], bit));
                }

                if(buffer.length() < 64){
                    buffer.append(getBit(pixels[2], bit));
                }

                if(pixels.length == 4 && buffer.length() < 64){
                    buffer.append(getBit(pixels[3], bit));
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

                    // if(i < 7 && pixels.length == 4){
                    //     bufferFileWriter.write(getBit(pixels[3], bit) + "");
                    //     i += 1;
                    // } else if (pixels.length == 4) {
                    //     i = 0;
                    //     bufferFileWriter.write(getBit(pixels[3], bit) + "\n");
                    // }
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

    public static BufferedImage getHiddenImageFromRed(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("redBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("redBuffer.txt");
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
                }
            }
            bufferFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        BufferedImage img = new BufferedImage(width, height, image.getType());
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

    public static BufferedImage getHiddenImageFromGreen(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
            int i = 0;
            for (int yy = 0; yy < originalHeight; yy++) {
                for (int xx = 0; xx < originalWidth; xx++) {
                    int[] pixels = originalraster.getPixel(xx, yy, (int[]) null);
                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[1], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[1], bit) + "\n");
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































    public static BufferedImage getHiddenImageWithFlip1(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
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
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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



    public static BufferedImage getHiddenImageWithFlip2(BufferedImage image, int width, int height, int bit){
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
            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int[] pixels = newraster.getPixel(xx, yy, (int[]) null);
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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





    public static BufferedImage getHiddenImageFromGreenWithFlip(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
            int i = 0;
            for (int yy = 0; yy < originalHeight; yy++) {
                for (int xx = 0; xx < originalWidth; xx++) {
                    int[] pixels = originalraster.getPixel(xx, yy, (int[]) null);
                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[1], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[1], bit) + "\n");
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
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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



    public static BufferedImage getHiddenImageFromRedWithFlip(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
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
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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


    public static BufferedImage getHiddenImageFromBlueWithFlip(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
            int i = 0;
            for (int yy = 0; yy < originalHeight; yy++) {
                for (int xx = 0; xx < originalWidth; xx++) {
                    int[] pixels = originalraster.getPixel(xx, yy, (int[]) null);
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
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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

    public static BufferedImage getHiddenImageFromAllWithFlip1(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
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


                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[3], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[3], bit) + "\n");
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
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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



    public static BufferedImage getHiddenImageFromAllWithFlip2(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("greenBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("greenBuffer.txt");
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


                    if(i < 7){
                        bufferFileWriter.write(getBit(pixels[3], bit) + "");
                        i += 1;
                    } else {
                        i = 0;
                        bufferFileWriter.write(getBit(pixels[3], bit) + "\n");
                    }
                }
            }
            bufferFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        WritableRaster newraster = img.getRaster();

        try {
            Scanner scn = new Scanner(bufferFile);
            for(int i = 0; i < 8; i +=1){
                scn.next();
            }
            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int[] pixels = newraster.getPixel(xx, yy, (int[]) null);
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[3] = Integer.parseInt(flipByte(scn.next()), 2);
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


    public static BufferedImage getHiddenImageFromBlue(BufferedImage image, int width, int height, int bit){
        int originalWidth = image.getWidth();
        int originalHeight = image.getHeight();
        WritableRaster originalraster = image.getRaster();
        File bufferFile = null;

        try{
            bufferFile = new File("blueBuffer.txt");
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
            FileWriter bufferFileWriter = new FileWriter("blueBuffer.txt");
            int i = 0;
            for (int yy = 0; yy < originalHeight; yy++) {
                for (int xx = 0; xx < originalWidth; xx++) {
                    int[] pixels = originalraster.getPixel(xx, yy, (int[]) null);
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

        BufferedImage img = new BufferedImage(width, height, image.getType());
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


    public static BufferedImage getHiddenImageFromAllMoreThanOneBit(BufferedImage image, int width, int height, int bit){
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
                    for(int k = bit; k >= 0; k -= 1){
                        if(i < 7){
                            bufferFileWriter.write(getBit(pixels[0], k) + "");
                            i += 1;
                        } else {
                            i = 0;
                            bufferFileWriter.write(getBit(pixels[0], k) + "\n");
                        }
                    }
                    
                    for(int k = bit; k >= 0; k -= 1){
                        if(i < 7){
                            bufferFileWriter.write(getBit(pixels[1], k) + "");
                            i += 1;
                        } else {
                            i = 0;
                            bufferFileWriter.write(getBit(pixels[1], k) + "\n");
                        }
                    }

                    for(int k = bit; k >= 0; k -= 1){
                        if(i < 7){
                            bufferFileWriter.write(getBit(pixels[2], k) + "");
                            i += 1;
                        } else {
                            i = 0;
                            bufferFileWriter.write(getBit(pixels[2], k) + "\n");
                        }
                    }
                }
            }
            bufferFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();

        try {
            Scanner scn = new Scanner(bufferFile);
            for(int i = 0; i < 8; i +=1){
                scn.next();
            }

            for (int yy = 0; yy < height; yy++) {
                for (int xx = 0; xx < width; xx++) {
                    int[] pixels = newraster.getPixel(xx, yy, (int[]) null);
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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


    public static BufferedImage getHiddenImageFromAllMoreThanOneBit2(BufferedImage image, int width, int height, int bit){
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
                    for(int k = bit; k >= 0; k -= 1){
                        if(i < 7){
                            bufferFileWriter.write(getBit(pixels[0], k) + "");
                            i += 1;
                        } else {
                            i = 0;
                            bufferFileWriter.write(getBit(pixels[0], k) + "\n");
                        }
                    }
                    
                    for(int k = bit; k >= 0; k -= 1){
                        if(i < 7){
                            bufferFileWriter.write(getBit(pixels[1], k) + "");
                            i += 1;
                        } else {
                            i = 0;
                            bufferFileWriter.write(getBit(pixels[1], k) + "\n");
                        }
                    }

                    for(int k = bit; k >= 0; k -= 1){
                        if(i < 7){
                            bufferFileWriter.write(getBit(pixels[2], k) + "");
                            i += 1;
                        } else {
                            i = 0;
                            bufferFileWriter.write(getBit(pixels[2], k) + "\n");
                        }
                    }
                }
            }
            bufferFileWriter.close();
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            System.exit(1);
        }

        BufferedImage img = new BufferedImage(width, height, image.getType());
        WritableRaster newraster = img.getRaster();

        try {
            Scanner scn = new Scanner(bufferFile);
            for(int i = 0; i < 8; i +=1){
                scn.next();
            }

            for (int xx = 0; xx < width; xx++) {
                for (int yy = 0; yy < height; yy++) {
                    int[] pixels = newraster.getPixel(xx, yy, (int[]) null);
                    pixels[0] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[1] = Integer.parseInt(flipByte(scn.next()), 2);
                    pixels[2] = Integer.parseInt(flipByte(scn.next()), 2);
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

    private static String flipByte(String x){
        StringBuffer reversedString = new StringBuffer();
        for(int i = x.length() - 1; i >= 0; i -= 1){
            reversedString.append(x.charAt(i));
        }
        return reversedString.toString();
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