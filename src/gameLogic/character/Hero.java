package gameLogic.character;

import common.Direction;
import common.SoundManager;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Block;
import gameLogic.object.Button;
import gameLogic.object.FallPoint;
import gameLogic.object.FlameTower;
import gameLogic.object.InvisibleWall;
import gameLogic.object.Treasure;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

/**
 * <p>
 * <b>Classe</b> che rappresenta il/i giocatore/i
 * </p>
 * 
 * @author Aloisius
 * 
 */
public class Hero extends AbstractCharacter
{
	private int triforcePieceCollected = 0;

	public Hero()
	{
		super(0, 0);
		setState(CharacterState.STAND);
	}

	public Hero(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);

		setId(StaticConstantID.HERO);
		setCurrentDirection(new Direction(Direction.CENTER));
		setDamageFar(2);
		setDamageNear(1);
		setFactorFar(1.0f);
		setFactorNear(1.0f);
		setHearts(6);
		setMagicEnergy(10);
		setScores(0);
		setMaximumHeart(6);
		setMaximumMagicEnergy(15);
		setState(CharacterState.STAND);
	}

	// attaco a distanza
	public int attackOne()
	{
		if (this.getMagicEnergy() > 0)
		{
			int xTarget = getX() + getCurrentDirection().getXSelected();
			int yTarget = getY() + getCurrentDirection().getYSelected();

			AbstractObjectScene sceneTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellBaseWorld(xTarget, yTarget);

			AbstractCharacter characterTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellCharacterWorld(xTarget, yTarget);

			if (sceneTarget.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
			{
				return 0;
			}

			int returnValue = 0;
			if (characterTarget instanceof EmptyCharacter)
			{
				returnValue = 2;
			}
			else if (characterTarget instanceof AbstractEnemy)
			{
				int totalDamage = (int) (getFactorFar() * getDamageFar());
				((AbstractEnemy) characterTarget).modifyHearts(-totalDamage);
				returnValue = 1;
			}
			else
			{
				return 0;
			}

			modifyMagicEnergy(-1);
			return returnValue;
		}
		return 0;
	}

	// attacco fisico
	public int attackZero()
	{
		AbstractCharacter objectTarget[] = new AbstractCharacter[3];

		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();
		int typeDirection = getCurrentDirection().getTypeDirection();
		int targetHitted = 0;

		AbstractUtilityObject utilityTarget = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(x, y);
		if (utilityTarget instanceof Treasure)
		{
			if (!((Treasure) utilityTarget).isOpen())
				SoundManager.playSound(SoundManager.TREASURE_OPEN, 1, SoundManager.EFFECT);

			((Treasure) utilityTarget).manageContent(this);

			return targetHitted;
		}
		if (utilityTarget instanceof Button)
		{
			Button button = (Button) utilityTarget;
			button.setActive(!button.isActive());

			SoundManager.playSound(SoundManager.BUTTON_CHANGE_STATE, 1, SoundManager.EFFECT);

			return targetHitted;
		}

		for (int i = -1; i < 2; i++)
		{
			if ((typeDirection == Direction.LEFT) || (typeDirection == Direction.RIGHT))
			{
				objectTarget[i + 1] = StaticConstantGame.gameManager.getConcreteWorld()
						.getCellCharacterWorld(x + i, y);
			}
			else if ((typeDirection == Direction.UP) || (typeDirection == Direction.DOWN))
			{
				objectTarget[i + 1] = StaticConstantGame.gameManager.getConcreteWorld()
						.getCellCharacterWorld(x, y + i);
			}
		}

		for (int i = 0; i < 3; i++)
		{
			if (objectTarget[i] instanceof AbstractEnemy)
			{
				AbstractEnemy enemy = (AbstractEnemy) objectTarget[i];
				int totalDamage = (int) (this.getDamageNear() * this.getFactorNear());
				enemy.modifyHearts(-totalDamage);
				enemy.setCurrentDirection(getCurrentDirection().getTypeDirection());
				enemy.move();
				targetHitted = 1;
			}
		}
		return targetHitted;
	}

	@Override
	public int attack(int typeAttack)
	{
		switch (typeAttack)
		{
			case 0:
				return attackZero();
			case 1:
				return attackOne();
			case 2:
				return attackTwo();
			default:
				return 0;
		}
	}

	public int attackTwo()
	{
		modifyDamageNear(+5);
		int enemeyHitted = 0;
		for (int i = 0; i < 4; i++)
		{
			enemeyHitted += attackZero();
			setCurrentDirection((getCurrentDirection().getTypeDirection() + 2) % 8);
		}
		modifyDamageNear(-5);
		return enemeyHitted;
	}

	@Override
	public boolean move()
	{
		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(x, y);
		AbstractUtilityObject utilityObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(x, y);
		AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(x, y);

		if (sceneObject.getId() >= StaticConstantID.LIMITS_ACCESSIBLE)
		{

			// controllo se stiamo per andare contro un invisible Wall
			if (utilityObject instanceof InvisibleWall)
			{
				((InvisibleWall) utilityObject).setActive(true);

				SoundManager.playSound(SoundManager.ENTER_INTO_INVISIBLE_WALL, 1,
						SoundManager.EFFECT);

				return false;
			}

			if (utilityObject instanceof Block)
			{
				if (((Block) utilityObject).isMovable())
				{
					((Block) utilityObject).move(this);
				}
				else
				{
					return false;
				}
			}

			if (utilityObject instanceof Treasure)
			{
				return false;
			}

			if (utilityObject instanceof FlameTower)
			{
				return false;
			}

			if (character instanceof AbstractEnemy)
			{
				modifyHearts(-1);
				return false;
			}

			// impostiamo posizione del personaggio
			StaticConstantGame.gameManager.getConcreteWorld().swapCell(
					ConcreteWorld.CODE_CHARACTER_WORLD, getX(), getY(), x, y);
			setX(x);
			setY(y);

			if (sceneObject.getId() == 0)
			{
				setState(CharacterState.FALLING);
			}

			if (utilityObject instanceof FallPoint)
			{
				((FallPoint) utilityObject).setOpen(true);
				setState(CharacterState.FALLING);
			}

		}
		return true;
	}

	@Override
	public void update()
	{
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}

	public int getTriforcePieceCollected()
	{
		return triforcePieceCollected;
	}

	public void setTriforcePieceCollected(int triforcePieceCollected)
	{
		this.triforcePieceCollected = triforcePieceCollected;
	}
}