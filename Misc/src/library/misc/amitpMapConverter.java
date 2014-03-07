package library.misc;

import com.badlogic.gdx.graphics.Pixmap;

public class amitpMapConverter {

	public static Pixmap convert(Pixmap pixmap)
	{
		int size = ((pixmap.getWidth()/23))*2+1;
		System.out.println("size: " + size);
		
		Pixmap pixmapret = new Pixmap(size, size, pixmap.getFormat());
		pixmapret.setColor(0xff0ff0ff);
		pixmapret.fill();
		
		for (int x=0; x<size; x++)
		{
			for (int y=0; y<size; y++)
			{
				if (x % 2 == 0)
				{
					int xval = x * 23 + 12;
					int yval = y * 23 + 6;
					if (xval < pixmap.getWidth() && yval < pixmap.getHeight())
					{
						int col = pixmap.getPixel(xval, yval);
						pixmapret.drawPixel(x*2, y*2, col);
						pixmapret.drawPixel(x*2+1, y*2, col);
						pixmapret.drawPixel(x*2, y*2+1, col);
						pixmapret.drawPixel(x*2+1, y*2+1, col);
					}
				} else if (x % 2 == 1){
					int xval = x * 23 + 12;
					int yval = y * 23 + 19;
					if (xval < pixmap.getWidth() && yval < pixmap.getHeight())
					{
						int col = pixmap.getPixel(xval, yval);
						pixmapret.drawPixel(x*2, y*2+1, col);
						pixmapret.drawPixel(x*2+1, y*2+1, col);
						pixmapret.drawPixel(x*2, y*2+1+1, col);
						pixmapret.drawPixel(x*2+1, y*2+1+1, col);
					}
				}
			}
		}
		
		return pixmapret;
	}
	
	public static void swapGreenChannelToGreyscale(Pixmap pixmap)
	{
		float greenchanmax = 255;
		float greenchanmin = 120;
		float greenchanrange = greenchanmax - greenchanmin;
		
		float greyscalemax = 255;
		float greyscalemin = 0;
		float greyscalerange = greyscalemax - greyscalemin;
				
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				int greycol = pixmap.getPixel(x, y);
				greycol = (greycol & 0x00ff0000) >> 16;
				greycol = (int)((((greycol - greenchanmin) * greyscalerange)/greenchanrange)+greyscalemin);
				greycol = (greycol<<24)|(greycol<<16)|(greycol<<8)|0x000000ff;
				pixmap.drawPixel(x, y, greycol);
			}
		}
	}
}
