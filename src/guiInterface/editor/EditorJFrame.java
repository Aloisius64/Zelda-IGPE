package guiInterface.editor;

import gameLogic.Drawable;
import gameLogic.ObjectScene;
import gameLogic.character.EmptyCharacter;
import gameLogic.object.EmptyUtility;
import gameLogic.object.StartPoint;
import gameLogic.scene.Empty;
import gameLogic.world.ConcreteWorld;
import gameLogic.world.World;
import gameLogic.world.WorldManager;
import guiInterface.character2D.AdvancedAttackSphere2D;
import guiInterface.character2D.AttackSphere2D;
import guiInterface.character2D.EmptyCharacter2D;
import guiInterface.character2D.Ganondorf2D;
import guiInterface.character2D.Hero2D;
import guiInterface.character2D.Skull2D;
import guiInterface.character2D.Warrior2D;
import guiInterface.character2D.Wizard2D;
import guiInterface.object2D.Block2D;
import guiInterface.object2D.Button2D;
import guiInterface.object2D.Coin2D;
import guiInterface.object2D.EmptyUtility2D;
import guiInterface.object2D.FallPoint2D;
import guiInterface.object2D.FinalPoint2D;
import guiInterface.object2D.FlameTower2D;
import guiInterface.object2D.Flower2D;
import guiInterface.object2D.Gate2D;
import guiInterface.object2D.Heart2D;
import guiInterface.object2D.InvisibleWall2D;
import guiInterface.object2D.MagicGate2D;
import guiInterface.object2D.MagicSphere2D;
import guiInterface.object2D.Platform2D;
import guiInterface.object2D.SkullHead2D;
import guiInterface.object2D.StartPoint2D;
import guiInterface.object2D.StaticPlatform2D;
import guiInterface.object2D.Treasure2D;
import guiInterface.object2D.TriforcePiece2D;
import guiInterface.scene2D.Empty2D;
import guiInterface.scene2D.HouseLarge2D;
import guiInterface.scene2D.HouseSmall2D;
import guiInterface.scene2D.MarbleScaleDown2D;
import guiInterface.scene2D.MarbleScaleLeft2D;
import guiInterface.scene2D.MarbleScaleRight2D;
import guiInterface.scene2D.MarbleScaleUp2D;
import guiInterface.scene2D.Statue2D;
import guiInterface.scene2D.SwordTrone2D;
import guiInterface.scene2D.TowerRoyal2D;
import guiInterface.scene2D.Tree2D;
import guiInterface.scene2D.TreeGreen2D;
import guiInterface.scene2D.TreeViolet2D;
import guiInterface.scene2D.Trunk2D;
import guiInterface.scene2D.Wall2D;
import guiInterface.scene2D.XLand2D;
import guiInterface.scene2D.desert.QuickSand2D;
import guiInterface.scene2D.forest.DiscesaDown2D;
import guiInterface.scene2D.forest.DiscesaLeft2D;
import guiInterface.scene2D.forest.DiscesaRight2D;
import guiInterface.scene2D.forest.DiscesaUp2D;
import guiInterface.scene2D.forest.Land2D;
import guiInterface.scene2D.forest.LandTerrain2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownLeftInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownLeftOutside2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownRightInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerDownRightOutside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpLeftInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpLeftOutside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpRightInside2D;
import guiInterface.scene2D.forest.LandTerrainCornerUpRightOutside2D;
import guiInterface.scene2D.forest.LandTerrainDown2D;
import guiInterface.scene2D.forest.LandTerrainLeft2D;
import guiInterface.scene2D.forest.LandTerrainRight2D;
import guiInterface.scene2D.forest.LandTerrainUp2D;
import guiInterface.scene2D.forest.MountainCornerLeftDown2D;
import guiInterface.scene2D.forest.MountainCornerLeftDownInside2D;
import guiInterface.scene2D.forest.MountainCornerLeftUp2D;
import guiInterface.scene2D.forest.MountainCornerLeftUpInside2D;
import guiInterface.scene2D.forest.MountainCornerRightDown2D;
import guiInterface.scene2D.forest.MountainCornerRightDownInside2D;
import guiInterface.scene2D.forest.MountainCornerRightUp2D;
import guiInterface.scene2D.forest.MountainCornerRightUpInside2D;
import guiInterface.scene2D.forest.MountainDown2D;
import guiInterface.scene2D.forest.MountainLeft2D;
import guiInterface.scene2D.forest.MountainRight2D;
import guiInterface.scene2D.forest.MountainUp2D;
import guiInterface.scene2D.forest.Water2D;
import guiInterface.scene2D.forest.WaterAngleDownLeft2D;
import guiInterface.scene2D.forest.WaterAngleDownRight2D;
import guiInterface.scene2D.forest.WaterAngleUpLeft2D;
import guiInterface.scene2D.forest.WaterAngleUpRight2D;
import guiInterface.scene2D.forest.WaterBorderDown2D;
import guiInterface.scene2D.forest.WaterBorderLeft2D;
import guiInterface.scene2D.forest.WaterBorderRight2D;
import guiInterface.scene2D.forest.WaterBorderUp2D;
import guiInterface.startScreen.MainFrame;
import guiInterface.startScreen.MenuPanel;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.StringTokenizer;

import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantURL;
import staticConstant.StaticConstantWorld;

import common.ImageProvider;

public class EditorJFrame extends JFrame
{
	// campi grafici
	private TreePanel treePanel;
	private JPanel previewPanel;
	private WorldPanel worldPanel;
	private InfoPanel infoPanel;
	private JSplitPane mainSplitPane;
	private JSplitPane splitPaneTreeAndPreview;
	private final MyMenu menu;
	private JPanel containerWorldInfoPanel;
	private JScrollPane scollJTree;

	private JButton buttonUp;
	private JButton buttonDown;
	private JButton buttonRight;
	private JButton buttonLeft;
	private JPanel containerWorldPanelAndButtons;

	// preview
	private ObjectScene objectSelected;
	private String name;
	private BoxLayout box_layout;

	// campi logici
	private WorldManager worldManager;
	private ConcreteWorld world;
	private int widthWorld = StaticConstantWorld.X_DIMENSION;
	private int heightWorld = StaticConstantWorld.Y_DIMENSION;
	private final String lookAndFeelSelected = "";

	// utile per il caricamento dinamico delle classi
	// private final ExtensibleClassLoader cl = new ExtensibleClassLoader(new
	// URL[] {}, Thread.currentThread().getContextClassLoader());

	public EditorJFrame()
	{
		super();
		// loadClassObject();

		// per il caricamento delle immagini
		new ImageProvider();

		// impostazioni della finestra
		setSettings();

		// grafica migliore
		setLookAndFeel("Nimbus");

		// imposto barra dei menu
		menu = new MyMenu(this);
		setJMenuBar(menu);

		setImageTitle(StaticConstantURL.editorLogo);
		setVisible(true);
	}

	public EditorJFrame(String nameLevel) throws Exception
	{
		super();

		// per il caricamento delle immagini
		new ImageProvider();

		// impostazioni della finestra
		setSettings();

		// imposto barra dei menu
		menu = new MyMenu(this);
		setJMenuBar(menu);

		// inizializzo il mondo
		worldManager = new WorldManager(nameLevel);
		this.world = worldManager.getCurrentWorld();

		startMainFrame();

		// grafica migliore
		setLookAndFeel("Nimbus");
		setResizeParameters("Nimbus");
	}

	public EditorJFrame(World world)
	{
		super();
		// loadClassObject();

		// per il caricamento delle immagini
		new ImageProvider();

		// impostazioni della finestra
		setSettings();

		// imposto barra dei menu
		menu = new MyMenu(this);
		setJMenuBar(menu);

		this.world = (ConcreteWorld) world;
		initEmptyWorld();

		startMainFrame();

		// grafica migliore
		// setLookAndFeel("Nimbus");
		// setResizeParameters("Nimbus");
	}

	private void assignListenerToButtons()
	{
		buttonUp.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ev)
			{
				int x = world.getxPrint();
				if (x >= StaticConstantWorld.X_DIMENSION)
				{
					world.moveToUp();
					buttonDown.setEnabled(true);
					if (x <= StaticConstantWorld.X_DIMENSION)
					{
						buttonUp.setEnabled(false);
					}
					repaint();
				}
			}
		});
		buttonDown.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ev)
			{
				int x = world.getxPrint();
				int distance = world.getHeight() - StaticConstantWorld.X_DIMENSION;
				if (x < distance)
				{
					world.moveToDown();
					buttonUp.setEnabled(true);
					if (world.getxPrint() >= distance)
					{
						buttonDown.setEnabled(false);
					}
					repaint();
				}
			}
		});
		buttonLeft.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ev)
			{
				int y = world.getyPrint();
				if (y >= StaticConstantWorld.Y_DIMENSION)
				{
					world.moveToLeft();
					buttonRight.setEnabled(true);
					if (y <= StaticConstantWorld.Y_DIMENSION)
					{
						buttonLeft.setEnabled(false);
					}
					repaint();
				}
			}
		});
		buttonRight.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent ev)
			{
				int y = world.getyPrint();
				int distance = world.getWidth() - StaticConstantWorld.Y_DIMENSION;
				if (y < distance)
				{
					world.moveToRight();
					buttonLeft.setEnabled(true);
					if (world.getyPrint() >= distance)
					{
						buttonRight.setEnabled(false);
					}
					repaint();
				}
			}
		});
	}

	private void createButtons()
	{
		// creo i componenti grafici
		buttonUp = new JButton("Up");
		if (world.getxPrint() < StaticConstantWorld.X_DIMENSION)
		{
			buttonUp.setEnabled(false);
		}
		buttonDown = new JButton("Down");
		if (world.getxPrint() >= (world.getHeight() - StaticConstantWorld.X_DIMENSION))
		{
			buttonDown.setEnabled(false);
		}
		buttonRight = new JButton("Right");
		if (world.getyPrint() >= (world.getWidth() - StaticConstantWorld.Y_DIMENSION))
		{
			buttonRight.setEnabled(false);
		}
		buttonLeft = new JButton("Left");
		if (world.getyPrint() <= StaticConstantWorld.Y_DIMENSION)
		{
			buttonLeft.setEnabled(false);
		}
	}

	private void createMainPanelLayout()
	{
		containerWorldPanelAndButtons = new JPanel();

		createButtons();

		GroupLayout group_layout = new GroupLayout(containerWorldPanelAndButtons);

		containerWorldPanelAndButtons.setLayout(group_layout); // imposto
																// dimensione e
																// layout
																// manager per
																// il pannello
		group_layout.setAutoCreateGaps(true); // creo un gap automatico tra i
												// componenti
		// creo un gap automatico tra i componenti e i lati del container
		group_layout.setAutoCreateContainerGaps(true);

		// definisco i criteri di allineamento
		GroupLayout.Alignment h_align = GroupLayout.Alignment.CENTER;
		GroupLayout.Alignment v_align = GroupLayout.Alignment.CENTER;

		// creo il gruppo sequenziale orizzontale
		GroupLayout.SequentialGroup for_horizontal = group_layout.createSequentialGroup();

		// aggiungo i gruppi
		for_horizontal.addGroup(group_layout.createParallelGroup(h_align).addComponent(buttonLeft));
		for_horizontal.addGroup(group_layout.createParallelGroup(h_align).addComponent(buttonUp)
				.addComponent(worldPanel).addComponent(buttonDown));
		for_horizontal
				.addGroup(group_layout.createParallelGroup(h_align).addComponent(buttonRight));
		group_layout.setHorizontalGroup(for_horizontal); // lo imposto

		// creo il gruppo sequenziale verticale
		GroupLayout.SequentialGroup for_vertical = group_layout.createSequentialGroup();

		// aggiungo i gruppi
		for_vertical.addGroup(group_layout.createParallelGroup(v_align).addComponent(buttonUp));
		for_vertical.addGroup(group_layout.createParallelGroup(v_align).addComponent(buttonLeft)
				.addComponent(worldPanel).addComponent(buttonRight));
		for_vertical.addGroup(group_layout.createParallelGroup(v_align).addComponent(buttonDown));
		group_layout.setVerticalGroup(for_vertical); // lo imposto

		add(containerWorldPanelAndButtons, BorderLayout.CENTER);
	}

	private ObjectScene getObject(String str)
	{
		if ((str == null) || (str.equals("forest")) || (str.equals("Scene"))
				|| (str.equals("[Root")) || (str.equals("Object")) || str.equals("Character"))
		{
			return null;
		}

		if (str.equals("Land"))
		{
			return new Land2D(0, 0);
		}
		else if (str.equals("Wall"))
		{
			return new Wall2D(0, 0);
		}
		else if (str.equals("Empty"))
		{
			return new Empty2D(0, 0);
		}
		else if (str.equals("HouseLarge"))
		{
			return new HouseLarge2D(0, 0);
		}
		else if (str.equals("HouseSmall"))
		{
			return new HouseSmall2D(0, 0);
		}
		else if (str.equals("MarbleScaleDown"))
		{
			return new MarbleScaleDown2D(0, 0);
		}
		else if (str.equals("MarbleScaleLeft"))
		{
			return new MarbleScaleLeft2D(0, 0);
		}
		else if (str.equals("MarbleScaleRight"))
		{
			return new MarbleScaleRight2D(0, 0);
		}
		else if (str.equals("MarbleScaleUp"))
		{
			return new MarbleScaleUp2D(0, 0);
		}
		else if (str.equals("SkullHead"))
		{
			return new SkullHead2D(0, 0);
		}
		else if (str.equals("Statue"))
		{
			return new Statue2D(0, 0);
		}
		else if (str.equals("SwordTrone"))
		{
			return new SwordTrone2D(0, 0);
		}
		else if (str.equals("TowerRoyal"))
		{
			return new TowerRoyal2D(0, 0);
		}
		else if (str.equals("Tree"))
		{
			return new Tree2D(0, 0);
		}
		else if (str.equals("TreeGreen"))
		{
			return new TreeGreen2D(0, 0);
		}
		else if (str.equals("TreeViolet"))
		{
			return new TreeViolet2D(0, 0);
		}
		else if (str.equals("Trunk"))
		{
			return new Trunk2D(0, 0);
		}
		else if (str.equals("XLand"))
		{
			return new XLand2D(0, 0);
		}
		else if (str.equals("QuickSand"))
		{
			return new QuickSand2D(0, 0);
		}
		else if (str.equals("DiscesaDown"))
		{
			return new DiscesaDown2D(0, 0);
		}
		else if (str.equals("DiscesaLeft"))
		{
			return new DiscesaLeft2D(0, 0);
		}
		else if (str.equals("DiscesaRight"))
		{
			return new DiscesaRight2D(0, 0);
		}
		else if (str.equals("DiscesaUp"))
		{
			return new DiscesaUp2D(0, 0);
		}
		else if (str.equals("LandTerrain"))
		{
			return new LandTerrain2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerDownLeftInside"))
		{
			return new LandTerrainCornerDownLeftInside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerDownLeftOutside"))
		{
			return new LandTerrainCornerDownLeftOutside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerDownRightInside"))
		{
			return new LandTerrainCornerDownRightInside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerDownRightOutside"))
		{
			return new LandTerrainCornerDownRightOutside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerUpLeftInside"))
		{
			return new LandTerrainCornerUpLeftInside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerUpLeftOutside"))
		{
			return new LandTerrainCornerUpLeftOutside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerUpRightInside"))
		{
			return new LandTerrainCornerUpRightInside2D(0, 0);
		}
		else if (str.equals("LandTerrainCornerUpRightOutside"))
		{
			return new LandTerrainCornerUpRightOutside2D(0, 0);
		}
		else if (str.equals("LandTerrainDown"))
		{
			return new LandTerrainDown2D(0, 0);
		}
		else if (str.equals("LandTerrainUp"))
		{
			return new LandTerrainUp2D(0, 0);
		}
		else if (str.equals("LandTerrainLeft"))
		{
			return new LandTerrainLeft2D(0, 0);
		}
		else if (str.equals("LandTerrainRight"))
		{
			return new LandTerrainRight2D(0, 0);
		}
		else if (str.equals("MountainCornerLeftDown"))
		{
			return new MountainCornerLeftDown2D(0, 0);
		}
		else if (str.equals("MountainCornerRightDown"))
		{
			return new MountainCornerRightDown2D(0, 0);
		}
		else if (str.equals("MountainCornerLeftUp"))
		{
			return new MountainCornerLeftUp2D(0, 0);
		}
		else if (str.equals("MountainCornerRightUp"))
		{
			return new MountainCornerRightUp2D(0, 0);
		}
		else if (str.equals("MountainCornerLeftDownInside"))
		{
			return new MountainCornerLeftDownInside2D(0, 0);
		}
		else if (str.equals("MountainCornerRightDownInside"))
		{
			return new MountainCornerRightDownInside2D(0, 0);
		}
		else if (str.equals("MountainCornerLeftUpInside"))
		{
			return new MountainCornerLeftUpInside2D(0, 0);
		}
		else if (str.equals("MountainCornerRightUpInside"))
		{
			return new MountainCornerRightUpInside2D(0, 0);
		}
		else if (str.equals("MountainDown"))
		{
			return new MountainDown2D(0, 0);
		}
		else if (str.equals("MountainUp"))
		{
			return new MountainUp2D(0, 0);
		}
		else if (str.equals("MountainLeft"))
		{
			return new MountainLeft2D(0, 0);
		}
		else if (str.equals("MountainRight"))
		{
			return new MountainRight2D(0, 0);
		}
		else if (str.equals("Water"))
		{
			return new Water2D(0, 0);
		}
		else if (str.equals("WaterAngleDownLeft"))
		{
			return new WaterAngleDownLeft2D(0, 0);
		}
		else if (str.equals("WaterAngleDownRight"))
		{
			return new WaterAngleDownRight2D(0, 0);
		}
		else if (str.equals("WaterAngleUpLeft"))
		{
			return new WaterAngleUpLeft2D(0, 0);
		}
		else if (str.equals("WaterAngleUpRight"))
		{
			return new WaterAngleUpRight2D(0, 0);
		}
		else if (str.equals("WaterBorderDown"))
		{
			return new WaterBorderDown2D(0, 0);
		}
		else if (str.equals("WaterBorderLeft"))
		{
			return new WaterBorderLeft2D(0, 0);
		}
		else if (str.equals("WaterBorderRight"))
		{
			return new WaterBorderRight2D(0, 0);
		}
		else if (str.equals("WaterBorderUp"))
		{
			return new WaterBorderUp2D(0, 0);
		}
		else if (str.equals("Button"))
		{
			return new Button2D(0, 0);
		}
		else if (str.equals("Block"))
		{
			return new Block2D(0, 0);
		}
		else if (str.equals("Coin"))
		{
			return new Coin2D(0, 0);
		}
		else if (str.equals("EmptyUtility"))
		{
			return new EmptyUtility2D(0, 0);
		}
		else if (str.equals("FinalPoint"))
		{
			return new FinalPoint2D(0, 0);
		}
		else if (str.equals("FlameTower"))
		{
			return new FlameTower2D(0, 0);
		}
		else if (str.equals("Flower"))
		{
			return new Flower2D(0, 0);
		}
		else if (str.equals("Gate"))
		{
			return new Gate2D(0, 0);
		}
		else if (str.equals("Heart"))
		{
			return new Heart2D(0, 0);
		}
		else if (str.equals("InvisibleWall"))
		{
			return new InvisibleWall2D(0, 0);
		}
		else if (str.equals("MagicSphere"))
		{
			return new MagicSphere2D(0, 0);
		}
		else if (str.equals("Platform"))
		{
			return new Platform2D(0, 0);
		}
		else if (str.equals("StartPoint"))
		{
			return new StartPoint2D(0, 0);
		}
		else if (str.equals("Treasure"))
		{
			return new Treasure2D(0, 0);
		}
		else if (str.equals("StaticPlatform"))
		{
			return new StaticPlatform2D(0, 0);
		}
		else if (str.equals("MagicGate"))
		{
			return new MagicGate2D(0, 0);
		}
		else if (str.equals("FallPoint"))
		{
			return new FallPoint2D(0, 0);
		}
		else if (str.equals("TriforcePiece"))
		{
			return new TriforcePiece2D(0, 0);
		}
		else if (str.equals("AdvancedAttackSphere"))
		{
			return new AdvancedAttackSphere2D(0, 0);
		}
		else if (str.equals("AttackSphere"))
		{
			return new AttackSphere2D(0, 0);
		}
		else if (str.equals("EmptyCharacter"))
		{
			return new EmptyCharacter2D(0, 0);
		}
		else if (str.equals("Ganondorf"))
		{
			return new Ganondorf2D(0, 0);
		}
		else if (str.equals("Hero"))
		{
			return new Hero2D();
		}
		else if (str.equals("Skull"))
		{
			return new Skull2D(0, 0);
		}
		else if (str.equals("Warrior"))
		{
			return new Warrior2D(0, 0);
		}
		else if (str.equals("Wizard"))
		{
			return new Wizard2D(0, 0);
		}
		else
		{
			return null;
		}
	}

	private void initEmptyWorld()
	{
		for (int i = 0; i < world.getWidth(); i++)
		{
			for (int j = 0; j < world.getHeight(); j++)
			{
				world.setCellBaseWorld(i, j, new Empty(i, j));
				world.setCellObjectWorld(i, j, new EmptyUtility(i, j));
				world.setCellCharacterWorld(i, j, new EmptyCharacter(i, j));
			}
		}
	}

	private void initPreviewPanel(String nameObject)
	{
		ObjectScene object = getObject(nameObject);
		if (object != null)
		{
			objectSelected = object;

			String str = objectSelected.getClass().getName();
			StringTokenizer strToken = new StringTokenizer(str, ".");
			if (strToken.hasMoreElements())
			{
				while (strToken.hasMoreElements())
				{
					str = strToken.nextToken();
				}
			}

			name = str;
		}
		else
		{
			objectSelected = new Empty2D(0, 0);

			// Verifico il nome dell'oggetto scelto
			String str = objectSelected.getClass().getName();
			StringTokenizer strToken = new StringTokenizer(str, ".");
			if (strToken.hasMoreElements())
			{
				while (strToken.hasMoreElements())
				{
					str = strToken.nextToken();
				}
			}
			name = str;
		}
	}

	/*
	 * private void loadClassObject() { final File sceneDirectory = new
	 * File("ObjectSceneJAR"); final File[] listFiles =
	 * sceneDirectory.listFiles();
	 * 
	 * for (final File f : listFiles) { final String name = f.getName(); try {
	 * if (name.endsWith(".class")) { cl.addURL(f.toURI().toURL()); } } catch
	 * (final Exception e) { JOptionPane.showMessageDialog(this,
	 * "Malformed URL Exception in EditorJFrame.loadClassObject"); } } }
	 */
	private void setImageTitle(String name)
	{
		// Immagine titolo
		URL urlTitleIcon = null;
		Image imageTitle = null;

		try
		{
			urlTitleIcon = (new File("./Images/Editor/" + name)).toURI().toURL();
			imageTitle = (new ImageIcon(urlTitleIcon)).getImage();
			setIconImage(imageTitle);
		}
		catch (MalformedURLException exception)
		{
			exception.printStackTrace();
		}
	}

	private void setSettings()
	{
		setTitle("ZeldaEditor");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(1080, 770);
		setResizable(true);
		setLocationRelativeTo(null);
	}

	private void startMainFrame()
	{
		treePanel = new TreePanel(this);
		previewPanel = new JPanel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);
				int xPosition = getWidth() / 4;
				int yPosition = getHeight() / 4;

				Font font = new Font("Times", Font.BOLD, 16);
				Graphics2D g2 = (Graphics2D) g;
				g2.setFont(font);
				FontRenderContext frc = g2.getFontRenderContext();
				TextLayout nameObject = new TextLayout(name, font, frc);
				nameObject.draw(g2, 20, 20);

				if (objectSelected instanceof Tree2D || objectSelected instanceof TreeViolet2D
						|| objectSelected instanceof TreeGreen2D
						|| objectSelected instanceof Statue2D
						|| objectSelected instanceof HouseSmall2D
						|| objectSelected instanceof HouseLarge2D
						|| objectSelected instanceof Trunk2D)
				{
					((Drawable) objectSelected).drawObject(g, xPosition + (getWidth() / 4),
							yPosition + (getHeight() / 4), getWidth() / 4, getHeight() / 4);
				}
				else
				{
					((Drawable) objectSelected).drawObject(g, xPosition, yPosition, 150, 150);
				}

			}
		};

		initPreviewPanel(null);

		scollJTree = new JScrollPane(treePanel.getTree());
		splitPaneTreeAndPreview = new JSplitPane(JSplitPane.VERTICAL_SPLIT, scollJTree,
				previewPanel);
		splitPaneTreeAndPreview.setDividerLocation(350);
		splitPaneTreeAndPreview.setOneTouchExpandable(false);

		infoPanel = new InfoPanel();
		worldPanel = new WorldPanel(this.world, infoPanel, this);
		createMainPanelLayout();
		assignListenerToButtons();

		containerWorldInfoPanel = new JPanel();
		box_layout = new BoxLayout(containerWorldInfoPanel, BoxLayout.PAGE_AXIS);
		containerWorldInfoPanel.setLayout(box_layout);

		containerWorldInfoPanel.add(containerWorldPanelAndButtons);
		containerWorldInfoPanel.add(infoPanel);

		// creo il container ad aree separate
		mainSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, splitPaneTreeAndPreview,
				containerWorldInfoPanel);
		mainSplitPane.setDividerLocation(400);
		mainSplitPane.setOneTouchExpandable(true);

		add(mainSplitPane, BorderLayout.CENTER);

		setImageTitle(StaticConstantURL.editorLogo);
		setVisible(true);
	}

	public int getHeightWorld()
	{
		return heightWorld;
	}

	public ObjectScene getObjectSelected()
	{
		return objectSelected;
	}

	public JPanel getPreviewPanel()
	{
		return previewPanel;
	}

	public int getWidthWorld()
	{
		return widthWorld;
	}

	public void manageAbout()
	{
		new AboutFrame();
	}

	public void manageExit()
	{
		MainFrame mainFrame = new MainFrame();
		MenuPanel menuPanel = new MenuPanel(mainFrame);
		mainFrame.switchPanel(menuPanel);
		StaticConstantGame.mainFrame = mainFrame;
		dispose();
	}

	public void manageNew()
	{
		String result = JOptionPane.showInputDialog(null, "Insert New Dimension (multiplo di"
				+ StaticConstantWorld.X_DIMENSION + ")", "New Dimension",
				JOptionPane.INFORMATION_MESSAGE);

		int numberResult = Integer.parseInt(result);
		if ((numberResult % StaticConstantWorld.X_DIMENSION) == 0)
		{
			EditorJFrameContainer.createEditorWithDimension(numberResult);
			this.dispose();
		}
	}

	public void manageOpen()
	{
		String pathLevel = "Levels/Custom/";
		JFileChooser fileChooser = new JFileChooser(pathLevel);

		// setSettings();

		int res = fileChooser.showOpenDialog(this); // dialogo di tipo "Open"
		if (res == JFileChooser.APPROVE_OPTION) // controllo la scelta
		{
			File f = fileChooser.getSelectedFile(); // / ottengo il file scelto
			JOptionPane.showMessageDialog(this, "Il file scelto è: " + f.getName());
			String levelName = f.getName().substring(0, f.getName().length() - 4);
			EditorJFrameContainer.createEditorWithNameLevel(levelName);
			this.dispose();
		}
		else if (res == JFileChooser.CANCEL_OPTION)
		{} // altro se ho cancellato la scelta
	}

	public void manageReset()
	{
		initEmptyWorld();
	}

	public void manageSave()
	{
		StartPoint point = (StartPoint) world.getStartPoint();
		if (point == null)
		{
			JOptionPane.showMessageDialog(null, "I can't save this level: Start Point Missed",
					"Warning", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String pathLevel = "Levels/Custom/";
		JFileChooser fileChooser = new JFileChooser(pathLevel);
		int dialog = fileChooser.showSaveDialog(this);
		if (dialog == JFileChooser.APPROVE_OPTION)
		{
			try
			{
				File generalFile = fileChooser.getSelectedFile();

				String path = generalFile.toString().substring(0,
						generalFile.toString().length() - 4);
				String pathBase = path + "/base.txt";
				String pathObject = path + "/object.txt";
				String pathCharacter = path + "/character.txt";

				new PrintWriter(path + ".txt");
				PrintWriter pwBase = new PrintWriter(pathBase);
				PrintWriter pwObject = new PrintWriter(pathObject);
				PrintWriter pwCharacter = new PrintWriter(pathCharacter);

				for (int i = 0; i < world.getWidth(); i++)
				{
					for (int j = 0; j < world.getHeight(); j++)
					{
						pwBase.print(world.getCellBaseWorld(i, j).getId() + " ");
						pwObject.print(world.getCellObjectWorld(i, j).getId() + " ");
						pwCharacter.print(world.getCellCharacterWorld(i, j).getId() + " ");

					}
					pwBase.println();
					pwObject.println();
					pwCharacter.println();

				}
				pwBase.close();
				pwObject.close();
				pwCharacter.close();
			}
			catch (final IOException ex)
			{
				JOptionPane.showMessageDialog(null, "Impossible to write "
						+ fileChooser.getSelectedFile().getAbsolutePath());
			}
		}
	}

	public void setHeightWorld(int heightWorld)
	{
		this.heightWorld = heightWorld;
	}

	public void setLookAndFeel(String name)
	{
		final UIManager.LookAndFeelInfo plaf[] = UIManager.getInstalledLookAndFeels();

		for (int i = 0; i < plaf.length; i++)
		{
			if (plaf[i].getName().equals(name))
			{
				try
				{
					UIManager.setLookAndFeel(plaf[i].getClassName());
					SwingUtilities.updateComponentTreeUI(this);
				}
				catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	public void setPreviewPanel(JPanel previewPanel)
	{
		this.previewPanel = previewPanel;
	}

	public void setResizeParameters(String name)
	{
		if (name.equals("Metal"))
		{
			worldPanel.setResizeX(3);
			worldPanel.setResizeY(2);
		}
		else if (name.equals("Nimbus"))
		{
			worldPanel.setResizeX(0);
			worldPanel.setResizeY(2);
		}
		else if (name.equals("Windows"))
		{
			worldPanel.setResizeX(3);
			worldPanel.setResizeY(2);
		}
	}

	@Override
	public void setVisible(boolean visible)
	{
		super.setVisible(visible);
		if (visible)
		{
			this.requestFocus();
		}
	}

	public void setWidthWorld(int widthWorld)
	{
		this.widthWorld = widthWorld;
	}

	public void updatePreviewPanel(String nameObject)
	{
		initPreviewPanel(nameObject);
		repaint();
	}
}