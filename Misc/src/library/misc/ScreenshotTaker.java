package library.misc;

import java.awt.image.BufferedImage;
import java.io.File;
import java.nio.ByteBuffer;

import javax.imageio.ImageIO;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;

public class ScreenshotTaker
{
	public static int sessionUID = Math.abs((int)System.currentTimeMillis());
	public static int screenshotUID = 0;
	
	public static void simpleScreenshotGIF()
	{
		screenshotUID++;
		saveScreenshotGif(sessionUID+"_"+String.format("%05d", screenshotUID), 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	
	public static void simpleScreenshotPNG()
	{
		screenshotUID++;
		saveScreenshotPNG(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	}
	/*
	public static void saveScreenshot(FileHandle file) {
        Pixmap pixmap = getScreenshot(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        byte[] bytes;
        
        try {
                bytes = PNG.toPNG(pixmap, false);
        } catch (IOException e) {
                e.printStackTrace();
                return;
        }
        
        boolean append = false;
        file.writeBytes(bytes, append);
}
	*/

	public static void saveScreenshotPNG(int x, int y, int w, int h)
	{
		Pixmap pixmap = getScreenshot(x, y, w, h, true);
		int width = pixmap.getWidth();
		int height = pixmap.getHeight();
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for (int i=0;i<width;i++)
		{
			for (int j=0;j<height;j++)
			{
				bi.setRGB(i, j, 0xff000000 | (pixmap.getPixel(i,j)>>8));
			}
		}
		File outputfile = new File(System.currentTimeMillis()+".png");
		try {
			ImageIO.write(bi, "png", outputfile);
		} catch (Exception e) {}

	}
	
	public static void saveScreenshotGif(String name, int x, int y, int w, int h)
	{
		Pixmap pixmap = getScreenshot(x, y, w, h, true);
		int width = pixmap.getWidth();
		int height = pixmap.getHeight();
		BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
		
		for (int i=0;i<width;i++)
		{
			for (int j=0;j<height;j++)
			{
				bi.setRGB(i, j, 0xff000000 | (pixmap.getPixel(i,j)>>8));
			}
		}
		File outputfile = new File(name+".gif");
		try {
			ImageIO.write(bi, "gif", outputfile);
		} catch (Exception e) {}

	}
	
	public static void saveScreenshot(FileHandle file, int x, int y, int w, int h) {
        Pixmap pixmap = getScreenshot(x, y, w, h, true);
        PixmapIO.writePNG(file, pixmap);
        pixmap.dispose();
}

public static Pixmap getScreenshot(int x, int y, int w, int h, boolean flipY) {
        Gdx.gl.glPixelStorei(GL20.GL_PACK_ALIGNMENT, 1);
        
        final Pixmap pixmap = new Pixmap(w, h, Format.RGBA8888);
        ByteBuffer pixels = pixmap.getPixels();
        Gdx.gl.glReadPixels(x, y, w, h, GL20.GL_RGBA, GL20.GL_UNSIGNED_BYTE, pixels);
        
        final int numBytes = w * h * 4;
        byte[] lines = new byte[numBytes];
        if (flipY) {
                final int numBytesPerLine = w * 4;
                for (int i = 0; i < h; i++) {
                        pixels.position((h - i - 1) * numBytesPerLine);
                        pixels.get(lines, i * numBytesPerLine, numBytesPerLine);
                }
                pixels.clear();
                pixels.put(lines);
        } else {
                pixels.clear();
                pixels.get(lines);
        }
        
        return pixmap;
}
}
