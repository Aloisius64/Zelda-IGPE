package guiInterface.object3D;

import com.jme3.scene.Spatial;
import common.Direction;
import common3D.Builderable;
import common3D.URLModelProvider;
import common3D.UpdaterControl;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.Hero;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Block;
import gameLogic.object.EmptyUtility;
import gameLogic.scene.AbstractObjectScene;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Block3D extends Block implements Builderable
{

	private Spatial model;

	public Block3D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		initModelToScene(xPosition, yPosition);
	}

	public Block3D(int xPosition, int yPosition, boolean movable)
	{
		super(xPosition, yPosition, false);
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
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.BLOCK_MODEL);
		model.setLocalScale(0.4f);
		model.move(yPosition, 0.5f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);
	}

	@Override
	public boolean move(Hero hero)
	{
		int x = getX() + hero.getCurrentDirection().getXSelected();
		int y = getY() + hero.getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(x, y);
		AbstractUtilityObject utilityObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(x, y);
		AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(x, y);

		if (sceneObject.getId() >= StaticConstantID.LIMITS_ACCESSIBLE)
		{

			if (character instanceof AbstractEnemy)
			{
				return false;
			}

			if (!(utilityObject instanceof EmptyUtility))
			{
				return false;
			}
		}

		float movement = 0.5f;
		int typeDirection = hero.getCurrentDirection().getTypeDirection();

		if (typeDirection == Direction.UP)
		{
			model.move(0, 0, -movement);
		}
		else if (typeDirection == Direction.LEFT)
		{
			model.move(-movement, 0, 0);
		}
		else if (typeDirection == Direction.RIGHT)
		{
			model.move(movement, 0, 0);
		}
		else if (typeDirection == Direction.DOWN)
		{
			model.move(0, 0, movement);
		}

		positionUpdate(true, model.getLocalTranslation().getX(), model.getLocalTranslation().getZ());

		return true;
	}

	public void positionUpdate(boolean falseAnimation, float xPosition, float zPosition)
	{
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
				new EmptyUtility(getX(), getY()));
		setX((int) Math.ceil(zPosition));
		setY((int) Math.ceil(xPosition));
		StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(), this);
	}
}
