package gameLogic.logicState.logicCondition.conditionLevelSeven;

import gameLogic.character.Hero;
import gameLogic.logicState.logicCondition.AbstractLogicCondition;
import guiInterface.character2D.Ganondorf2D;
import guiInterface.character2D.Hero2D;
import staticConstant.StaticConstantGame;

import common.SoundManager;

public class GanondorfCreation extends AbstractLogicCondition
{

	public GanondorfCreation()
	{
		super();
	}

	@Override
	public boolean isLocked()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if (hero.getX() > 20 && hero.getY() < 20)
		{
			return false;
		}
		return true;
	}

	@Override
	public void doEffect()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();

		SoundManager.stop();
		SoundManager.playSound(SoundManager.SKY_BATTLE, 0, SoundManager.MUSIC);

		Ganondorf2D ganondorf = new Ganondorf2D(hero.getX() - 12, hero.getY());
		StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(ganondorf.getX(),
				ganondorf.getY(), ganondorf);
		StaticConstantGame.gameManager.getCharacterCollection().add(ganondorf);
		ganondorf.setPanel(((Hero2D) StaticConstantGame.gameManager.getHero()).getPanel());
		ganondorf.startGanondorf();
	}

	@Override
	public boolean mustBeRemoved()
	{
		return true;
	}

}
