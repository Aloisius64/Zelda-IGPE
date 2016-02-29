package guiInterface.scene2D.desert;

import gameLogic.Drawable;
import gameLogic.Update;
import gameLogic.character.Hero;
import gameLogic.scene.desert.QuickSand;

import java.awt.Color;
import java.awt.Graphics;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;

public class QuickSand2D extends QuickSand implements Drawable, Update
{

	public QuickSand2D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.setColor(Color.yellow);
		graphics.fillRect(xPosition, yPosition, width, height);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero.getX() == getX() && hero.getY() == getY())
		{
			StaticConstantGame.gameManager.getConcreteWorld().swapCell(2, resetX, resetY,
					hero.getX(), hero.getY());
			StaticConstantGame.gameManager.getHero().setX(resetX);
			StaticConstantGame.gameManager.getHero().setY(resetY);

			StaticConstantGame.gameManager.getConcreteWorld().setxPrint(
					resetX - (resetX % StaticConstantWorld.X_DIMENSION));
			StaticConstantGame.gameManager.getConcreteWorld().setyPrint(
					resetY - (resetY % StaticConstantWorld.Y_DIMENSION));
			hero.setState(5);
		}
	}
}
