package guiInterface.object3D;

import com.jme3.scene.Spatial;
import common3D.Builderable;
import common3D.URLModelProvider;
import common3D.UpdaterControl;
import gameLogic.object.InvisibleWall;
import staticConstant.StaticConstantGame;

public class InvisibleWall3D extends InvisibleWall implements Builderable
{

	private Spatial model;

	public InvisibleWall3D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		initModelToScene(xPosition, yPosition);
	}

	public Spatial getModel()
	{
		return model;
	}

	public void setModel(Spatial model)
	{
		this.model = model;
	}

	private void initModelToScene(int xPosition, int yPosition)
	{
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.INVISIBLE_WALL_MODEL);
		model.setLocalScale(0.01f);
		model.move(yPosition, 0.751f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		// Controllo per l'aggiornamento
		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);
	}
}
