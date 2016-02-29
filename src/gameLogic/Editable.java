package gameLogic;

import gameLogic.world.World;

public interface Editable
{

	public abstract void manageAddition(World world, int x, int y);

	public abstract boolean canAdd(int x, int y);

	public abstract void manageRemotion(World world, int x, int y);
}
