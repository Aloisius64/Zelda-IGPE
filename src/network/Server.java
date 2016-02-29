package network;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;
import java.util.Set;

public class Server
{

	private class ClientManager implements Runnable
	{

		private String name;

		private PrintWriter pw;

		private final ServerGameManager server;

		private final Socket socket;

		public ClientManager(final Socket socket, final ServerGameManager server)
		{
			this.socket = socket;
			this.server = server;
		}

		public void dispatch(final String message)
		{
			pw.println(message);
		}

		public String getName()
		{
			return name;

		}

		@Override
		public void run()
		{
			try
			{
				final BufferedReader br = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				pw = new PrintWriter(socket.getOutputStream(), true);
				name = br.readLine();
				server.dispatch(server.getConnectedClientNames(), null);
				server.setReady(this);
				final boolean running = true;
				while (running)
				{
					final String buffer = br.readLine();
					server.dispatch(buffer, this);
				}
			}
			catch (final IOException e)
			{
				server.dispatch("Connection", this);
				System.out.println("Client disconnected: " + name);
			}

		}

	}

	public class ServerGameManager
	{
		private final Set<ClientManager> clients = new HashSet<Server.ClientManager>();

		private final Set<ClientManager> readyClients = new HashSet<Server.ClientManager>();

		public void add(final ClientManager cm)
		{
			clients.add(cm);
		}

		public void dispatch(final String message, final ClientManager senderClientManager)
		{
			for (final ClientManager cm : clients)
			{
				if (cm != senderClientManager)
				{
					cm.dispatch(message);
				}
			}
		}

		public String getConnectedClientNames()
		{
			final StringBuilder sb = new StringBuilder();
			for (final ClientManager cm : clients)
			{
				if (cm.getName() != null)
				{
					sb.append(cm.getName());
					sb.append(";");
				}
			}
			return sb.toString();
		}

		public void setReady(final ClientManager clientManager)
		{
			readyClients.add(clientManager);
			if (readyClients.size() == 2)
			{
				dispatch("#START", null);
			}
		}

		public void startGame()
		{
			for (final ClientManager cm : clients)
			{
				new Thread(cm, cm.toString()).start();
			}
		}

		public void dispatch(String message)
		{
			for (final ClientManager cm : clients)
			{
				cm.dispatch(message);
			}
		}

	}

	/**
	 * @param args
	 * @throws IOException
	 */
	public static void main(final String[] args) throws IOException
	{
		final Server server = new Server(Integer.parseInt(args[0]));
		server.runServer();
	}

	private final int port;

	private final boolean running = true;

	private ServerSocket serverSocket;

	public Server(final int port)
	{
		this.port = port;
	}

	void runServer() throws IOException
	{
		serverSocket = new ServerSocket(port);
		while (running)
		{
			final ServerGameManager gameManager = new ServerGameManager();

			final Socket socket1 = serverSocket.accept();
			final ClientManager cm1 = new ClientManager(socket1, gameManager);
			gameManager.add(cm1);
			System.out.println("Player 1 entrato");

			final Socket socket2 = serverSocket.accept();
			final ClientManager cm2 = new ClientManager(socket2, gameManager);
			gameManager.add(cm2);
			System.out.println("Player 2 entrato");

			gameManager.startGame();
		}
	}
}
