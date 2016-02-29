package guiInterface.startScreen;

import java.awt.Image;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import staticConstant.StaticConstantGame;
import wiiusej.WiiUseApiManager;
import wiiusej.Wiimote;
import eventState.WiiMoteMenuListener;

public class MainFrame extends JFrame
{
	public MainFrame()
	{
		setSize(1336, 768);
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		addWindowListener(new MainFrameWindowListener(this));
		setResizable(false);
		setUndecorated(true);
		setLocationRelativeTo(null);
		setImageTitle("Triforza.png");
	}

	public int initWiiMote()
	{
		Wiimote[] wiimotes = WiiUseApiManager.getWiimotes(1, true);
		if (wiimotes.length <= 0)
			return -1;
		Wiimote wiimote = wiimotes[0];
		wiimote.activateIRTRacking();
		StaticConstantGame.wiimote = wiimote;
		StaticConstantGame.wiimoteListener = new WiiMoteMenuListener();
		wiimote.addWiiMoteEventListeners(StaticConstantGame.wiimoteListener);
		return wiimotes.length;
	}

	public void switchPanel(JPanel panel)
	{
		this.getContentPane().removeAll();
		this.setContentPane(panel);
		// Riorganizzazione nel pannello
		panel.revalidate();
		// Abilità il pannello alla ricezioni di eventi da tastiera
		panel.requestFocus();
		this.setVisible(true);
		this.setEnabled(true);
		// this.setExtendedState(Frame.NORMAL);
	}

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
}
