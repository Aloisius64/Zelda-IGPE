package common;

public class Direction
{

	public static final int CENTER = 4;
	public static final int DOWN = 7;
	public static final int DOWN_LEFT = 6;
	public static final int DOWN_RIGHT = 8;
	public static final int LEFT = 3;
	public static final int RIGHT = 5;
	public static final int UP = 1;
	public static final int UP_LEFT = 0;
	public static final int UP_RIGHT = 2;
	private int xSelected;
	private int ySelected;

	/**
     */
	public Direction()
	{
		xSelected = 0;
		ySelected = 0;
	}

	public Direction(int typeDirection)
	{
		setDirection(typeDirection);
	}

	public void addDirection(Direction direction)
	{
		this.xSelected += direction.getXSelected();
		this.ySelected += direction.getYSelected();
	}

	public Direction getReverse()
	{
		Direction d = new Direction();
		d.setXSelected(-this.getXSelected());
		d.setYSelected(-this.getYSelected());
		return d;
	}

	public int getTypeDirection()
	{
		if ((xSelected == 0) && (ySelected == 0))
		{
			return Direction.CENTER;
		}
		else if ((xSelected == 1) && (ySelected == 0))
		{
			return Direction.DOWN;
		}
		else if ((xSelected == 1) && (ySelected == -1))
		{
			return Direction.DOWN_LEFT;
		}
		else if ((xSelected == 1) && (ySelected == 1))
		{
			return Direction.DOWN_RIGHT;
		}
		else if ((xSelected == 0) && (ySelected == -1))
		{
			return Direction.LEFT;
		}
		else if ((xSelected == 0) && (ySelected == 1))
		{
			return Direction.RIGHT;
		}
		else if ((xSelected == -1) && (ySelected == 0))
		{
			return Direction.UP;
		}
		else if ((xSelected == -1) && (ySelected == -1))
		{
			return Direction.UP_LEFT;
		}
		else if ((xSelected == -1) && (ySelected == 1))
		{
			return Direction.UP_RIGHT;
		}
		return 0;
	}

	/**
	 * Getter of the property <tt>xSelected</tt>
	 * 
	 * @return Returns the xSelected.
	 * @uml.property name="xSelected"
	 */
	public int getXSelected()
	{
		return xSelected;
	}

	/**
	 * Getter of the property <tt>ySelected</tt>
	 * 
	 * @return Returns the ySelected.
	 * @uml.property name="ySelected"
	 */
	public int getYSelected()
	{
		return ySelected;
	}

	public void reset()
	{
		this.xSelected = 0;
		this.ySelected = 0;
	}

	public void setDirection(int typeDirection)
	{
		switch (typeDirection)
		{
			case UP_LEFT:
				xSelected = ySelected = -1;
				break;
			case UP:
				xSelected = -1;
				ySelected = 0;
				break;
			case UP_RIGHT:
				xSelected = -1;
				ySelected = 1;
				break;
			case LEFT:
				xSelected = 0;
				ySelected = -1;
				break;
			case CENTER:
				xSelected = 0;
				ySelected = 0;
				break;
			case RIGHT:
				xSelected = 0;
				ySelected = 1;
				break;
			case DOWN_LEFT:
				xSelected = 1;
				ySelected = -1;
				break;
			case DOWN:
				xSelected = 1;
				ySelected = 0;
				break;
			case DOWN_RIGHT:
				xSelected = 1;
				ySelected = 1;
				break;
			default:
				reset();
				break;
		}
	}

	/**
	 * Setter of the property <tt>xSelected</tt>
	 * 
	 * @param xSelected
	 *            The xSelected to set.
	 * @uml.property name="xSelected"
	 */
	public void setXSelected(int xSelected)
	{
		this.xSelected = xSelected;
	}

	/**
	 * Setter of the property <tt>ySelected</tt>
	 * 
	 * @param ySelected
	 *            The ySelected to set.
	 * @uml.property name="ySelected"
	 */
	public void setYSelected(int ySelected)
	{
		this.ySelected = ySelected;
	}

	@Override
	public String toString()
	{
		return getTypeDirection() + "";
	}
}
