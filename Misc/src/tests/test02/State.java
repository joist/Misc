package tests.test02;

import library.misc.PixmapRenderer;
import library.statestuff.AppState;
import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;

public class State extends AppState {
	
	Pixmap pixmap;
	PixmapRenderer pixmaprenderer;
	
	@Override
	public void create() {		
		pixmap = new Pixmap(Gdx.files.internal("data/testpng.png"));
		pixmaprenderer = new PixmapRenderer();
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render(InputState inputstate) {	
		pixmaprenderer.updateView(inputstate);
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		pixmaprenderer.render(pixmap);
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
