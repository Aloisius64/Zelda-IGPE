package guiInterface.object2D;

import common.ImageProvider;
import common.SoundManager;
import gameLogic.Drawable;
import gameLogic.character.Hero;
import gameLogic.object.MagicGate;
import java.awt.Graphics;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURL;

public class MagicGate2D extends MagicGate implements Drawable
{
	private int indexCurrentImage = 0;
	private boolean nullGate = false;

	public MagicGate2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.magicGate[indexCurrentImage], xPosition, yPosition, width,
				height, null);
	}

	@Override
	public void update()
	{
		super.update();

		Hero hero = StaticConstantGame.gameManager.getHero();

		int distance = (int) Math.sqrt(Math.pow((getX() - hero.getX()), 2)
				+ Math.pow((getY() - hero.getY()), 2));
		if (distance <= 1 && isCorrect())
		{
			SoundManager.playSound(SoundManager.MAGIC_GATE, 1, SoundManager.EFFECT);
		}
		indexCurrentImage = (indexCurrentImage + 1) % StaticConstantURL.MAGIC_GATE.length;

	}

	public void setNullGate(boolean nullGate)
	{
		this.nullGate = nullGate;
	}

	public boolean isNullGate()
	{
		return nullGate;
	}
}
