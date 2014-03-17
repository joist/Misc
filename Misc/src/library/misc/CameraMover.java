package library.misc;

import library.statestuff.InputState;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.math.Vector3;

public class CameraMover {
	
	public float rotateangle = 0;
	public float updownangle = 0;
	
	public boolean backdown = false;
	public boolean forwarddown = false;
	public boolean leftdown = false;
	public boolean rightdown = false;
	
	public Vector3 up = new Vector3(0,1,0);
	public Vector3 viewdirection = new Vector3(1,0,0);
	public Vector3 tmp = new Vector3();
	public Vector3 right = new Vector3();
	
	public Vector3 position = new Vector3(-1,0,0);
	
	public void move(InputState inputstate)
	{
		tmp.set(0,1,0);
		rotateangle -= inputstate.dx;
		updownangle -= inputstate.dy;
		if (updownangle < -89) updownangle = -89;
		if (updownangle > 89) updownangle = 89;
		
		viewdirection.set(1,0,0).rotate(up, rotateangle);
		tmp.set(viewdirection).crs(up).nor();
		viewdirection.rotate(tmp, updownangle);
		
		right.set(0,0,1);
		right.rotate(up, rotateangle);
		
		tmp.set(0,0,0);
		
		if (inputstate.keysdown[Keys.W]) tmp.add(viewdirection);
		if (inputstate.keysdown[Keys.S]) tmp.sub(viewdirection);
		if (inputstate.keysdown[Keys.D]) tmp.add(right);
		if (inputstate.keysdown[Keys.A]) tmp.sub(right);

		position.add(tmp.scl(4f*0.033f));
	}
	
	public void setCam(Camera cam)
	{
		cam.up.set(up);
		cam.direction.set(viewdirection);
		cam.position.set(position);
		
		cam.update();
	}

	public static void moveCam(Camera cam, InputState inputstate)
	{
/*
		Vector3 tmp = new Vector3(0,1,0);
		rotateangle -= inputstate.dx;
		updownangle -= inputstate.dy;
		if (updownangle < -89) updownangle = -89;
		if (updownangle > 89) updownangle = 89;
		
		cam.up.set(up);
		viewdirection.set(1,0,0).rotate(up, rotateangle);
		tmp.set(viewdirection).crs(up).nor();
		viewdirection.rotate(tmp, updownangle);
		cam.direction.set(viewdirection);
		
		Vector3 right = new Vector3(0,0,1);
		right.rotate(up, rotateangle);
		
		Vector3 tmp2 = new Vector3(0,0,0);
		
		if (inputstate.keysdown[Keys.W]) tmp2.add(viewdirection);
		if (inputstate.keysdown[Keys.S]) tmp2.sub(viewdirection);
		if (inputstate.keysdown[Keys.D]) tmp2.add(right);
		if (inputstate.keysdown[Keys.A]) tmp2.sub(right);
		
		cam.translate(tmp2.scl(4f*0.033f));
		
		cam.update();*/
	}
}
