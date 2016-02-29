package guiInterface.object2D;

import common.ImageProvider;
import gameLogic.Drawable;
import gameLogic.object.StaticPlatform;
import guiInterface.character2D.Hero2D;
import java.awt.Graphics;
import javax.swing.JPanel;
import staticConstant.StaticConstantGame;

public class StaticPlatform2D extends StaticPlatform implements Drawable
{
	private JPanel gamePanel;

	public StaticPlatform2D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
	}

	@Override
	public void drawObject(Graphics graphics, int xPosition, int yPosition, int width, int height)
	{
		graphics.drawImage(ImageProvider.platform, xPosition, yPosition, width, height, null);
	}

	@Override
	public void update()
	{
	}

	public void setGamePanel()
	{
		gamePanel = ((Hero2D) StaticConstantGame.gameManager.getHero()).getPanel();
	}

	public JPanel getGamePanel()
	{
		return gamePanel;
	}

}
