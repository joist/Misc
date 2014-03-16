package library.shaders;




import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g3d.Renderable;
import com.badlogic.gdx.graphics.g3d.Shader;
import com.badlogic.gdx.graphics.g3d.utils.RenderContext;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;


public class NormalShader implements Shader {
	public ShaderProgram program;
	Camera camera;
	RenderContext context;
	public int u_projTrans;
	public int u_worldTrans;

	@Override
	public void init() {
		String vert = "attribute vec3 a_position;"+
				"attribute vec3 a_normal;"+
		 
		"uniform mat4 u_worldTrans;"+
		"uniform mat4 u_projTrans;"+

		"varying vec4 col;"+
		 
		"void main() {"+
			"   gl_Position = (u_projTrans * u_worldTrans) * vec4(a_position, 1.0);"+
			"   col = vec4(normalize(a_normal),1.0);"+
			"	col = u_worldTrans * col;"+
			"    col.xyz = 0.5 * (col.xyz + 1.0);"+
		"}";
		
		String frag = "varying vec4 col;"+

		"void main() {"+
		"gl_FragColor = col;"+
		//"gl_FragColor = vec4(0.5, 0.5, 0.5, 0.5);"+
		"}";
		
		ShaderProgram.pedantic = false;
        program = new ShaderProgram(vert, frag);
        if (!program.isCompiled())
            throw new GdxRuntimeException(program.getLog());
        u_projTrans = program.getUniformLocation("u_projTrans");
        u_worldTrans = program.getUniformLocation("u_worldTrans");
	}

	@Override
	public void dispose() {
		program.dispose();
	}

	@Override
	public void begin(Camera camera, RenderContext context) {
		this.camera = camera;
		this.context = context;
		program.begin();
		program.setUniformMatrix(u_projTrans, camera.combined);
		Gdx.gl20.glEnable(GL20.GL_DEPTH_TEST);
		context.setDepthTest(GL20.GL_LEQUAL);
		Gdx.gl20.glEnable(GL20.GL_CULL_FACE);
		context.setCullFace(GL20.GL_BACK);
	}

	@Override
	public void render(Renderable renderable) {
		program.setUniformMatrix(u_worldTrans, renderable.worldTransform);
		renderable.mesh.render(program,
				renderable.primitiveType,
				renderable.meshPartOffset,
				renderable.meshPartSize);
	}

	@Override
	public void end() {
		program.end();
	}

	@Override
	public int compareTo(Shader other) {
		return 0;
	}
	@Override
	public boolean canRender(Renderable instance) {
		return true;
	}
}
