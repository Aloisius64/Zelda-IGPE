package guiInterface.startScreen;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class MenuButton
{
	private Image imageSelected;
	private boolean selected;
	private boolean clicked;
	int printXPosition;
	int printYPosition;

	public MenuButton()
	{
		this.imageSelected = null;
		this.selected = false;
		this.clicked = false;
	}

	public MenuButton(Image imageSelected)
	{
		this.imageSelected = imageSelected;
		this.selected = false;
		this.clicked = false;
	}

	public MenuButton(String imageSelectedString)
	{
		Image newImageSelected = null;

		try
		{
			newImageSelected = ImageIO.read(new File(imageSelectedString));
		}
		catch (IOException e)
		{
			System.out.println("Immagini non caricate");
		}

		this.imageSelected = newImageSelected;
		this.selected = false;
		this.clicked = false;
	}

	public MenuButton(String imageSelectedString, boolean selected)
	{
		Image newImageSelected = null;

		try
		{
			newImageSelected = ImageIO.read(new File(imageSelectedString));
		}
		catch (IOException e)
		{
			System.out.println("Immagini non caricate");
		}

		this.imageSelected = newImageSelected;
		this.selected = selected;
		this.clicked = false;
	}

	public MenuButton(String imageSelectedString, int printX, int printY)
	{
		Image newImageSelected = null;

		try
		{
			newImageSelected = ImageIO.read(new File(imageSelectedString));
		}
		catch (IOException e)
		{
			System.out.println("Immagini non caricate");
		}

		this.imageSelected = newImageSelected;
		this.selected = false;
		this.clicked = false;
		this.printXPosition = 0;
		this.printYPosition = 0;
	}

	public MenuButton(String imageSelectedString, int printX, int printY, boolean selected)
	{
		Image newImageSelected = null;

		try
		{
			newImageSelected = ImageIO.read(new File(imageSelectedString));
		}
		catch (IOException e)
		{
			System.out.println("Immagini non caricate");
		}
		this.imageSelected = newImageSelected;
		this.selected = selected;
		this.clicked = false;
		this.printXPosition = printX;
		this.printYPosition = printY;
	}

	public Image getImageSelected()
	{
		return imageSelected;
	}

	public boolean isClicked()
	{
		return clicked;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void paintMenuButton(Graphics g, int width, int height)
	{
		if (this.selected)
			g.drawImage(imageSelected, 0, 0, width, height, null);
	}

	public void setClicked(boolean clicked)
	{
		this.clicked = clicked;
	}

	public void setImageSelected(Image image)
	{
		this.imageSelected = image;
	}

	public void setSelected(boolean selected)
	{
		this.selected = selected;
	}
}
