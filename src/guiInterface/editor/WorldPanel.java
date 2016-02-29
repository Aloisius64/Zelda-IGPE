package guiInterface.editor;

import gameLogic.Drawable;
import gameLogic.Editable;
import gameLogic.ObjectScene;
import gameLogic.character.AbstractCharacter;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.scene.Empty;
import gameLogic.scene.XLand;
import gameLogic.scene.desert.QuickSand;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import guiInterface.character2D.Character2D;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

import staticConstant.StaticConstantWorld;

public class WorldPanel extends JTabbedPane
{
	private class AscoltaMouse extends MouseAdapter
	{
		private boolean leftButton = false;

		@Override
		public void mouseClicked(MouseEvent ev)
		{
			if (ev.getButton() == MouseEvent.BUTTON3)
			{
				leftButton = !leftButton;
			}
			else
			{
				int y = (ev.getX() / (xSize / 10)) + world.getyPrint();
				int x = (ev.getY() / (ySize / 10)) + world.getxPrint();
				assignCell(x, y);
				frame.repaint();
			}
		}

		@Override
		public void mouseMoved(MouseEvent ev)
		{
			if (leftButton)
			{
				int y = (ev.getX() / (xSize / 10)) + world.getyPrint();
				int x = (ev.getY() / (ySize / 10)) + world.getxPrint();
				assignCell(x, y);
			}
			getInfoPanel().setxPosition(ev.getX());
			getInfoPanel().setyPosition(ev.getY());
			frame.repaint();
		}
	}

	private JPanel panel_base_world;
	private JPanel panel_object_world;
	private JPanel panel_character_world;

	private final int xSize = 550;
	private final int ySize = 550;

	private final ConcreteWorld world;
	private final InfoPanel infoPanel;
	private final JFrame frame;

	// costante per il bordo del pannello mondo
	private int resizeX = 3;
	private int resizeY = 2;

	public WorldPanel(final ConcreteWorld world, InfoPanel infoPanel, JFrame frame)
	{
		super();
		this.world = world;
		this.infoPanel = infoPanel;
		this.frame = frame;
		AscoltaMouse ascoltaMouse = new AscoltaMouse();
		createPanels(world, ascoltaMouse);
		createTabbed();
	}

	private void assignCell(int x, int y)
	{
		ObjectScene objectScene = ((EditorJFrame) frame).getObjectSelected();

		if (objectScene instanceof Editable)
		{
			Editable object = (Editable) objectScene;
			if (object.canAdd(x, y))
			{
				if (object instanceof AbstractObjectScene)
				{
					Editable oldObject = world.getCellBaseWorld(x, y);
					oldObject.manageRemotion(world, x, y);
					world.setCellBaseWorld(x, y, ((AbstractObjectScene) object));
					object.manageAddition(world, x, y);
				}
				else if (object instanceof AbstractUtilityObject)
				{
					world.setCellObjectWorld(x, y, ((AbstractUtilityObject) object));
				}
				else if (object instanceof AbstractCharacter)
				{
					world.setCellCharacterWorld(x, y, ((AbstractCharacter) object));
				}
			}
		}
	}

	private void createPanels(final ConcreteWorld world, AscoltaMouse ascoltaMouse)
	{
		panel_base_world = new JPanel()
		{

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				printGrid(g);
				paintWorld(g, world, true, false, false);

				// paintWorld(g, world, true, false, false);
				// paintWorld2(g, world);
				printGrid(g);
			};
		};
		panel_base_world.addMouseListener(ascoltaMouse);
		panel_base_world.addMouseMotionListener(ascoltaMouse);

		panel_object_world = new JPanel()
		{

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				printGrid(g);
				paintWorld(g, world, true, true, false);
				printGrid(g);
			};
		};
		panel_object_world.addMouseListener(ascoltaMouse);
		panel_object_world.addMouseMotionListener(ascoltaMouse);

		panel_character_world = new JPanel()
		{

			@Override
			protected void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				printGrid(g);
				paintWorld(g, world, true, true, true);
				printGrid(g);
			};
		};
		panel_character_world.addMouseListener(ascoltaMouse);
		panel_character_world.addMouseMotionListener(ascoltaMouse);
	}

	private void createTabbed()
	{
		// path delle immagini
		URL icon_green_url = null;
		URL icon_yellow_url = null;
		URL icon_red_url = null;
		try
		{
			icon_green_url = new File("./Images/Editor/base.png").toURI().toURL();
			icon_yellow_url = new File("./Images/Editor/object.png").toURI().toURL();
			icon_red_url = new File("./Images/Editor/character.png").toURI().toURL();
		}
		catch (MalformedURLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// immagini da visualizzare
		ImageIcon icon_green = new ImageIcon(icon_green_url);
		ImageIcon icon_yellow = new ImageIcon(icon_yellow_url);
		ImageIcon icon_red = new ImageIcon(icon_red_url);

		// creo il container a tab
		setPreferredSize(new Dimension(xSize, ySize));
		addTab("Base World...", icon_green, panel_base_world, "The whole wold scene");
		addTab("Object World...", icon_yellow, panel_object_world, "Object's world");
		addTab("Character...", icon_red, panel_character_world, "Character's world");

		setMnemonicAt(0, KeyEvent.VK_B);
		setMnemonicAt(1, KeyEvent.VK_O);
		setMnemonicAt(2, KeyEvent.VK_C);
	}

	private void paintWorld(Graphics g, World currentWorld, boolean baseWorld, boolean objectWorld,
			boolean characterWorld)
	{
		int widthSize = xSize / 10 - resizeX;
		int heightSize = (ySize / 10) - resizeY;

		int x = world.getxPrint();
		int y = world.getyPrint();
		int xLimit = x + StaticConstantWorld.X_DIMENSION;
		int yLimit = y + StaticConstantWorld.Y_DIMENSION;

		// System.out.println("World Panel, paint world: " + x + " " + y);

		for (x = world.getxPrint(); x < xLimit; x++)
		{
			for (y = world.getyPrint(); y < yLimit; y++)
			{

				int newX = (x % StaticConstantWorld.X_DIMENSION);
				int newY = (y % StaticConstantWorld.Y_DIMENSION);

				if (baseWorld)
				{
					AbstractObjectScene baseCell = currentWorld.getCellBaseWorld(x, y);
					if (baseCell instanceof Drawable)
					{
						((Drawable) baseCell).drawObject(g, newY * widthSize, newX * heightSize,
								widthSize, heightSize);
					}
				}

			}
		}

		for (x = world.getxPrint(); x < xLimit; x++)
		{
			for (y = world.getyPrint(); y < yLimit; y++)
			{

				int newX = (x % StaticConstantWorld.X_DIMENSION);
				int newY = (y % StaticConstantWorld.Y_DIMENSION);

				if (objectWorld)
				{
					AbstractUtilityObject objectCell = currentWorld.getCellObjectWorld(x, y);
					if (objectCell instanceof Drawable)
					{
						((Drawable) objectCell).drawObject(g, newY * widthSize, newX * heightSize,
								widthSize, heightSize);
					}
				}

			}
		}

		for (x = world.getxPrint(); x < xLimit; x++)
		{
			for (y = world.getyPrint(); y < yLimit; y++)
			{

				int newX = (x % StaticConstantWorld.X_DIMENSION);
				int newY = (y % StaticConstantWorld.Y_DIMENSION);

				if (characterWorld)
				{
					AbstractCharacter characterCell = currentWorld.getCellCharacterWorld(x, y);
					if (characterCell instanceof Character2D)
					{
						((Character2D) characterCell).drawObject(g, newY * widthSize, newX
								* heightSize, widthSize, heightSize);
					}

				}

			}
		}

	}

	private void paintWorld2(Graphics g, World currentWorld)
	{
		int widthSize = xSize / 10 - resizeX;
		int heightSize = (ySize / 10) - resizeY;

		int x = world.getxPrint();
		int y = world.getyPrint();
		int xLimit = x + StaticConstantWorld.X_DIMENSION;
		int yLimit = y + StaticConstantWorld.Y_DIMENSION;

		// System.out.println("World Panel, paint world: " + x + " " + y);

		for (x = world.getxPrint(); x < xLimit; x++)
		{
			for (y = world.getyPrint(); y < yLimit; y++)
			{

				int newX = (x % StaticConstantWorld.X_DIMENSION);
				int newY = (y % StaticConstantWorld.Y_DIMENSION);

				AbstractObjectScene baseCell = currentWorld.getCellBaseWorld(x, y);
				if (baseCell instanceof Drawable)
				{
					if (baseCell instanceof XLand || baseCell instanceof Empty
							|| baseCell instanceof QuickSand)
						((Drawable) baseCell).drawObject(g, newY * widthSize, newX * heightSize,
								widthSize, heightSize);
				}

			}
		}

	}

	private void printGrid(Graphics g)
	{
		int widthSize = (xSize / StaticConstantWorld.X_DIMENSION) - resizeX;
		int heightSize = (ySize / StaticConstantWorld.Y_DIMENSION) - resizeY;
		// g.setColor(Color.black);
		// g.fillRect(0, 0, xSize, ySize);
		g.setColor(Color.orange);

		for (int x = 0; x <= StaticConstantWorld.X_DIMENSION; x++)
		{
			g.drawLine(x * widthSize, 0, x * widthSize, ySize);
			g.drawLine(0, x * heightSize, ySize, x * heightSize);
		}
	}

	public InfoPanel getInfoPanel()
	{
		return infoPanel;
	}

	public int getResizeX()
	{
		return resizeX;
	}

	public int getResizeY()
	{
		return resizeY;
	}

	public void setResizeX(int resizeX)
	{
		this.resizeX = resizeX;
	}

	public void setResizeY(int resizeY)
	{
		this.resizeY = resizeY;
	}

}
