/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package common3D;

import com.jme3.scene.Spatial;

/**
 * 
 * @author Aloisius
 */
public interface Builderable
{
	public Spatial getModel();

	public void setModel(Spatial model);
}
