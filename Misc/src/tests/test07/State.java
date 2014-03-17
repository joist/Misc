package tests.test07;

import library.misc.PixmapRenderer;
import library.misc.ScreenshotTaker;
import library.statestuff.AppState;
import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;

public class State extends AppState {
	
	PixmapRenderer pixmaprenderer;
	Pixmap pixmap;
	
	@Override
	public void create() {		
		pixmaprenderer = new PixmapRenderer();
		pixmaprenderer.scale = 2f;
		
		pixmap = new Pixmap(179,179,Format.RGBA8888);
		
		generatePerlin();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render(InputState inputstate) {	
		if (inputstate.isJustPressed(Keys.J)) generateJibRandom();
		if (inputstate.isJustPressed(Keys.K)) generateJavaRandom();
		if (inputstate.isJustPressed(Keys.L)) generatePerlin();
		if (inputstate.isJustPressed(Keys.P)) generateRidgedMulti();
		if (inputstate.isJustPressed(Keys.O)) generateBillow();
		if (inputstate.isJustPressed(Keys.I)) generateVoronoi();
		
		pixmaprenderer.updateView(inputstate);
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pixmaprenderer.render(pixmap);
		
		if (inputstate.isJustPressed(Keys.NUM_1)) ScreenshotTaker.simpleScreenshotPNG();
	}
	
	public void generateVoronoi()
	{
		int seed = Math.abs((int)System.currentTimeMillis());
		int val;
		jibnoise.module.Voronoi voronoi = new jibnoise.module.Voronoi();
		voronoi.SetSeed(seed);
		voronoi.SetFrequency(0.05d);
		int min = 255;
		int max = 0;
		
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				val = (int)((voronoi.GetValue(x, y, 0) + 1.5f)/3f * 240);
				pixmap.drawPixel(x, y, val<<24 | val<<16 | val<<8 | 0x000000ff);
				System.out.println("vale: "+val);
				if (val < min) min = val;
				if (val > max) max = val;
			}
		}
		System.out.println("min: "+min+", max: "+max);
	}
	
	public void generateBillow()
	{
		int seed = Math.abs((int)System.currentTimeMillis());
		int val;
		jibnoise.module.Billow billow = new jibnoise.module.Billow();
		billow.SetSeed(seed);
		billow.SetFrequency(0.05d);
		int min = 255;
		int max = 0;
		
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				val = (int)((billow.GetValue(x, y, 0) + 1.5f)/3f * 240);
				pixmap.drawPixel(x, y, val<<24 | val<<16 | val<<8 | 0x000000ff);
				System.out.println("vale: "+val);
				if (val < min) min = val;
				if (val > max) max = val;
			}
		}
		System.out.println("min: "+min+", max: "+max);
	}
	
	public void generateRidgedMulti()
	{
		int seed = Math.abs((int)System.currentTimeMillis());
		int val;
		jibnoise.module.RidgedMulti ridgedmulti = new jibnoise.module.RidgedMulti();
		ridgedmulti.SetSeed(seed);
		ridgedmulti.SetFrequency(0.05d);
		int min = 255;
		int max = 0;
		
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				val = (int)((ridgedmulti.GetValue(x, y, 0) + 1.5f)/3f * 240);
				pixmap.drawPixel(x, y, val<<24 | val<<16 | val<<8 | 0x000000ff);
				System.out.println("vale: "+val);
				if (val < min) min = val;
				if (val > max) max = val;
			}
		}
		System.out.println("min: "+min+", max: "+max);
	}
	
	public void generatePerlin()
	{
		int seed = Math.abs((int)System.currentTimeMillis());
		int val;
		jibnoise.module.Perlin perlin = new jibnoise.module.Perlin(seed);
		perlin.SetFrequency(0.05d);
		int min = 255;
		int max = 0;
		
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				val = (int)((perlin.GetValue(x, y, 0) + 1.5f)/3f * 240);
				pixmap.drawPixel(x, y, val<<24 | val<<16 | val<<8 | 0x000000ff);
				System.out.println("vale: "+val);
				if (val < min) min = val;
				if (val > max) max = val;
			}
		}
		System.out.println("min: "+min+", max: "+max);
	}
	
	public void generateJibRandom()
	{
		int seed = (int)System.currentTimeMillis();
		int val;
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				val = (int)((jibnoise.noise.ValueNoise3D(x, y, 0, seed)+1)/2 * 255);
				pixmap.drawPixel(x, y, val<<24 | val<<16 | val<<8 | 0x000000ff);
				System.out.println("vale: "+val);
			}
		}
	}
	
	public void generateJavaRandom()
	{
		int val;
		for (int x=0; x<pixmap.getWidth(); x++)
		{
			for (int y=0; y<pixmap.getHeight(); y++)
			{
				val = (int)(Math.random() * 255);
				pixmap.drawPixel(x, y, val<<24 | val<<16 | val<<8 | 0x000000ff);
				System.out.println("vale: "+val);
			}
		}
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
