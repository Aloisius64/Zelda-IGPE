package network;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class NetworkMain extends JFrame
{
	public static void main(String[] args)
	{
		final NetworkMain main = new NetworkMain();
	}

	public NetworkMain()
	{
		this.setSize(100, 100);
		this.setLayout(new BorderLayout());

		final JTextField textField = new JTextField();
		this.add(textField, BorderLayout.CENTER);

		JButton startServer = new JButton("Start Server");
		startServer.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent paramMouseEvent)
			{
				super.mouseClicked(paramMouseEvent);
				if (textField.getText() != null && !(textField.getText().equals("")))
				{
					Server server = new Server(Integer.parseInt(textField.getText()));
					try
					{
						System.out.println("Server Partito");
						server.runServer();
					}
					catch (IOException e)
					{
						System.out.println("Impossibile avviare il server");
					}
				}
			}
		});
		this.add(startServer, BorderLayout.SOUTH);

		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
