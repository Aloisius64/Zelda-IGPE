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
import gameLogic.object.Gate;

/**
 * 
 * @author Aloisius
 */
public class TransfertControl extends AbstractControl
{

	private Gate gate;

	public TransfertControl()
	{

	}

	public TransfertControl(Gate gate)
	{
		this.gate = gate;
	}

	@Override
	protected void controlUpdate(float tpf)
	{
		gate.update();
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{
	}

	public Control cloneForSpatial(Spatial spatial)
	{
		TransfertControl control = new TransfertControl(gate);
		control.setSpatial(spatial);
		return control;
	}

}
