package threadState;

import gameLogic.Update;
import gameLogic.character.AttackSphere;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.swing.JPanel;

import staticConstant.StaticConstantGame;

public class SphereManager implements Runnable
{

	private List<AttackSphere> spheres;
	private Thread manager;
	private boolean removed = false;
	private final JPanel gamePanel;

	public SphereManager(JPanel gamePanel)
	{
		this.gamePanel = gamePanel;
		spheres = new ArrayList<AttackSphere>();
	}

	public SphereManager(JPanel gamePanel, List<AttackSphere> spheres)
	{
		this.gamePanel = gamePanel;
		this.spheres = spheres;
		this.spheres = new ArrayList<AttackSphere>();
	}

	@Override
	public void run()
	{
		while (StaticConstantGame.gameRunning)
		{
			if (!StaticConstantGame.paused)
			{
				CopyOnWriteArrayList<AttackSphere> printSpheres = new CopyOnWriteArrayList<AttackSphere>();
				printSpheres.addAll(spheres);
				Iterator<AttackSphere> iterator = printSpheres.iterator();
				while (iterator.hasNext() && !isRemoved())
				{
					AttackSphere sphere = iterator.next();
					if (sphere instanceof Update)
					{
						((Update) sphere).update();
					}
				}
				gamePanel.repaint();
			}
			try
			{
				Thread.sleep(10);
			}
			catch (InterruptedException exception)
			{
				exception.printStackTrace();
			}
			if (removed)
			{
				removed = false;
			}
		}
	}

	public void setSpheres(List<AttackSphere> spheres)
	{
		this.spheres = spheres;
	}

	public void start()
	{
		setManager(new Thread(this, "Sphere Manager"));
		getManager().start();
	}

	public List<AttackSphere> getSpheres()
	{
		return spheres;
	}

	public void addSphere(AttackSphere sphere)
	{
		spheres.add(sphere);
	}

	public void removeSphere(AttackSphere sphere)
	{
		spheres.remove(sphere);
		removed = true;
	}

	public void setRemoved(boolean removed)
	{
		this.removed = removed;
	}

	public boolean isRemoved()
	{
		return removed;
	}

	public JPanel getGamePanel()
	{
		return gamePanel;
	}

	public Thread getManager()
	{
		return manager;
	}

	public void setManager(Thread manager)
	{
		this.manager = manager;
	}
}
