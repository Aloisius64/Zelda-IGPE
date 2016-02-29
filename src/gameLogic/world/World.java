package gameLogic.world;

import gameLogic.character.AbstractCharacter;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.scene.AbstractObjectScene;

public interface World
{
	public abstract AbstractObjectScene getCellBaseWorld(int x, int y);

	public abstract AbstractCharacter getCellCharacterWorld(int x, int y);

	public abstract AbstractUtilityObject getCellObjectWorld(int x, int y);

	public abstract AbstractUtilityObject getFinalPoint();

	public abstract int getHeight();

	public abstract int getNumberEnemies();

	public abstract int getNumberObject(int typeObject);

	public abstract AbstractUtilityObject getStartPoint();

	public abstract int getWidth();

	public abstract void setCellBaseWorld(int xPosition, int yPosition,
			AbstractObjectScene objectScene);

	public abstract void setCellCharacterWorld(int xPosition, int yPosition,
			AbstractCharacter character);

	public abstract void setCellObjectWorld(int xPosition, int yPosition,
			AbstractUtilityObject utilityObject);

	public abstract void setFinalPoint(int xPosition, int yPosition);
}
