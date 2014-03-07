package library.misc;

import library.statestuff.InputState;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class PixmapRenderer {
	
	public float scale = 1;
	public float x = 0;
	public float y = 0;
	public float scrollspeed = 5f;
	public float speedmodifier;
	
	public void updateView(InputState inputstate)
	{
		if (inputstate.isKeyDown(Keys.SHIFT_LEFT)) speedmodifier = scale;
		else if (inputstate.isKeyDown(Keys.SHIFT_LEFT)) speedmodifier = 0.25f;
		else speedmodifier = 1;
			
		if (inputstate.isKeyDown(Keys.W)) y -= speedmodifier*scrollspeed/scale;
		if (inputstate.isKeyDown(Keys.S)) y += speedmodifier*scrollspeed/scale;

		if (inputstate.isKeyDown(Keys.A)) x += speedmodifier*scrollspeed/scale;
		if (inputstate.isKeyDown(Keys.D)) x -= speedmodifier*scrollspeed/scale;
		
		if (inputstate.isKeyDown(Keys.Z)) scale *= 1.01;
		if (inputstate.isKeyDown(Keys.X)) scale /= 1.01;
	}
	
	public void render(Pixmap pixmap)
	{
		renderPixmap(pixmap, x, y, scale);
	}
	
	public static void renderPixmap(Pixmap pixmap, float x, float y, float scale)
	{
		float width = Gdx.graphics.getWidth();
		float height = Gdx.graphics.getHeight();
		OrthographicCamera c = new OrthographicCamera(width, height);
		
		
		
		SpriteBatch sb = new SpriteBatch();
		Texture t = new Texture(pixmap);
		
		Sprite s = new Sprite(t);
		s.setPosition(x-s.getWidth()/2, y-s.getHeight()/2);
		
		sb.setProjectionMatrix(c.combined.scl(scale));
		sb.begin();
		s.draw(sb);
		sb.end();
		
		t.dispose();
		sb.dispose();
	}
}
