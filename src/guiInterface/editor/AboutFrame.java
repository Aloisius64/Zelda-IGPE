package guiInterface.editor;

import java.awt.Graphics;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;

public class AboutFrame extends JFrame
{
	private JPanel background;
	private URL urlTitleIcon = null;
	private ImageIcon imageTitle = null;
	private int xSize = 250;
	private int ySize = 250;

	AboutFrame()
	{
		setSettings();

		try
		{
			urlTitleIcon = (new File("./Images/Editor/about.png")).toURI().toURL();
			imageTitle = new ImageIcon(urlTitleIcon);
		}
		catch (MalformedURLException exception)
		{
			exception.printStackTrace();
		}

		background = new JPanel()
		{
			@Override
			public void paintComponent(Graphics g)
			{
				super.paintComponent(g);

				g.drawImage(imageTitle.getImage(), 0, 0, xSize, ySize, null);
			}
		};

		getContentPane().add(background);

		setVisible(true);
	}

	private void setSettings()
	{
		setTitle("About");
		setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
		setSize(xSize, ySize);
		setResizable(false);
		setLocationRelativeTo(null);
	}
}
