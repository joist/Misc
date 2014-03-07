package library.statestuff;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.InputProcessor;

public class InputState implements InputProcessor{
	
	public boolean[] keysdown;
	public boolean mouserightdown;
	public boolean mouseleftdown;
	public float lx, ly;
	public float mx, my;
	public float dx, dy;
	public float mousses;
	
	public int[] justpressed = new int[30];
	public int justpressedcounter = 0;
	public int[] justreleased = new int[30];
	public int justreleasedcounter = 0;
	
	public InputState(float mousespeed)
	{
		keysdown = new boolean[256];
		for (int i=0; i<keysdown.length; i++)
		{
			keysdown[i] = false;
		}
		mouserightdown = false;
		mouseleftdown = false;
		lx = 0;
		ly = 0;
		mx = 0;
		my = 0;
		dx = 0;
		dy = 0;
		mousses = mousespeed;
	}
	
	public void flushPressedReleasedBuffers()
	{
		justpressedcounter = 0;
		justreleasedcounter = 0;
		dy = 0;
		dx = 0;
	}
	
	public boolean isJustPressed(int keycode)
	{
		if (justpressedcounter > 0)
		{
			for (int i=0;i<justpressedcounter;i++)
			{
				if (justpressed[i] == keycode)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isJustReleased(int keycode)
	{
		if (justreleasedcounter > 0)
		{
			for (int i=0;i<justreleasedcounter;i++)
			{
				if (justreleased[i] == keycode)
				{
					return true;
				}
			}
		}
		return false;
	}
	
	public boolean isKeyDown(int keycode)
	{
		return keysdown[keycode];
	}
	
	@Override
	public boolean keyDown(int keycode) {
		keysdown[keycode] = true;
		if (justpressedcounter < justpressed.length)
		{
			justpressed[justpressedcounter] = keycode;
			justpressedcounter++;
		}
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		keysdown[keycode] = false;
		if (justreleasedcounter < justreleased.length)
		{
			justreleased[justreleasedcounter] = keycode;
			justreleasedcounter++;
		}
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		
		Gdx.input.setCursorCatched(true);
		if (button == Buttons.LEFT) mouseleftdown = true;
		if (button == Buttons.RIGHT) mouserightdown = true;
		
		return true;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		if (button == Buttons.LEFT) mouseleftdown = false;
		if (button == Buttons.RIGHT) mouserightdown = false;
		return true;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		dx = Gdx.input.getDeltaX()*mousses;
		dy = Gdx.input.getDeltaY()*mousses;
		
		return true;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		dx = Gdx.input.getDeltaX()*mousses;
		dy = Gdx.input.getDeltaY()*mousses;
		
		return true;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
