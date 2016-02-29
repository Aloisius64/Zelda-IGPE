package guiInterface.startScreen;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import network.ConnectionManager;

public class ConnectionPanel extends JPanel
{
	private final MainFrame mainFrame;
	private final MultiPlayerPanel panel;

	private final JTextField ipTextField;
	private final JTextField nameTextField;
	private final JTextField portTextField;

	public ConnectionPanel(final MainFrame mainFrame, final MultiPlayerPanel panel)
	{
		try
		{
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		}
		catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (InstantiationException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (IllegalAccessException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (UnsupportedLookAndFeelException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		this.mainFrame = mainFrame;
		this.panel = panel;

		this.setLayout(new BorderLayout());

		final JPanel content = new JPanel(new GridBagLayout());
		final GridBagConstraints constraints = new GridBagConstraints();
		constraints.fill = GridBagConstraints.HORIZONTAL;
		constraints.insets = new Insets(20, 20, 20, 20);
		constraints.gridx = 0;
		constraints.gridy = 0;
		content.add(new JLabel("Server IP:"), constraints);
		ipTextField = new JTextField(20);
		ipTextField.setText("127.0.0.1");
		constraints.gridx++;
		content.add(ipTextField, constraints);
		constraints.gridy++;
		constraints.gridx = 0;
		content.add(new JLabel("Server Port:"), constraints);
		portTextField = new JTextField(20);
		portTextField.setText("1515");
		constraints.gridx++;
		content.add(portTextField, constraints);
		constraints.gridy++;
		constraints.gridx = 0;
		content.add(new JLabel("Nickname:"), constraints);
		nameTextField = new JTextField(20);
		nameTextField.setText("NicknameZ");
		constraints.gridx++;
		content.add(nameTextField, constraints);
		constraints.gridy++;
		constraints.gridx = 0;

		// BUTTON PANEL
		JPanel buttonPanel = new JPanel();

		JLabel connectLabel = new JLabel("<html><p align=\"center\">Connect</p></html>",
				JLabel.CENTER);
		JButton connectButton = new JButton("");
		connectButton.setLayout(new BorderLayout());
		connectButton.add(connectLabel, BorderLayout.CENTER);
		connectButton.setPreferredSize(new Dimension(120, 120));
		connectButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent)
			{
				super.mouseClicked(paramMouseEvent);
				try
				{
					if (!portTextField.getText().equals("") && !ipTextField.getText().equals(""))
					{
						Socket socket = new Socket(ipTextField.getText(), Integer
								.parseInt(portTextField.getText()));
						mainFrame.switchPanel(new LoadingPanel(mainFrame));
						final ConnectionManager connectionManager = new ConnectionManager(socket,
								nameTextField.getText(), mainFrame, panel);
						new Thread(connectionManager, "Connection Manager").start();
					}
				}
				catch (UnknownHostException e)
				{
					System.out.println("Host non disponibile");
				}
				catch (IOException e)
				{
					System.out.println("Errore nella connessione con il server (Connection Panel)");
				}
			}
		});
		buttonPanel.add(connectButton);

		JLabel goToMenuLabel = new JLabel("<html><p align=\"center\">Exit</p></html>",
				JLabel.CENTER);
		JButton goToMenuButton = new JButton("");
		goToMenuButton.setLayout(new BorderLayout());
		goToMenuButton.add(goToMenuLabel, BorderLayout.CENTER);
		goToMenuButton.setPreferredSize(new Dimension(120, 120));
		goToMenuButton.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent)
			{
				ConnectionPanel.this.mainFrame.switchPanel(panel);
			}
		});
		buttonPanel.add(goToMenuButton);

		// BORDER LAYOUT NORD
		JPanel titlePanel = new JPanel();
		titlePanel.setLayout(new BorderLayout());
		JLabel titleLabel = new JLabel(
				"<html><p align=\"center\"><b><font size=\"25\">NETWORK</font></b></p></html>",
				JLabel.CENTER);
		titleLabel.setFont(new Font("Triforce", Font.BOLD, 25));
		titlePanel.add(titleLabel);

		this.add(titlePanel, BorderLayout.NORTH);
		this.add(content, BorderLayout.CENTER);
		this.add(buttonPanel, BorderLayout.SOUTH);

		this.setVisible(true);
	}
}
