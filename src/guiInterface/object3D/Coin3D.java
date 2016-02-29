package guiInterface.object3D;

import com.jme3.math.FastMath;
import com.jme3.math.Quaternion;
import com.jme3.math.Vector3f;
import com.jme3.scene.Spatial;
import common3D.Builderable;
import common3D.Rotation;
import common3D.URLModelProvider;
import common3D.UpdaterControl;
import gameLogic.character.Hero;
import gameLogic.object.Coin;
import gameLogic.object.EmptyUtility;
import guiInterface.GamePanel3D;
import staticConstant.StaticConstantGame;

public class Coin3D extends Coin implements Builderable
{

	private Spatial model;

	public Coin3D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 10);
		initModelToScene(xPosition, yPosition);
	}

	public Coin3D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
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
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.COIN_MODEL);
		model.setLocalScale(0.1f);
		model.rotate(new Quaternion().fromAngleAxis(FastMath.PI / 2, new Vector3f(1, 0, 0)));
		model.move(yPosition, 0.5f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		// Controllo per la rotazione nella scena
		Rotation rotation = new Rotation(0, 0, 10, this);
		model.addControl(rotation);

		// Controllo per l'aggiornamento
		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			hero.modifyScores(this.getScore());
			GamePanel3D.updateGuiNode = true;
			StaticConstantGame.rootNode.detachChild(model);
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);
		}
	}
}
