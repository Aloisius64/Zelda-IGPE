package guiInterface.object3D;

import com.jme3.scene.Spatial;
import common3D.Builderable;
import common3D.Rotation;
import common3D.URLModelProvider;
import common3D.UpdaterControl;
import gameLogic.character.Hero;
import gameLogic.object.EmptyUtility;
import gameLogic.object.Heart;
import guiInterface.GamePanel3D;
import staticConstant.StaticConstantGame;

public class Heart3D extends Heart implements Builderable
{

	private Spatial model;

	public Heart3D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		initModelToScene(xPosition, yPosition);
	}

	@Override
	public Spatial getModel()
	{
		return model;
	}

	@Override
	public void setModel(Spatial model)
	{
		this.model = model;
	}

	private void initModelToScene(int xPosition, int yPosition)
	{
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.HEART_MODEL);
		model.setLocalScale(0.01f);
		model.move(yPosition, 0.0f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		// Controllo per l'aggiornamento
		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);

		// Controllo per la rotazione nella scena
		Rotation rotation = new Rotation(0, 10, 0, this);
		model.addControl(rotation);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			StaticConstantGame.rootNode.detachChild((this).getModel());
			GamePanel3D.updateGuiNode = true;
			if (hero.getHearts() >= hero.getMaximumHeart())
			{
				hero.modifyScores(-(this.getScore() * 2));
			}
			else
			{
				hero.modifyHearts(1);
				hero.modifyScores(-(this.getScore()));
			}
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);

		}

	}
}
