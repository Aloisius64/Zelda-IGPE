package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.RenderingHints.Key;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

public class TriforceFont
{
	// private ArrayList<Image> numbers;
	// private ArrayList<Image> upperCase;
	private final ArrayList<Image> lowerCase;

	public TriforceFont()
	{
		lowerCase = new ArrayList<Image>();
		for (int i = 0; i < 26; i++)
		{
			try
			{
				lowerCase.add(ImageIO.read(new File("Images/Lettere/Minuscole/"
						+ String.valueOf(Character.toChars(i + 97)) + ".png")));
			}
			catch (IOException e)
			{
				System.out
						.println("Non è stata trovata qualche immagine relative al caricamento delle lettere minuscole");
			}
		}
	}

	public void printLetter(Graphics g, int key, int x, int y, int width, int height)
	{
		int i = key - 65;
		g.drawImage(lowerCase.get(i), x, y, width, height, null);
	}
}
