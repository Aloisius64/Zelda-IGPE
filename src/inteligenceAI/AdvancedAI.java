package inteligenceAI;

import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.CharacterState;
import gameLogic.character.Hero;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Block;
import gameLogic.object.Button;
import gameLogic.object.FallPoint;
import gameLogic.object.FlameTower;
import gameLogic.object.Gate;
import gameLogic.object.InvisibleWall;
import gameLogic.object.MagicGate;
import gameLogic.object.Platform;
import gameLogic.object.Treasure;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.World;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

import common.Direction;

public class AdvancedAI extends AbstractAI
{
	public AdvancedAI()
	{
		super();
	}

	public AdvancedAI(AbstractCharacter enemy)
	{
		super(enemy);
		getEnemy().modifyFactorNear((int) getEnemy().getFactorNear());
		getEnemy().modifyFactorFar((int) getEnemy().getFactorFar());
	}

	private void setNextState()
	{
		int distance = getDistance();
		if (distance <= 1)
		{
			if (areInTheSameDirection())
			{
				correctDirection();
				getEnemy().setState(CharacterState.PHYSIC_ATTACK);
			}
			else
			{
				getEnemy().setState(CharacterState.WALK);
			}
		}
		else if (areInTheSameDirection())
		{
			correctDirection();
			getEnemy().setState(CharacterState.DISTANCE_ATTACK);
		}
		else if (distance <= 8)
		{
			getEnemy().setState(CharacterState.WALK);
		}
	}

	@Override
	public void update()
	{
		int state = getEnemy().getState();
		switch (state)
		{
			case CharacterState.STAND:
			{
				setNextState();
				break;
			}
			case CharacterState.PHYSIC_ATTACK:
			{
				getEnemy().setCurrentDirection(getDirection());
				setNextState();
				break;
			}
			case CharacterState.DISTANCE_ATTACK:
			{
				getEnemy().setCurrentDirection(getDirection());
				setNextState();
			}
				break;
			case CharacterState.WALK:
			{
				getEnemy().setCurrentDirection(
						chooseDirection(getEnemy(), StaticConstantGame.gameManager.getHero(),
								StaticConstantGame.gameManager.getConcreteWorld()));
				setNextState();
			}
				break;
			default:
				getEnemy().setState(CharacterState.STAND);
				setNextState();
				break;
		}
	}

	private class Cell
	{

		final int column;

		final int row;

		public Cell(final int row, final int column)
		{
			this.row = row;
			this.column = column;
		}

		@Override
		public boolean equals(final Object obj)
		{
			if (this == obj)
			{
				return true;
			}
			if (obj == null)
			{
				return false;
			}
			if (getClass() != obj.getClass())
			{
				return false;
			}
			final Cell other = (Cell) obj;
			if (column != other.column)
			{
				return false;
			}
			if (row != other.row)
			{
				return false;
			}
			return true;
		}

		@Override
		public int hashCode()
		{
			final int prime = 31;
			int result = 1;
			result = prime * result + column;
			result = prime * result + row;
			return result;
		}

	}

	public int chooseDirection(final AbstractCharacter enemy, final Hero hero, final World world)
	{
		final List<Cell> queue = new ArrayList<Cell>();
		queue.add(new Cell(enemy.getX(), enemy.getY()));
		boolean found = false;
		int last = 0;
		final Map<Cell, Cell> tree = new HashMap<Cell, Cell>();
		while (!found && last < queue.size())
		{
			final Cell current = queue.get(last);
			last++;
			if (hero.getY() == current.column && hero.getX() == current.row)
			{
				found = true;
				return findDirectionFromCell(tree, current, enemy);
			}
			else
			{
				addChildren(queue, tree, current, world);
			}
		}
		return Direction.CENTER;
	}

	private void addChildren(final List<Cell> queue, final Map<Cell, Cell> tree,
			final Cell current, final World world)
	{
		if (current.row - 1 >= 0)
		{
			AbstractObjectScene scene = world.getCellBaseWorld(current.row - 1, current.column);
			AbstractUtilityObject object = world
					.getCellObjectWorld(current.row - 1, current.column);
			AbstractCharacter character = world.getCellCharacterWorld(current.row - 1,
					current.column);

			boolean canAdd = true;

			if (scene.getId() <= StaticConstantID.LIMITS_ACCESSIBLE)
			{
				canAdd = false;
			}

			if (object instanceof Treasure || object instanceof Button || object instanceof Block
					|| object instanceof FlameTower || object instanceof FallPoint
					|| object instanceof Gate || object instanceof InvisibleWall
					|| object instanceof MagicGate || object instanceof Platform)
			{
				canAdd = false;
			}

			if (character instanceof AbstractEnemy)
			{
				canAdd = false;
			}

			if (canAdd)
			{
				final Cell newCell = new Cell(current.row - 1, current.column);
				if (!queue.contains(newCell))
				{
					queue.add(newCell);
					tree.put(newCell, current);
				}
			}
		}
		if (current.row + 1 < world.getHeight())
		{
			AbstractObjectScene scene = world.getCellBaseWorld(current.row + 1, current.column);
			AbstractUtilityObject object = world
					.getCellObjectWorld(current.row + 1, current.column);
			AbstractCharacter character = world.getCellCharacterWorld(current.row + 1,
					current.column);

			boolean canAdd = true;

			if (scene.getId() <= StaticConstantID.LIMITS_ACCESSIBLE)
			{
				canAdd = false;
			}

			if (object instanceof Treasure || object instanceof Button || object instanceof Block
					|| object instanceof FlameTower || object instanceof FallPoint
					|| object instanceof Gate || object instanceof InvisibleWall
					|| object instanceof MagicGate || object instanceof Platform)
			{
				canAdd = false;
			}

			if (character instanceof AbstractEnemy)
			{
				canAdd = false;
			}

			if (canAdd)
			{
				final Cell newCell = new Cell(current.row + 1, current.column);
				if (!queue.contains(newCell))
				{
					queue.add(newCell);
					tree.put(newCell, current);
				}
			}
		}
		if (current.column - 1 >= 0)
		{
			AbstractObjectScene scene = world.getCellBaseWorld(current.row, current.column - 1);
			AbstractUtilityObject object = world
					.getCellObjectWorld(current.row, current.column - 1);
			AbstractCharacter character = world.getCellCharacterWorld(current.row,
					current.column - 1);

			boolean canAdd = true;

			if (scene.getId() <= StaticConstantID.LIMITS_ACCESSIBLE)
			{
				canAdd = false;
			}

			if (object instanceof Treasure || object instanceof Button || object instanceof Block
					|| object instanceof FlameTower || object instanceof FallPoint
					|| object instanceof Gate || object instanceof InvisibleWall
					|| object instanceof MagicGate || object instanceof Platform)
			{
				canAdd = false;
			}

			if (character instanceof AbstractEnemy)
			{
				canAdd = false;
			}

			if (canAdd)
			{
				final Cell newCell = new Cell(current.row, current.column - 1);
				if (!queue.contains(newCell))
				{
					queue.add(newCell);
					tree.put(newCell, current);
				}
			}
		}
		if (current.column + 1 < world.getWidth())
		{
			AbstractObjectScene scene = world.getCellBaseWorld(current.row, current.column + 1);
			AbstractUtilityObject object = world
					.getCellObjectWorld(current.row, current.column + 1);
			AbstractCharacter character = world.getCellCharacterWorld(current.row,
					current.column + 1);
			boolean canAdd = true;

			if (scene.getId() <= StaticConstantID.LIMITS_ACCESSIBLE)
			{
				canAdd = false;
			}

			if (object instanceof Treasure || object instanceof Button || object instanceof Block
					|| object instanceof FlameTower || object instanceof FallPoint
					|| object instanceof Gate || object instanceof InvisibleWall
					|| object instanceof MagicGate || object instanceof Platform)
			{
				canAdd = false;
			}

			if (character instanceof AbstractEnemy)
			{
				canAdd = false;
			}

			if (canAdd)
			{
				final Cell newCell = new Cell(current.row, current.column + 1);
				if (!queue.contains(newCell))
				{
					queue.add(newCell);
					tree.put(newCell, current);
				}
			}
		}
	}

	private int findDirectionFromCell(final Map<Cell, Cell> tree, final Cell current,
			final AbstractCharacter enemy)
	{
		Cell parent = tree.get(current);
		Cell directionCell = current;
		while (tree.get(parent) != null)
		{
			directionCell = parent;
			parent = tree.get(parent);
		}
		if (directionCell == null)
		{
			return Direction.CENTER;
		}
		else if (directionCell.row < enemy.getX())
		{
			return Direction.UP;
		}
		else if (directionCell.row > enemy.getX())
		{
			return Direction.DOWN;
		}
		else if (directionCell.column < enemy.getY())
		{
			return Direction.LEFT;
		}
		else if (directionCell.column > enemy.getY())
		{
			return Direction.RIGHT;
		}
		else
		{
			return Direction.CENTER;
		}
	}

	@Override
	public Direction getNewDirection()
	{
		int chooseDirection = chooseDirection(getEnemy(), StaticConstantGame.gameManager.getHero(),
				StaticConstantGame.gameManager.getConcreteWorld());

		if (chooseDirection != Direction.CENTER)
		{
			return new Direction(chooseDirection);
		}

		return getEnemy().getCurrentDirection();
	}
}
