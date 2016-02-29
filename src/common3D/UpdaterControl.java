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
import gameLogic.Update;

/**
 * 
 * @author Aloisius
 */
public class UpdaterControl extends AbstractControl
{
	Update updateObject;

	public UpdaterControl()
	{
	}

	public UpdaterControl(Update updateObject)
	{
		this.updateObject = updateObject;
	}

	@Override
	protected void controlUpdate(float tpf)
	{
		updateObject.update();
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{
	}

	public Control cloneForSpatial(Spatial spatial)
	{
		UpdaterControl control = new UpdaterControl(updateObject);
		control.setSpatial(spatial);
		return control;
	}

}
