package guiInterface.startScreen;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainFrameWindowListener extends WindowAdapter
{
	private final MainFrame mainFrame;

	public MainFrameWindowListener(MainFrame mainFrame)
	{
		super();
		this.mainFrame = mainFrame;
	}

	@Override
	public void windowClosing(WindowEvent paramWindowEvent)
	{
		System.exit(0);
	}

}
