package tests.test03;

import library.misc.PixmapRenderer;
import library.misc.amitpMapConverter;
import library.statestuff.AppState;
import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;

public class State extends AppState {
	
	PixmapRenderer pixmaprenderer;
	Pixmap biomepixmap;
	Pixmap elevationpixmap;
	
	@Override
	public void create() {		
		pixmaprenderer = new PixmapRenderer();
		pixmaprenderer.scale = 2f;

		Pixmap p = new Pixmap(Gdx.files.internal("data/85882-8.biomes.png"));
		biomepixmap = amitpMapConverter.convert(p);

		p = new Pixmap(Gdx.files.internal("data/85882-8.elevation.png"));
		elevationpixmap = amitpMapConverter.convert(p);
		
		amitpMapConverter.swapGreenChannelToGreyscale(elevationpixmap);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render(InputState inputstate) {	
		
		pixmaprenderer.updateView(inputstate);
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if (inputstate.isKeyDown(Keys.SPACE)) pixmaprenderer.render(elevationpixmap);
		else pixmaprenderer.render(biomepixmap);
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
