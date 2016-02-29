package gameLogic.object;

import gameLogic.AbstractObject;
import gameLogic.Editable;
import gameLogic.HasScore;
import gameLogic.Update;

/**
 * <p>
 * <b>Classe</b> per gli oggetti che modificano le caratteristiche del gioco
 * </p>
 * 
 * @author Aloisius
 * 
 */
public abstract class AbstractUtilityObject extends AbstractObject implements HasScore, Update,
		Editable
{

	private final int score;

	public AbstractUtilityObject(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		this.score = 0;
	}

	public AbstractUtilityObject(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition);
		this.score = score;
	}

	@Override
	public void update()
	{
	}

	@Override
	public int getScore()
	{
		return score;
	}
}
