/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common3D;

import com.jme3.renderer.RenderManager;
import com.jme3.renderer.ViewPort;
import com.jme3.scene.Spatial;
import com.jme3.scene.control.AbstractControl;
import com.jme3.scene.control.Control;
import gameLogic.object.AbstractUtilityObject;

/**
 * 
 * @author Aloisius
 */
public class Rotation extends AbstractControl
{

	private float speedX = 0;
	private float speedY = 0;
	private float speedZ = 0;
	private AbstractUtilityObject object;

	public Rotation()
	{
		speedX = 0;
		speedY = 0;
		speedZ = 0;
	}

	public Rotation(float speedX, float speedY, float speedZ)
	{
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedZ = speedZ;
	}

	public Rotation(float speedX, float speedY, float speedZ, AbstractUtilityObject object)
	{
		this.speedX = speedX;
		this.speedY = speedY;
		this.speedZ = speedZ;
		this.object = object;
	}

	@Override
	protected void controlUpdate(float tpf)
	{
		spatial.rotate(tpf * speedX, tpf * speedY, tpf * speedZ);
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{
	}

	public Control cloneForSpatial(Spatial spatial)
	{
		Rotation control = new Rotation(speedX, speedY, speedZ);
		control.setSpatial(spatial);
		return control;
	}

	public float getSpeedX()
	{
		return speedX;
	}

	public void setSpeedX(float speedX)
	{
		this.speedX = speedX;
	}

	public float getSpeedY()
	{
		return speedY;
	}

	public void setSpeedY(float speedY)
	{
		this.speedY = speedY;
	}

	public float getSpeedZ()
	{
		return speedZ;
	}

	public void setSpeedZ(float speedZ)
	{
		this.speedZ = speedZ;
	}

	public AbstractUtilityObject getObject()
	{
		return object;
	}

	public void setObject(AbstractUtilityObject object)
	{
		this.object = object;
	}
}
