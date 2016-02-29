/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiInterface.object3D;

import com.jme3.scene.Spatial;
import common3D.Builderable;
import common3D.TransfertControl;
import common3D.URLModelProvider;
import gameLogic.character.EmptyCharacter;
import gameLogic.character.Hero;
import gameLogic.object.Gate;
import guiInterface.character3D.Hero3D;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantWorld;

/**
 * 
 * @author Aloisius
 */
public class Gate3D extends Gate implements Builderable
{

	private Spatial model;

	public Gate3D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		initModelToScene(xPosition, yPosition);
	}

	public Spatial getModel()
	{
		return model;
	}

	public void setModel(Spatial model)
	{
		this.model = model;
	}

	private void initModelToScene(int xPosition, int yPosition)
	{
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.BLOCK_MODEL);
		model.setLocalScale(0.4f);
		model.move(yPosition, 0.0f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);
		model.setCullHint(Spatial.CullHint.Always);

		// Controllo per l'aggiornamento
		TransfertControl control = new TransfertControl(this);
		model.addControl(control);
	}

	@Override
	public void update()
	{
		if (isOpen())
		{
			Hero hero = StaticConstantGame.gameManager.getHero();
			if ((getX() == hero.getX()) && (getY() == hero.getY()))
			{
				hero.setX(getNextX());
				hero.setY(getNextY());
				((Hero3D) hero).getModel().setLocalTranslation(hero.getY(), 1.0f, hero.getX());
				StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(),
						getY(), new EmptyCharacter(getX(), getY()));
				StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(
						hero.getX(), hero.getY(), hero);

				StaticConstantGame.gameManager.getConcreteWorld().setxPrint(
						getNextX() - (getNextX() % StaticConstantWorld.X_DIMENSION));
				StaticConstantGame.gameManager.getConcreteWorld().setyPrint(
						getNextY() - (getNextY() % StaticConstantWorld.Y_DIMENSION));
			}

		}
	}
}
