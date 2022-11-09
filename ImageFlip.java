import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.awt.image.WritableRaster;
import java.io.*;
import java.util.Objects;

public class ImageFlip {


    public static void main(String[] args) throws Exception {
        File dir = new File("ThreeBits");
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            BufferedImage img = flipImage(ImageIO.read(new File(file.getAbsolutePath())));
            ImageIO.write(img, "png", new File("FlippedThreeBits/" + "ReverseThreeBits" + file.getName()));
        }
//        BufferedImage img = flipImageTBLR(ImageIO.read(new File("/Users/lovemorenyaumwe/Desktop/security/Project-4-Steganography/ExtractedImage/NormalGreenAlbumCover.png")));
//        ImageIO.write(img, "png", new File("FlippedImage/" + "NormalGreenAlbumCoverTBLR.png"));

//        BufferedImage img = flipImage(ImageIO.read(new File("/Users/lovemorenyaumwe/Desktop/security/Project-4-Steganography/extractedAlbumCoverImage.png")));
//        ImageIO.write(img, "png", new File("FlippedImage/" + "extractedAlbumCoverImage.png"));
    }

    private static BufferedImage flipImage(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();

        for (int xx = 0; xx < width; xx++) {
            for (int yy = 0; yy < height; yy++) {
                int[] pixels = raster.getPixel(xx, yy, (int[]) null);
                pixels[0] = reverseBits(Integer.toBinaryString(pixels[0]));
                pixels[1] = reverseBits(Integer.toBinaryString(pixels[1]));
                pixels[2] = reverseBits(Integer.toBinaryString(pixels[2]));
                raster.setPixel(xx, yy, pixels);
            }
        }
        return image;
    }

    private static BufferedImage flipImageTBLR(BufferedImage image) {
        int width = image.getWidth();
        int height = image.getHeight();
        WritableRaster raster = image.getRaster();
        BufferedImage newImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        WritableRaster newRaster = newImage.getRaster();

        for (int xx = 0; xx < height; xx++) {
            for (int yy = 0; yy < width; yy++) {
                int[] pixels = raster.getPixel(yy, xx, (int[]) null);
                int[] newPixels = newRaster.getPixel(yy, xx, (int[]) null);
                newPixels[0] = reverseBits(Integer.toBinaryString(pixels[0]));
                newPixels[1] = reverseBits(Integer.toBinaryString(pixels[1]));
                newPixels[2] = reverseBits(Integer.toBinaryString(pixels[2]));
                newRaster.setPixel(yy, xx, newPixels);
            }
        }
        return newImage;
    }

    public static int reverseBits(String bits) {
        bits = String.format("%8s", bits).replace(' ', '0');
        StringBuilder str = new StringBuilder();
        for (int i = bits.length() - 1; i >= 0; i--) {
            str.append(bits.charAt(i));
        }
        String reversedBits = String.format("%8s", str.toString()).replace(' ', '0');
        return Integer.parseInt(reversedBits,2);
    }

}
