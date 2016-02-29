package guiInterface.editor;

import gameLogic.world.ConcreteWorld;
import staticConstant.StaticConstantGame;

public class EditorJFrameContainer
{
	// Base

	public static void createBaseEditor()
	{
		StaticConstantGame.enable3D = false;
		new EditorJFrame();
	}

	// Con Dimensioni
	public static void createEditorWithDimension(int dimension)
	{
		ConcreteWorld world = new ConcreteWorld(dimension, dimension);
		new EditorJFrame(world);
	}

	// Con Nome Livello
	public static void createEditorWithNameLevel(String nameLevel)
	{
		String pathLevel = "Levels/Custom/";
		try
		{
			StaticConstantGame.typeWorldChoose = -1;
			new EditorJFrame(pathLevel + nameLevel);
		}
		catch (Exception exception)
		{
			System.out.println("Unable to load World: " + nameLevel);
			exception.printStackTrace();
		}
	}
}
