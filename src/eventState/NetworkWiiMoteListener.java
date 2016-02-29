package eventState;

import gameLogic.MultiplayerGameManager;
import gameLogic.character.CharacterState;
import guiInterface.character2D.Hero2D;
import guiInterface.startScreen.MultiplayerGamePanel;
import staticConstant.StaticConstantGame;
import wiiusej.values.RawAcceleration;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.JoystickEvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukButtonsEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;

import common.Direction;

public class NetworkWiiMoteListener extends WiiMoteListener2D
{
	private final MultiplayerGamePanel multiplayerGamePanel;

	public NetworkWiiMoteListener(MultiplayerGamePanel multiplayerGamePanel)
	{
		super();
		this.multiplayerGamePanel = multiplayerGamePanel;
		StaticConstantGame.wiimote.activateMotionSensing();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBHeld())
		{
			setTwistMode(true);
		}
	}

	@Override
	public void onExpansionEvent(ExpansionEvent ev)
	{
		if (ev instanceof NunchukEvent)
		{
			Hero2D hero = null;
			if (multiplayerGamePanel.isPlayerOne())
				hero = (Hero2D) StaticConstantGame.gameManager.getHero();
			else
				hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager)
						.getPlayer2();

			NunchukButtonsEvent buttonsEvent = ((NunchukEvent) ev).getButtonsEvent();
			if (buttonsEvent.isButtonCPressed())
			{
				setShotModeOn(true);
			}
			else
			{
				setShotModeOn(false);
			}

			JoystickEvent joystickEvent = ((NunchukEvent) ev).getNunchukJoystickEvent();
			float angle = joystickEvent.getAngle();
			float magnitude = joystickEvent.getMagnitude();

			if (magnitude >= 0.8f)
			{
				if (angle > 45 && angle <= 135)
				{
					hero.setCurrentDirection(Direction.RIGHT);
					hero.setState(CharacterState.WALK);
				}
				else if (angle > 135 && angle < 225)
				{
					hero.setCurrentDirection(Direction.DOWN);
					hero.setState(CharacterState.WALK);
				}
				else if (angle >= 225 && angle < 315)
				{
					hero.setCurrentDirection(Direction.LEFT);
					hero.setState(CharacterState.WALK);
				}
				else if ((angle >= 0.1 && angle <= 10) || (angle >= 350 && angle <= 359))
				{
					hero.setCurrentDirection(Direction.UP);
					hero.setState(CharacterState.WALK);
				}
			}
			else
			{
				if (hero.getState() != 4)
				{
					hero.setState(0);
				}
			}

			multiplayerGamePanel.dispatch();
			multiplayerGamePanel.repaint();
		}
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent ev)
	{
		Hero2D hero = null;
		if (multiplayerGamePanel.isPlayerOne())
			hero = (Hero2D) StaticConstantGame.gameManager.getHero();
		else
			hero = (Hero2D) ((MultiplayerGameManager) StaticConstantGame.gameManager).getPlayer2();

		RawAcceleration rawAcceleration = ev.getRawAcceleration();
		short x = rawAcceleration.getX();

		if (x >= 200 || x <= 100)
		{
			if (isTwistMode())
			{
				hero.setState(CharacterState.SPECIAL_ATTACK);
				setTwistMode(false);
			}
			else
			{
				if (isShotModeOn())
				{
					hero.setState(CharacterState.DISTANCE_ATTACK);
					try
					{
						Thread.sleep(250);
					}
					catch (InterruptedException exception)
					{
						// TODO Auto-generated catch block
						exception.printStackTrace();
					}
				}
				else
				{
					hero.setState(CharacterState.PHYSIC_ATTACK);
				}
			}
			multiplayerGamePanel.dispatch();
			multiplayerGamePanel.repaint();
			try
			{
				Thread.sleep(250);
			}
			catch (InterruptedException exception)
			{
				// TODO Auto-generated catch block
				exception.printStackTrace();
			}
		}
	}

	public MultiplayerGamePanel getMultiplayerGamePanel()
	{
		return multiplayerGamePanel;
	}

}
