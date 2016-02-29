/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventState;

import gameLogic.character.CharacterState;
import guiInterface.character2D.Hero2D;
import staticConstant.StaticConstantGame;
import wiiusej.values.RawAcceleration;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.JoystickEvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukButtonsEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

import common.Direction;

/**
 * 
 * @author Aloisius
 */
public class WiiMoteListener2D implements wiiusej.wiiusejevents.utils.WiimoteListener
{
	private boolean shotModeOn = false;
	private boolean twistMode = false;
	private Hero2D hero;

	public WiiMoteListener2D()
	{
		setHero((Hero2D) StaticConstantGame.gameManager.getHero());
		StaticConstantGame.wiimote.activateMotionSensing();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBHeld())
		{
			twistMode = true;
		}
	}

	@Override
	public void onIrEvent(IREvent e)
	{
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent ev)
	{
		RawAcceleration rawAcceleration = ev.getRawAcceleration();
		short x = rawAcceleration.getX();

		if (x >= 200 || x <= 100)
		{
			if (twistMode)
			{
				getHero().setState(CharacterState.SPECIAL_ATTACK);
				twistMode = false;
			}
			else
			{
				if (shotModeOn)
				{
					getHero().setState(CharacterState.DISTANCE_ATTACK);
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
					getHero().setState(CharacterState.PHYSIC_ATTACK);
				}
			}
			getHero().getPanel().repaint();
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

	@Override
	public void onExpansionEvent(ExpansionEvent ev)
	{
		if (ev instanceof NunchukEvent)
		{
			NunchukButtonsEvent buttonsEvent = ((NunchukEvent) ev).getButtonsEvent();
			if (buttonsEvent.isButtonCPressed())
			{
				shotModeOn = true;
			}
			else
			{
				shotModeOn = false;
			}

			JoystickEvent joystickEvent = ((NunchukEvent) ev).getNunchukJoystickEvent();
			float angle = joystickEvent.getAngle();
			float magnitude = joystickEvent.getMagnitude();

			if (magnitude >= 0.8f)
			{
				if (angle > 45 && angle <= 135)
				{
					getHero().setCurrentDirection(Direction.RIGHT);
					getHero().setState(CharacterState.WALK);
				}
				else if (angle > 135 && angle < 225)
				{
					getHero().setCurrentDirection(Direction.DOWN);
					getHero().setState(CharacterState.WALK);
				}
				else if (angle >= 225 && angle < 315)
				{
					getHero().setCurrentDirection(Direction.LEFT);
					getHero().setState(CharacterState.WALK);
				}
				else if ((angle >= 0.1 && angle <= 10) || (angle >= 350 && angle <= 359))
				{
					getHero().setCurrentDirection(Direction.UP);
					getHero().setState(CharacterState.WALK);
				}
			}
			else
			{
				getHero().setState(CharacterState.STAND);
			}
		}
	}

	@Override
	public void onStatusEvent(StatusEvent e)
	{
	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent e)
	{
	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent e)
	{
	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent e)
	{
	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent e)
	{
	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent e)
	{
	}

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent e)
	{
	}

	@Override
	public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent e)
	{
	}

	public Hero2D getHero()
	{
		return hero;
	}

	public void setHero(Hero2D hero)
	{
		this.hero = hero;
	}

	public boolean isShotModeOn()
	{
		return shotModeOn;
	}

	public boolean isTwistMode()
	{
		return twistMode;
	}

	public void setShotModeOn(boolean shotModeOn)
	{
		this.shotModeOn = shotModeOn;
	}

	public void setTwistMode(boolean twistMode)
	{
		this.twistMode = twistMode;
	}
}
