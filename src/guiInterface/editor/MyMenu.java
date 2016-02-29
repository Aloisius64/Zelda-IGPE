package guiInterface.editor;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

class MyMenu extends JMenuBar
{
	private class AscoltaItemMenu implements ActionListener
	{
		@Override
		public void actionPerformed(ActionEvent ev)
		{
			String command = ev.getActionCommand();

			if (command.equals("Exit"))
			{
				mainFrame.manageExit();
			}
			else if (command.equals("New..."))
			{
				mainFrame.manageNew();
			}
			else if (command.equals("Open..."))
			{
				mainFrame.manageOpen();
			}
			else if (command.equals("Save..."))
			{
				mainFrame.manageSave();
			}
			else if (command.equals("Reset"))
			{
				mainFrame.manageReset();
			}
			else if (command.equals("Set Nimbus Look"))
			{
				mainFrame.setLookAndFeel("Nimbus");
				mainFrame.setResizeParameters("Nimbus");
			}
			else if (command.equals("Set Windows Look..."))
			{
				mainFrame.setLookAndFeel("Windows");
				mainFrame.setResizeParameters("Windows");
			}
			else if (command.equals("About"))
			{
				mainFrame.manageAbout();
			}
		}
	}

	private final JMenu file_menu;
	private final JMenu edit_menu;
	private final JMenu help_menu;
	private final AscoltaItemMenu ascoltaItemMenu;
	private final EditorJFrame mainFrame;

	// datasource per il menu File
	// Testo item, icona, mnemonico, acceleratore, separatore
	Object items_data_for_file[][] = {
			{ "New...", "new16.png", KeyEvent.VK_N,
					KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK) },
			{ "Open...", "open16.png", KeyEvent.VK_O,
					KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK) },
			{ "Save...", "save.png", KeyEvent.VK_S,
					KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK) },
			{ "separator" /* item separatore */}, { "Exit", "exit16.png", null, null } };

	// datasource per il menu Edit
	// Testo item, icona, mnemonico, acceleratore, separatore
	Object items_data_for_edit[][] = {
			{ "Reset", "trash.png", KeyEvent.VK_R,
					KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK) },
			{ "separator" /* item separatore */},
			{ "Set Nimbus Look", "Nimbus.png", KeyEvent.VK_M,
					KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.CTRL_MASK) },
			{ "Set Windows Look...", "Windows.png", KeyEvent.VK_W,
					KeyStroke.getKeyStroke(KeyEvent.VK_W, ActionEvent.CTRL_MASK) } };

	// datasource per il menu Help
	// Testo item, icona, mnemonico, acceleratore, separatore
	Object items_data_for_help[][] = { { "About", null, KeyEvent.VK_A,
			KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK) } };

	public MyMenu(EditorJFrame mainFrame)
	{
		super();

		ascoltaItemMenu = new AscoltaItemMenu();
		this.mainFrame = mainFrame;

		// creo i menu
		file_menu = menuCreation("File", KeyEvent.VK_F);
		edit_menu = menuCreation("Edit", KeyEvent.VK_E);
		help_menu = menuCreation("Help", KeyEvent.VK_H);

		// creo gli items per i menu
		menuItemCreation(file_menu, items_data_for_file, "normal");
		menuItemCreation(edit_menu, items_data_for_edit, "normal");
		menuItemCreation(help_menu, items_data_for_help, "normal");

		// aggiungo alla menu bar i menu
		this.add(file_menu);
		this.add(edit_menu);
		this.add(help_menu);
	}

	private ImageIcon getIcon(String name)
	{
		// Immagine titolo
		try
		{
			// path delle icone
			URL urlTitleIcon = (new File("./Images/Editor/" + name)).toURI().toURL();
			return new ImageIcon(urlTitleIcon);
		}
		catch (MalformedURLException exception)
		{
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		return new ImageIcon();
	}

	private JMenu menuCreation(String text, Integer mnemonic)
	{
		JMenu menu = new JMenu(text);
		if (mnemonic != null)
		{
			menu.setMnemonic(mnemonic);
		}

		return menu;
	}

	private void menuItemCreation(JMenu menu, Object data[][], String type)
	{
		JMenuItem item = null;

		for (int i = 0; i < data.length; i++)
		{
			item = new JMenuItem();

			String data0 = (String) data[i][0];
			if (data0.equals("separator"))
			{
				menu.addSeparator();
			}
			else
			{
				// proprietà dell'item
				String text = data0;
				ImageIcon icon = data[i][1] != null ? getIcon((String) data[i][1]) : null;
				Integer mnemonic = data[i][2] != null ? (Integer) data[i][2]
						: KeyEvent.VK_UNDEFINED;
				KeyStroke accelerator = data[i][3] != null ? (KeyStroke) data[i][3] : null;

				// imposto le proprietà all'item
				item.setText(text);
				item.setIcon(icon);
				item.setMnemonic(mnemonic);
				item.setAccelerator(accelerator);

				menu.add(item);
				item.addActionListener(ascoltaItemMenu);
			}
		}
	}
}
