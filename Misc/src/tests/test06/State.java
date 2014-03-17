package tests.test06;

import library.misc.CameraMover;
import library.misc.ModelConverter;
import library.misc.ScreenshotTaker;
import library.shaders.NormalShader;
import library.statestuff.AppState;
import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
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
		
		shader = (Shader)(new NormalShader());
		shader.init();
		
		cameramover = new CameraMover();
		
		ObjLoader objLoader = new ObjLoader();
		model = objLoader.loadModel(Gdx.files.internal("data/icodec.obj"));
		modelinstance = new ModelInstance(model);
		
		Mesh mesh = new Mesh(true, 4, 6,
                new VertexAttribute(Usage.Position, 3, "a_position"),
                new VertexAttribute(Usage.Normal, 3, "a_normal"));

    	float[] verts = new float[4*6];
    	
    	verts[0]=0.5f;verts[1]=0;verts[2]=0.5f;verts[3]=0;verts[4]=1;verts[5]=0;
    	verts[6]=-0.5f;verts[7]=0;verts[8]=0.5f;verts[9]=0;verts[10]=1;verts[11]=0;
    	verts[12]=-0.5f;verts[13]=0;verts[14]=-0.5f;verts[15]=0;verts[16]=1;verts[17]=0;
    	verts[18]=0.5f;verts[19]=0;verts[20]=-0.5f;verts[21]=0;verts[22]=1;verts[23]=0;
    	
    	short[] indices = new short[2*3];
    	
    	indices[0]=0;indices[1]=1;indices[2]=2;
    	indices[3]=0;indices[4]=2;indices[5]=3;

    	mesh.setVertices(verts);
    	mesh.setIndices(indices);
    	
    	model = ModelConverter.convertMeshToModel("mesh", mesh);
    	modelinstance = new ModelInstance(model);
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
