package guiInterface.editor;

import java.io.File;
import java.util.StringTokenizer;

import javax.swing.JFrame;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class TreePanel
{
	private JTree tree;
	private JFrame mainFrame;

	private String[] nameScene;
	private String[] nameSceneForest;
	private String[] nameObject;
	private String[] nameCharacter;

	public TreePanel(final JFrame mainFrame)
	{
		loadComponent();

		// nodo ROOT
		DefaultMutableTreeNode root = new DefaultMutableTreeNode("Root");

		// nodo Object Scene e figli
		DefaultMutableTreeNode object_Scene = new DefaultMutableTreeNode("Scene");
		for (int i = 1; i < nameScene.length; i++)
		{
			if (nameScene[i] != null)
			{
				DefaultMutableTreeNode tmpNode = new DefaultMutableTreeNode(nameScene[i]);
				object_Scene.add(tmpNode);
			}
		}

		// nodo Forest e figli
		DefaultMutableTreeNode object_Scene_Forest = new DefaultMutableTreeNode("Forest");
		for (int i = 0; i < nameSceneForest.length; i++)
		{
			DefaultMutableTreeNode tmpNode = new DefaultMutableTreeNode(nameSceneForest[i]);
			object_Scene_Forest.add(tmpNode);
		}
		object_Scene.add(object_Scene_Forest);

		// nodo Utility Object e figli
		DefaultMutableTreeNode utility_Object = new DefaultMutableTreeNode("Object");
		for (int i = 1; i < nameObject.length; i++)
		{
			DefaultMutableTreeNode tmpNode = new DefaultMutableTreeNode(nameObject[i]);
			utility_Object.add(tmpNode);
		}

		// nodo Character
		DefaultMutableTreeNode character = new DefaultMutableTreeNode("Character");
		for (int i = 2; i < nameCharacter.length; i++)
		{
			DefaultMutableTreeNode tmpNode = new DefaultMutableTreeNode(nameCharacter[i]);
			character.add(tmpNode);
		}

		// aggiungo i nodi al ROOT
		root.add(object_Scene);
		root.add(utility_Object);
		root.add(character);

		setTree(new JTree(root));
		setMainFrame(mainFrame);

		tree.addTreeSelectionListener(new TreeSelectionListener()
		{

			@Override
			public void valueChanged(TreeSelectionEvent ev)
			{
				TreePath path = ev.getNewLeadSelectionPath();
				if (path != null)
				{
					String nameObject = getNameObject(path.toString());
					if (mainFrame instanceof EditorJFrame)
					{
						((EditorJFrame) mainFrame).updatePreviewPanel(nameObject);
					}
				}
			}
		});
	}

	private String getNameObject(String str)
	{
		String tmp = "";
		StringTokenizer strToken = new StringTokenizer(str, " ");
		while (strToken.hasMoreElements())
		{
			tmp = (String) strToken.nextElement();
		}
		return tmp.substring(0, tmp.length() - 1);
	}

	private void loadComponent()
	{
		final String path = "items/";
		final File sceneDirectory = new File(path + "scene");
		final File sceneForestDirectory = new File(path + "scene/forest");

		final File objectDirectory = new File(path + "object");
		final File characterDirectory = new File(path + "character");
		final File[] listFilesScene;
		final File[] listFilesSceneForest;

		// ////

		final File[] listFilesObject;
		final File[] listFilesCharacter;

		listFilesSceneForest = sceneForestDirectory.listFiles();
		listFilesScene = sceneDirectory.listFiles();
		listFilesObject = objectDirectory.listFiles();
		listFilesCharacter = characterDirectory.listFiles();

		nameSceneForest = new String[listFilesSceneForest.length];
		nameScene = new String[listFilesScene.length];
		nameObject = new String[listFilesObject.length];
		nameCharacter = new String[listFilesCharacter.length];

		for (int i = 1; i < listFilesScene.length; i++)
		{
			final String name = listFilesScene[i].getName();

			if (name.endsWith(".class"))
			{
				StringTokenizer strTokenizer = new StringTokenizer(name, ".");
				nameScene[i] = strTokenizer.nextToken();
			}
		}

		for (int i = 0; i < listFilesSceneForest.length; i++)
		{
			final String name = listFilesSceneForest[i].getName();

			if (name.endsWith(".class"))
			{
				StringTokenizer strTokenizer = new StringTokenizer(name, ".");
				nameSceneForest[i] = strTokenizer.nextToken();
			}
		}

		for (int i = 1; i < listFilesObject.length; i++)
		{
			final String name = listFilesObject[i].getName();

			if (name.endsWith(".class"))
			{
				StringTokenizer strTokenizer = new StringTokenizer(name, ".");
				nameObject[i] = strTokenizer.nextToken();
			}
		}

		for (int i = 2; i < listFilesCharacter.length; i++)
		{
			final String name = listFilesCharacter[i].getName();

			if (name.endsWith(".class"))
			{
				StringTokenizer strTokenizer = new StringTokenizer(name, ".");
				nameCharacter[i] = strTokenizer.nextToken();
			}
		}
	}

	public JFrame getMainFrame()
	{
		return mainFrame;
	}

	public JTree getTree()
	{
		return tree;
	}

	public void setMainFrame(JFrame mainFrame)
	{
		this.mainFrame = mainFrame;
	}

	public void setTree(JTree tree)
	{
		this.tree = tree;
	}
}
