package guiInterface.object3D;

import com.jme3.scene.Spatial;
import common3D.Builderable;
import common3D.URLModelProvider;
import common3D.UpdaterControl;
import gameLogic.object.StartPoint;
import staticConstant.StaticConstantGame;

public class StartPoint3D extends StartPoint implements Builderable
{
	private Spatial model;

	public StartPoint3D(int xPosition, int yPosition)
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
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.START_POINT_MODEL);
		model.setLocalScale(0.2f);
		model.move(yPosition, 0.0f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);
	}
}
