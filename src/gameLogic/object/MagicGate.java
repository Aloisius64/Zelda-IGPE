package gameLogic.object;

import staticConstant.StaticConstantID;

public class MagicGate extends Gate
{

	private boolean correct = false;

	public MagicGate(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setId(StaticConstantID.MAGIC_GATE);
		setOpen(true);
		setCorrect(false);
	}

	public boolean isCorrect()
	{
		return correct;
	}

	public void setCorrect(boolean correct)
	{
		this.correct = correct;
	}
}
