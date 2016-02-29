package gameLogic;

/**
 * <p>
 * <b>Classe</b> Tutto ci� che costituisce il mondo
 * </p>
 * 
 * @author Aloisius
 * 
 */
public abstract class AbstractObject implements ObjectScene, Editable
{

	/**
	 * @uml.property name="id"
	 */
	private int id;
	private int xPosition;
	private int yPosition;

	public AbstractObject(final int xPosition, final int yPosition)
	{
		this.xPosition = xPosition;
		this.yPosition = yPosition;
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

	@Override
	public int getX()
	{
		return xPosition;
	}

	@Override
	public int getY()
	{
		return yPosition;
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

	public void setX(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public void setY(int yPosition)
	{
		this.yPosition = yPosition;
	}
}
