package guiInterface.editor;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

//Barra indicatore mouse
public class InfoPanel extends JPanel
{
	private int xPosition;
	private int yPosition;

	public InfoPanel()
	{
		super();
		xPosition = 0;
		yPosition = 0;
	}

	public void paintComponent(Graphics g)
	{
		g.drawString("(x,y): (" + xPosition + ", " + yPosition + ")", getWidth() - 100, 10);
	}

	public int getxPosition()
	{
		return xPosition;
	}

	public void setxPosition(int xPosition)
	{
		this.xPosition = xPosition;
	}

	public int getyPosition()
	{
		return yPosition;
	}

	public void setyPosition(int yPosition)
	{
		this.yPosition = yPosition;
	}
}
