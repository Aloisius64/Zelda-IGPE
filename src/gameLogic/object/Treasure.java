package gameLogic.object;

import gameLogic.character.Hero;
import gameLogic.world.World;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

public class Treasure extends AbstractUtilityObject
{

	private boolean open;
	private Heart heart = null;
	private Coin coin = null;
	private MagicSphere magicSphere = null;

	public Treasure(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 500);
		setId(StaticConstantID.TREASURE);
		createContent();
	}

	public Treasure(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
		setId(StaticConstantID.TREASURE);
		createContent();
	}

	private void createContent()
	{
		int choise = (int) (Math.random() % 3);
		switch (choise)
		{
			case 1:
				heart = new Heart(0, 0, 400);
			case 2:
				coin = new Coin(0, 0, 600);
			default:
				magicSphere = new MagicSphere(0, 0, 200);
		}
	}

	public boolean isOpen()
	{
		return open;
	}

	public void manageContent(Hero hero)
	{
		if (!open)
		{
			if (heart != null)
			{
				hero.modifyHearts(1);
				hero.modifyScores(heart.getScore());
			}
			if (coin != null)
			{
				hero.modifyScores(coin.getScore());
			}
			if (magicSphere != null)
			{
				hero.modifyScores(magicSphere.getScore());
				hero.modifyMagicEnergy(1);
			}
			setOpen(true);
			StaticConstantGame.gameManager.modifiyTreasureOpen(+1);
		}
	}

	public void setOpen(boolean open)
	{
		this.open = open;
	}

	@Override
	public void update()
	{
	}

	@Override
	public boolean canAdd(int x, int y)
	{
		return true;
	}

	@Override
	public void manageAddition(World world, int x, int y)
	{
	}

	@Override
	public void manageRemotion(World world, int x, int y)
	{
	}
}
