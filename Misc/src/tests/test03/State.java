package tests.test03;

import library.renderpixmap.PixmapRenderer;
import library.statestuff.AppState;
import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;

public class State extends AppState {
	
	Pixmap pixmap;
	PixmapRenderer pixmaprenderer;
	Pixmap pixmap2;
	
	@Override
	public void create() {		
		pixmap = new Pixmap(Gdx.files.internal("data/85882-8.biomes.png"));
		pixmaprenderer = new PixmapRenderer();
		
		pixmap2 = new Pixmap(256, 256, pixmap.getFormat());
		pixmap2.setColor(0xff000000);
		pixmap2.fill();
		
		for (int x=0; x<256; x++)
		{
			for (int y=0; y<256; y++)
			{
				if (x % 2 == 0)
				{
					int xval = x * 23 + 12;
					int yval = y * 23 + 6;
					if (xval < pixmap.getWidth() && yval < pixmap.getHeight())
					{
						int col = pixmap.getPixel(xval, yval);
						pixmap2.drawPixel(x*2, y*2, col);
						pixmap2.drawPixel(x*2+1, y*2, col);
						pixmap2.drawPixel(x*2, y*2+1, col);
						pixmap2.drawPixel(x*2+1, y*2+1, col);
					}
				} else if (x % 2 == 1){
					int xval = x * 23 + 12;
					int yval = y * 23 + 19;
					if (xval < pixmap.getWidth() && yval < pixmap.getHeight())
					{
						int col = pixmap.getPixel(xval, yval);
						pixmap2.drawPixel(x*2, y*2+1, col);
						pixmap2.drawPixel(x*2+1, y*2+1, col);
						pixmap2.drawPixel(x*2, y*2+1+1, col);
						pixmap2.drawPixel(x*2+1, y*2+1+1, col);
					}
				}
			}
		}
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render(InputState inputstate) {	
		
		pixmaprenderer.updateView(inputstate);
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pixmaprenderer.render(pixmap2);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
