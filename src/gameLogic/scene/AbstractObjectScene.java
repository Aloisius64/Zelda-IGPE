package gameLogic.scene;

import gameLogic.AbstractObject;
import gameLogic.Editable;

/**
 * <p>
 * <b>Classe</b> per gli elementi dello scenario che non interferiscono nella
 * logica del gioco
 * </p>
 * 
 * @author Aloisius
 * 
 */

public abstract class AbstractObjectScene extends AbstractObject
{

	public AbstractObjectScene(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

}
