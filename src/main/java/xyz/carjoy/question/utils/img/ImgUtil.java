package xyz.carjoy.question.utils.img;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.Transparency;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

import org.apache.commons.lang3.StringUtils;

public class ImgUtil {

    public static void zoomImageUtils(File imageFile, String newPath, BufferedImage bufferedImage, int width, int height)
            throws IOException {

        String suffix = StringUtils.substringAfterLast(imageFile.getName(), ".");
        // 处理 png 背景变黑的问题
        if (suffix != null
                && (suffix.trim().toLowerCase().endsWith("png") || suffix.trim().toLowerCase().endsWith("gif"))) {
            BufferedImage to = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            Graphics2D g2d = to.createGraphics();
            to = g2d.getDeviceConfiguration().createCompatibleImage(width, height, Transparency.TRANSLUCENT);
            g2d.dispose();

            g2d = to.createGraphics();
            Image from = bufferedImage.getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
            g2d.drawImage(from, 0, 0, null);
            g2d.dispose();
            ImageIO.write(to, suffix, new File(newPath));
        } else {
            BufferedImage newImage = new BufferedImage(width, height, bufferedImage.getType());
            Graphics g = newImage.getGraphics();
            g.drawImage(bufferedImage, 0, 0, width, height, null);
            g.dispose();
            ImageIO.write(newImage, suffix, new File(newPath));
        }
    }

    public static void zoomImage(File imageFile, String newPath, int width, int height) throws IOException {
        if (imageFile != null && !imageFile.canRead())
            return;
        Image src = Toolkit.getDefaultToolkit().getImage(imageFile.getPath());
        BufferedImage bufferedImage = toBufferedImage(src);
        if (null == bufferedImage)
            return;
        zoomImageUtils(imageFile, newPath, bufferedImage, width, height);
    }

    /**
     * 按指定高度 等比例缩放图片
     *
     * @param newPath
     * @param newWidth
     *            新图的宽度
     * @throws IOException
     */
    public static void zoomImageScale(String path, String newPath, int newWidth) throws IOException {
        File imageFile = new File(path);
        if (!imageFile.canRead())
            return;
        Image src = Toolkit.getDefaultToolkit().getImage(imageFile.getPath());
        BufferedImage bufferedImage = toBufferedImage(src);
        if (null == bufferedImage)
            return;
        int originalWidth = bufferedImage.getWidth();
        int originalHeight = bufferedImage.getHeight();
        double scale = (double) originalWidth / (double) newWidth; // 缩放的比例
        int newHeight = (int) (originalHeight / scale);
        zoomImageUtils(imageFile, newPath, bufferedImage, newWidth, newHeight);
    }

    public static BufferedImage toBufferedImage(Image image) {
        if (image instanceof BufferedImage) {
            return (BufferedImage) image;
        }
        image = new ImageIcon(image).getImage();
        BufferedImage bimage = null;
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        try {
            int transparency = Transparency.OPAQUE;
            GraphicsDevice gs = ge.getDefaultScreenDevice();
            GraphicsConfiguration gc = gs.getDefaultConfiguration();
            bimage = gc.createCompatibleImage(image.getWidth(null), image.getHeight(null), transparency);
        } catch (HeadlessException e) {
            // The system does not have a screen
        }
        if (bimage == null) {
            // Create a buffered image using the default color model
            int type = BufferedImage.TYPE_INT_RGB;
            bimage = new BufferedImage(image.getWidth(null), image.getHeight(null), type);
        }
        // Copy image to buffered image
        Graphics g = bimage.createGraphics();
        // Paint the image onto the buffered image
        g.drawImage(image, 0, 0, null);
        g.dispose();
        return bimage;
    }

    public static void main(String[] arg) {
        String filePath = "D:\\用户目录\\我的图片\\";
        String fileName = "1503563482643098540.jpg";
        File file = new File(filePath + fileName);
        try {
            //imgUtil.zoomImage(file, filePath + "small_" + fileName, 300, 300);
            zoomImageScale(filePath+fileName, filePath + "small_" + fileName, 400);
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}

