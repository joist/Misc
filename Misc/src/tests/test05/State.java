package tests.test05;

import library.misc.CameraMover;
import library.misc.ScreenshotTaker;
import library.shaders.NormalShader;
import library.statestuff.AppState;
import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.loader.ObjLoader;

public class State extends AppState {
	
	public PerspectiveCamera camera;
	public ModelBatch modelbatch;
	public Model model;
	public ModelInstance modelinstance;
	public Shader shader;
	public CameraMover cameramover;
	
	@Override
	public void create() {		
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new PerspectiveCamera(90, w, h);
		camera.position.set(1, 0, 0);
		camera.up.set(0, 1, 0);
		camera.lookAt(0, 0, 0);
		camera.near = 0.1f;
		camera.far = 100f;
		camera.update();

		modelbatch = new ModelBatch();

		ObjLoader objLoader = new ObjLoader();
		model = objLoader.loadModel(Gdx.files.internal("data/icodec.obj"));
		modelinstance = new ModelInstance(model);
		
		shader = (Shader)(new NormalShader());
		shader.init();
		
		cameramover = new CameraMover();
	}

	@Override
	public void dispose() {
		model.dispose();
		modelbatch.dispose();
		shader.dispose();
	}

	@Override
	public void render(InputState inputstate) {	
		if (inputstate.mouseleftdown) Gdx.input.setCursorCatched(true);
		
		cameramover.move(inputstate);
		cameramover.setCam(camera);
		
		Gdx.gl.glClearColor(0.5f, 0.5f, 0.5f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);

		modelbatch.begin(camera);
		modelbatch.render(modelinstance, shader);
		modelbatch.end();
		modelinstance.transform.rotate(0, 1, -1, 360/90);
		
		if (inputstate.isKeyDown(Keys.NUM_1)) ScreenshotTaker.simpleScreenshotGIF();
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
