package guiInterface.character2D;

import gameLogic.Drawable;
import javax.swing.JPanel;

public interface Character2D extends Drawable
{

	public void setPanel(JPanel panel);

	public JPanel getPanel();

	public void setDeltaX(int deltaX);

	public void setDeltaY(int deltaY);

	public int getDeltaY();

	public int getDeltaX();
}
