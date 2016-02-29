/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package eventState;

import gameLogic.character.Hero;
import guiInterface.GamePanel3D;
import guiInterface.character3D.Hero3D;
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

/**
 * 
 * @author Aloisius
 */
public class WiiMoteListener3D implements wiiusej.wiiusejevents.utils.WiimoteListener
{

	private Hero hero;
	private boolean shotModeOn = false;
	private int counterAttack = 0;

	public WiiMoteListener3D()
	{
		hero = StaticConstantGame.gameManager.getHero();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{
		if (e.isButtonBPressed())
		{
			shotModeOn = true;
		}
		else
		{
			shotModeOn = false;
		}

		if (e.isButtonHomeJustPressed())
		{
			System.exit(0);
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
		short y = rawAcceleration.getZ();
		boolean motionDetected = false;

		if (y >= 200 || y <= 50)
		{
			motionDetected = true;
			if (shotModeOn)
			{
				GamePanel3D.setDistanceAttack(true);
			}
			else
			{
				GamePanel3D.setPhysicAttack(true);
				if (counterAttack % 2 == 0)
				{
					((Hero3D) hero).setIndexAnimation(4);
				}
				else
				{
					((Hero3D) hero).setIndexAnimation(7);
				}
			}
		}

		if (x >= 240 || x <= 10)
		{
			motionDetected = true;
			if (shotModeOn)
			{
				GamePanel3D.setDistanceAttack(true);
			}
			else
			{
				GamePanel3D.setPhysicAttack(true);
				if (counterAttack % 2 == 0)
				{
					((Hero3D) hero).setIndexAnimation(5);
				}
				else
				{
					((Hero3D) hero).setIndexAnimation(6);
				}
			}
		}

		if (!motionDetected)
		{
			GamePanel3D.setPhysicAttack(false);
			GamePanel3D.setDistanceAttack(false);
		}

		counterAttack++;
	}

	@Override
	public void onExpansionEvent(ExpansionEvent ev)
	{
		if (ev instanceof NunchukEvent)
		{
			NunchukButtonsEvent buttonsEvent = ((NunchukEvent) ev).getButtonsEvent();
			if (buttonsEvent.isButtonZPressed())
			{
				GamePanel3D.setChangeCameraView(true);
			}
			else
			{
				GamePanel3D.setChangeCameraView(false);
			}

			JoystickEvent joystickEvent = ((NunchukEvent) ev).getNunchukJoystickEvent();
			float angle = joystickEvent.getAngle();
			float magnitude = joystickEvent.getMagnitude();

			if (magnitude >= 0.8f)
			{
				if (angle > 45 && angle <= 135)
				{
					GamePanel3D.setRight(true);
				}
				else if (angle > 135 && angle < 225)
				{
					GamePanel3D.setDown(true);
				}
				else if (angle >= 225 && angle < 315)
				{
					GamePanel3D.setLeft(true);
				}
				else if ((angle >= 0.1 && angle <= 10) || (angle >= 350 && angle <= 359))
				{
					GamePanel3D.setUp(true);
				}
			}
			else
			{
				GamePanel3D.setRight(false);
				GamePanel3D.setLeft(false);
				GamePanel3D.setDown(false);
				GamePanel3D.setUp(false);
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
}
