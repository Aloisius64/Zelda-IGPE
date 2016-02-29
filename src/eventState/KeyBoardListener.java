package eventState;

import gameLogic.character.CharacterState;
import gameLogic.character.Hero;
import guiInterface.GamePanel2D;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;
import guiInterface.startScreen.OptionPanel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;

import common.Direction;

public class KeyBoardListener implements KeyListener
{

	private Hero hero;
	private JPanel panel;
	private boolean typed = false;
	private int counter = 0;

	public KeyBoardListener(JPanel panel)
	{
		this.setPanel(panel);
		this.setHero(StaticConstantGame.gameManager.getHero());
	}

	public Hero getHero()
	{
		return hero;
	}

	public JPanel getPanel()
	{
		return panel;
	}

	@Override
	public void keyPressed(KeyEvent e)
	{
		if (!((GamePanel2D) panel).isPause())
		{
			int c = e.getKeyCode();
			if (c == StaticConstantGame.moveUp)
			{
				hero.setCurrentDirection(Direction.UP);
				hero.setState(CharacterState.WALK);
			}
			else if (c == StaticConstantGame.moveDown)
			{
				hero.setCurrentDirection(Direction.DOWN);
				hero.setState(CharacterState.WALK);
			}
			else if (c == StaticConstantGame.moveLeft)
			{
				hero.setCurrentDirection(Direction.LEFT);
				hero.setState(CharacterState.WALK);
			}
			else if (c == StaticConstantGame.moveRight)
			{
				hero.setCurrentDirection(Direction.RIGHT);
				hero.setState(CharacterState.WALK);
			}

			if (e.getKeyCode() == StaticConstantGame.attack1)
			{
				if (!typed)
				{
					hero.setState(CharacterState.PHYSIC_ATTACK);
					typed = true;
				}
				else
				{
					counter++;
				}

			}
			else if (e.getKeyCode() == StaticConstantGame.attack2)
			{
				hero.setState(CharacterState.DISTANCE_ATTACK);
			}
		}
		else if (((GamePanel2D) panel).isPause())
		{
			int c = e.getKeyCode();
			if (c == KeyEvent.VK_UP)
			{
				((GamePanel2D) panel).addPauseSelected(-1);
			}
			if (c == KeyEvent.VK_DOWN)
			{
				((GamePanel2D) panel).addPauseSelected(1);
			}
			if (c == KeyEvent.VK_ENTER)
			{
				int pauseSelected = ((GamePanel2D) panel).getPauseSelected();
				if (pauseSelected == 1)
				{
					((GamePanel2D) panel).setPause(false);
					StaticConstantGame.paused = false;
				}
				if (pauseSelected == 2)
				{
					StaticConstantGame.gamePanel2D = (GamePanel2D) panel;
					MainFrame mainFrame = ((GamePanel2D) panel).getMainFrame();
					mainFrame.switchPanel(new OptionPanel(mainFrame, null));
				}
				if (pauseSelected == 3)
				{
					MainFrame mainFrame = ((GamePanel2D) panel).getMainFrame();
					mainFrame.switchPanel(new MenuPanel(mainFrame));
					StaticConstantGame.gameRunning = false;
				}
			}
			panel.repaint();
		}
	}

	@Override
	public void keyReleased(KeyEvent e)
	{
		if (hero.getState() != CharacterState.HITTED)
		{
			hero.setState(CharacterState.STAND);
		}

		if (typed && counter > 20)
		{
			hero.setState(CharacterState.SPECIAL_ATTACK);
		}

		typed = false;
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
		if (e.getKeyChar() == 'p')
		{
			StaticConstantGame.paused = !StaticConstantGame.paused;
			((GamePanel2D) panel).setPause(!((GamePanel2D) panel).isPause());
			panel.repaint();
		}
	}

	public void setHero(Hero hero)
	{
		this.hero = hero;
	}

	public void setPanel(JPanel panel)
	{
		this.panel = panel;
	}
}
