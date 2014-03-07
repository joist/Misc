package temp;

import library.statestuff.StateHolder;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Misc";
		cfg.width = 640;
		cfg.height = 360;
		
		//new LwjglApplication(new MyGdxGame(), cfg);
		new LwjglApplication(new StateHolder(new tests.test02.State()), cfg);
	}
}
