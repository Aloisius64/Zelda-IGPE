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
import gameLogic.character.AbstractEnemy;
import java.util.concurrent.Future;

/**
 * 
 * @author Aloisius
 */
public class EnemyControl extends AbstractControl
{

	private AbstractEnemy enemy;

	public EnemyControl(AbstractEnemy enemy)
	{
		this.enemy = enemy;
	}

	@Override
	protected void controlUpdate(float tpf)
	{
		enemy.update();
	}

	@Override
	protected void controlRender(RenderManager rm, ViewPort vp)
	{
	}

	public Control cloneForSpatial(Spatial spatial)
	{
		EnemyControl control = new EnemyControl(enemy);
		control.setSpatial(spatial);
		return control;
	}
}
