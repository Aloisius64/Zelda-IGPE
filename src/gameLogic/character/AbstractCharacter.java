package gameLogic.character;

import common.Direction;
import gameLogic.Editable;
import gameLogic.HasScore;
import gameLogic.ObjectScene;
import gameLogic.Update;

public abstract class AbstractCharacter implements ObjectScene, HasScore, Update, Editable
{

	private Direction currentDirection;
	private float damageFar;
	private float damageNear;
	private float factorFar;
	private float factorNear;
	private int hearts;
	private int id;
	private int magicEnergy;
	private int maximumHeart;
	private int maximumMagicEnergy;
	private int scores;
	private int state;
	private int xPosition;
	private int yPosition;

	public AbstractCharacter(final int xPosition, final int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
	}

	public abstract int attack(int typeAttack);

	/**
	 * Getter of the property <tt>currentDirection</tt>
	 * 
	 * @return Returns the currentDirection.
	 * @uml.property name="currentDirection"
	 */
	public Direction getCurrentDirection()
	{
		return currentDirection;
	}

	/**
	 * Getter of the property <tt>damageFar</tt>
	 * 
	 * @return Returns the damageFar.
	 * @uml.property name="damageFar"
	 */
	public float getDamageFar()
	{
		return damageFar;
	}

	/**
	 * Getter of the property <tt>damageNear</tt>
	 * 
	 * @return Returns the damageNear.
	 * @uml.property name="damageNear"
	 */
	public float getDamageNear()
	{
		return damageNear;
	}

	/**
	 * Getter of the property <tt>coefficientFar</tt>
	 * 
	 * @return Returns the coefficientFar.
	 * @uml.property name="factorFar"
	 */
	public float getFactorFar()
	{
		return factorFar;
	}

	/**
	 * Getter of the property <tt>coefficientNear</tt>
	 * 
	 * @return Returns the coefficientNear.
	 * @uml.property name="factorNear"
	 */
	public float getFactorNear()
	{
		return factorNear;
	}

	/**
	 * Getter of the property <tt>hearts</tt>
	 * 
	 * @return Returns the hearts.
	 * @uml.property name="hearts"
	 */
	public int getHearts()
	{
		return hearts;
	}

	/**
	 * Getter of the property <tt>id</tt>
	 * 
	 * @return Returns the id.
	 * @uml.property name="id"
	 */
	public int getId()
	{
		return id;
	}

	/**
	 * Getter of the property <tt>magicEnergy</tt>
	 * 
	 * @return Returns the magicEnergy.
	 * @uml.property name="magicEnergy"
	 */
	public int getMagicEnergy()
	{
		return magicEnergy;
	}

	/**
	 * Getter of the property <tt>maximumHeart</tt>
	 * 
	 * @return Returns the maximumHeart.
	 * @uml.property name="maximumHeart"
	 */
	public int getMaximumHeart()
	{
		return maximumHeart;
	}

	/**
	 * Getter of the property <tt>maximumMagicEnergy</tt>
	 * 
	 * @return Returns the maximumMagicEnergy.
	 * @uml.property name="maximumMagicEnergy"
	 */
	public int getMaximumMagicEnergy()
	{
		return maximumMagicEnergy;
	}

	/**
	 * Getter of the property <tt>score</tt>
	 * 
	 * @return Returns the score.
	 * @uml.property name="scores"
	 */
	@Override
	public int getScore()
	{
		return scores;
	}

	public int getState()
	{
		return state;
	}

	@Override
	public int getX()
	{
		// TODO Auto-generated method stub
		return xPosition;
	}

	@Override
	public int getY()
	{
		// TODO Auto-generated method stub
		return yPosition;
	}

	/**
     */
	public boolean isDead()
	{
		return hearts < 1;
	}

	public void modifyDamageFar(final int numberDamageFar)
	{
		damageFar += numberDamageFar;
	}

	public void modifyDamageNear(final int numberDamageNear)
	{
		damageNear += numberDamageNear;
	}

	public void modifyFactorFar(final int numberFactorFar)
	{
		factorFar += numberFactorFar;
	}

	public void modifyFactorNear(final int numberFactorNear)
	{
		factorNear += numberFactorNear;
	}

	public void modifyHearts(final int numberHearts)
	{
		hearts += numberHearts;
	}

	public void modifyMagicEnergy(final int numberMagicEnergy)
	{
		magicEnergy += numberMagicEnergy;
	}

	public void modifyScores(final int numberScores)
	{
		int oldScore = scores;
		scores += numberScores;
		if (scores < 0)
		{
			scores = 0;
		}
	}

	public abstract boolean move();

	public void setCurrentDirection(Direction direction)
	{
		this.currentDirection = direction;
	}

	/**
	 * Setter of the property <tt>currentDirection</tt>
	 * 
	 * @param currentDirection
	 *            The currentDirection to set.
	 * @uml.property name="currentDirection"
	 */
	public void setCurrentDirection(int typeDirection)
	{
		this.currentDirection.setDirection(typeDirection);
	}

	/**
	 * Setter of the property <tt>damageFar</tt>
	 * 
	 * @param damageFar
	 *            The damageFar to set.
	 * @uml.property name="damageFar"
	 */
	public void setDamageFar(float damageFar)
	{
		this.damageFar = damageFar;
	}

	/**
	 * Setter of the property <tt>damageNear</tt>
	 * 
	 * @param damageNear
	 *            The damageNear to set.
	 * @uml.property name="damageNear"
	 */
	public void setDamageNear(float damageNear)
	{
		this.damageNear = damageNear;
	}

	/**
	 * Setter of the property <tt>coefficientFar</tt>
	 * 
	 * @param coefficientFar
	 *            The coefficientFar to set.
	 * @uml.property name="factorFar"
	 */
	public void setFactorFar(float factorFar)
	{
		this.factorFar = factorFar;
	}

	/**
	 * Setter of the property <tt>coefficientNear</tt>
	 * 
	 * @param coefficientNear
	 *            The coefficientNear to set.
	 * @uml.property name="factorNear"
	 */
	public void setFactorNear(float factorNear)
	{
		this.factorNear = factorNear;
	}

	/**
	 * Setter of the property <tt>hearts</tt>
	 * 
	 * @param hearts
	 *            The hearts to set.
	 * @uml.property name="hearts"
	 */
	public void setHearts(int hearts)
	{
		this.hearts = hearts;
	}

	/**
	 * Setter of the property <tt>id</tt>
	 * 
	 * @param id
	 *            The id to set.
	 * @uml.property name="id"
	 */
	public void setId(int id)
	{
		this.id = id;
	}

	/**
	 * Setter of the property <tt>magicEnergy</tt>
	 * 
	 * @param magicEnergy
	 *            The magicEnergy to set.
	 * @uml.property name="magicEnergy"
	 */
	public void setMagicEnergy(int magicEnergy)
	{
		this.magicEnergy = magicEnergy;
	}

	/**
	 * Setter of the property <tt>maximumHeart</tt>
	 * 
	 * @param maximumHeart
	 *            The maximumHeart to set.
	 * @uml.property name="maximumHeart"
	 */
	public void setMaximumHeart(int maximumHeart)
	{
		this.maximumHeart = maximumHeart;
	}

	/**
	 * Setter of the property <tt>maximumMagicEnergy</tt>
	 * 
	 * @param maximumMagicEnergy
	 *            The maximumMagicEnergy to set.
	 * @uml.property name="maximumMagicEnergy"
	 */
	public void setMaximumMagicEnergy(int maximumMagicEnergy)
	{
		this.maximumMagicEnergy = maximumMagicEnergy;
	}

	/**
	 * Setter of the property <tt>score</tt>
	 * 
	 * @param score
	 *            The score to set.
	 * @uml.property name="scores"
	 */
	public void setScores(int scores)
	{
		this.scores = scores;
	}

	public void setState(int state)
	{
		this.state = state;
	}

	public void setX(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public void setY(int yPosition)
	{
		this.yPosition = yPosition;
	}

	@Override
	public abstract void update();
}
