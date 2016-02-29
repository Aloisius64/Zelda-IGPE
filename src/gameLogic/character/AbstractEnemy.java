package gameLogic.character;

import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.InvisibleWall;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.ConcreteWorld;
import inteligenceAI.AbstractAI;
import inteligenceAI.AdvancedAI;
import inteligenceAI.BasicAI;
import inteligenceAI.NetworkAI;
import inteligenceAI.NoAI;
import inteligenceAI.RandomAI;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

/**
 * <p>
 * <b>Classe</b> che rappresenta un generico nemico
 * </p>
 * 
 * @author Aloisius
 * 
 */
public abstract class AbstractEnemy extends AbstractCharacter
{

	private AbstractAI inteligence;

	public AbstractEnemy(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		setState(CharacterState.STAND);
		switch (StaticConstantGame.typeAI)
		{
			case 1:
				setInteligence(new BasicAI(this));
				break;
			case 2:
				setInteligence(new AdvancedAI(this));
				break;
			case 3:
				setInteligence(new NetworkAI(this));
				break;
			case 4:
				setInteligence(new NoAI(this));
				break;
			default:
				setInteligence(new RandomAI(this));
				break;
		}
		getInteligence().setEnemy(this);
	}

	public AbstractEnemy(final int xPosition, final int yPosition, AbstractAI inteligence)
	{
		super(xPosition, yPosition);
		setState(CharacterState.STAND);
		this.setInteligence(inteligence);
		this.getInteligence().setEnemy(this);
	}

	// attacco a distanza
	public int attackOne()
	{
		if (getMagicEnergy() > 0)
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
			else if (characterTarget instanceof Hero)
			{
				int totalDamage = (int) (getFactorFar() * getDamageFar());
				((Hero) characterTarget).modifyHearts(-totalDamage);
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
		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();

		AbstractCharacter objectTarget = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(x, y);

		if (objectTarget instanceof Hero)
		{
			Hero enemy = (Hero) objectTarget;
			int totalDamage = (int) (this.getDamageNear() * this.getFactorNear());
			enemy.modifyHearts(-totalDamage);
			enemy.setCurrentDirection(getCurrentDirection().getTypeDirection());
			enemy.setState(CharacterState.HITTED);
			return 1;
		}
		return 0;
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
			default:
				return 0;
		}
	}

	public AbstractAI getInteligence()
	{
		return inteligence;
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

		if (sceneObject.getId() > StaticConstantID.LIMITS_ACCESSIBLE)
		{
			// controllo se stiamo per andare contro un invisible Wall
			if (utilityObject instanceof InvisibleWall)
			{
				return false;
			}

			if (character instanceof AbstractEnemy)
			{
				return false;
			}

			if (character instanceof AttackSphere)
			{
				modifyHearts(-1);
				return false;
			}

			if (character instanceof Hero)
			{
				Hero hero = StaticConstantGame.gameManager.getHero();
				hero.modifyHearts(-1);
				hero.setState(CharacterState.HITTED);
				return false;
			}

			// impostiamo posizione del personaggio
			StaticConstantGame.gameManager.getConcreteWorld().swapCell(
					ConcreteWorld.CODE_CHARACTER_WORLD, getX(), getY(), x, y);
			setX(x);
			setY(y);

		}
		return true;
	}

	public void setInteligence(AbstractAI inteligence)
	{
		this.inteligence = inteligence;
	}

	@Override
	public void update()
	{
	}

	public boolean logicUpdate()
	{
		if (isDead())
		{
			StaticConstantGame.gameManager.getHero().modifyScores(getScore());
			StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(), getY(),
					new EmptyCharacter(getX(), getY()));
			StaticConstantGame.gameManager.getConcreteWorld().getCharacterCollection().remove(this);
			return false;
		}
		else
		{
			StaticConstantGame.gameManager.modifiyEnemyDefeated(+1);
			getInteligence().update();
			return true;
		}
	}
}
