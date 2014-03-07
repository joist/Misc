package library.statestuff;




import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;

public class StateHolder implements ApplicationListener {
	
	InputState inputstate;
	
	public AppState appstate;
	
	public StateHolder(AppState state)
	{
		if (state != null)
		{
			appstate = state;
		} else {
			appstate = new AppState();
		}
	}
	
	@Override
	public void create() {	
		appstate.create();
		inputstate = new InputState(0.3f);
		Gdx.input.setInputProcessor(inputstate);
	}

	@Override
	public void dispose() {
	}

	@Override
	public void render() {
		appstate.update(inputstate);
		appstate.render(inputstate);
		appstate.swapstate(this, inputstate);
		inputstate.flushPressedReleasedBuffers();
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
