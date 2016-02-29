package eventState;

import guiInterface.Navi;
import staticConstant.StaticConstantGame;
import wiiusej.values.IRSource;
import wiiusej.wiiusejevents.physicalevents.ExpansionEvent;
import wiiusej.wiiusejevents.physicalevents.IREvent;
import wiiusej.wiiusejevents.physicalevents.JoystickEvent;
import wiiusej.wiiusejevents.physicalevents.MotionSensingEvent;
import wiiusej.wiiusejevents.physicalevents.NunchukEvent;
import wiiusej.wiiusejevents.physicalevents.WiimoteButtonsEvent;
import wiiusej.wiiusejevents.utils.WiimoteListener;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.ClassicControllerRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.DisconnectionEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.GuitarHeroRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukInsertedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.NunchukRemovedEvent;
import wiiusej.wiiusejevents.wiiuseapievents.StatusEvent;

import common.Direction;

public class WiiMoteMenuListener implements WiimoteListener
{
	private int xPosition;
	private int yPosition;

	public WiiMoteMenuListener()
	{
		super();
		StaticConstantGame.wiimote.activateIRTRacking();
	}

	@Override
	public void onButtonsEvent(WiimoteButtonsEvent e)
	{

	}

	private void reset()
	{
		Navi navi = StaticConstantGame.navi;
		navi.setxPosition(100);
		navi.setyPosition(100);
	}

	@Override
	public void onIrEvent(IREvent e)
	{
		IRSource[] points = e.getIRPoints();
		int nbPoints = points.length;
		for (int i = 0; i < points.length; i++)
		{
			xPosition = points[i].getRx();
			yPosition = points[i].getRy();
		}

		StaticConstantGame.navi.setxPosition(xPosition);
		StaticConstantGame.navi.setyPosition(yPosition);
		// StaticConstantGame.mainFrame.getContentPane().repaint();
	}

	@Override
	public void onMotionSensingEvent(MotionSensingEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onExpansionEvent(ExpansionEvent e)
	{
		// TODO Auto-generated method stub

	}

	protected int getDirectionJoystick(ExpansionEvent e)
	{
		if (e instanceof NunchukEvent)
		{
			JoystickEvent joystickEvent = ((NunchukEvent) e).getNunchukJoystickEvent();
			float angle = joystickEvent.getAngle();
			float magnitude = joystickEvent.getMagnitude();

			if (magnitude >= 0.8f)
			{
				if (angle > 45 && angle <= 135)
				{
					return Direction.RIGHT;
				}
				else if (angle > 135 && angle < 225)
				{
					return Direction.DOWN;
				}
				else if (angle >= 225 && angle < 315)
				{
					return Direction.LEFT;
				}
				else if ((angle >= 0.1 && angle <= 10) || (angle >= 350 && angle <= 359))
				{
					return Direction.UP;
				}
			}
			return Direction.CENTER;
		}
		return -1;
	}

	@Override
	public void onStatusEvent(StatusEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onDisconnectionEvent(DisconnectionEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onNunchukInsertedEvent(NunchukInsertedEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onNunchukRemovedEvent(NunchukRemovedEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onGuitarHeroInsertedEvent(GuitarHeroInsertedEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onGuitarHeroRemovedEvent(GuitarHeroRemovedEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onClassicControllerInsertedEvent(ClassicControllerInsertedEvent e)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void onClassicControllerRemovedEvent(ClassicControllerRemovedEvent e)
	{
		// TODO Auto-generated method stub

	}

	public int getxPosition()
	{
		return xPosition;
	}

	public int getyPosition()
	{
		return yPosition;
	}

	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}

	protected void stop()
	{
		try
		{
			Thread.sleep(500);
		}
		catch (InterruptedException exception)
		{
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
	}
}
