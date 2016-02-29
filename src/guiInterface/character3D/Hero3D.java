/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiInterface.character3D;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.math.FastMath;
import com.jme3.scene.Spatial;
import common.Direction;
import common.SoundManager;
import common3D.Builderable;
import common3D.URLModelProvider;
import common3D.UpdaterControl;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.CharacterState;
import gameLogic.character.EmptyCharacter;
import gameLogic.character.Hero;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Block;
import gameLogic.object.InvisibleWall;
import gameLogic.object.Treasure;
import gameLogic.scene.AbstractObjectScene;
import gameLogic.world.ConcreteWorld;
import guiInterface.GamePanel3D;
import guiInterface.object3D.Block3D;
import java.util.Random;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;
import staticConstant.StaticConstantWorld;

public class Hero3D extends Hero implements Builderable, AnimEventListener
{

	private Spatial model;
	private AnimChannel animChannel;
	private AnimControl animControl;
	private int indexAnimation = 0;
	private final String[] animationsName = { "Stand", "Stand2", "Walk", "Walk2", "SwordUp",
			"SwordLeft", "SwordRight", "SwordDown", "MagicAttack", "Twist" };
	// Utility
	final private Random random = new Random();
	private float movement = 0.02f;

	public Hero3D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		setCurrentDirection(Direction.DOWN);
		initModelToScene(xPosition, yPosition);
	}

	private void initModelToScene(int xPosition, int yPosition)
	{
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.HERO_MODEL);
		model.setLocalScale(0.1f);
		model.move(yPosition, 1.0f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);
		// Controllo per l'aggiornamento
		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);

		animControl = model.getControl(AnimControl.class);
		animControl.addListener(this);
		animChannel = animControl.createChannel();
		animChannel.setAnim(animationsName[indexAnimation]);
	}

	@Override
	public Spatial getModel()
	{
		return model;
	}

	@Override
	public void setModel(Spatial model)
	{
		this.model = model;
	}

	@Override
	public boolean move()
	{
		boolean falseMove = false;
		int typeDirection = getCurrentDirection().getTypeDirection();

		int xTarget = getX() + getCurrentDirection().getXSelected();
		int yTarget = getY() + getCurrentDirection().getYSelected();

		AbstractObjectScene sceneObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellBaseWorld(xTarget, yTarget);
		AbstractUtilityObject utilityObject = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(xTarget, yTarget);
		AbstractCharacter character = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(xTarget, yTarget);

		if (sceneObject.getId() >= StaticConstantID.LIMITS_ACCESSIBLE)
		{
			// controllo se stiamo per andare contro un invisible Wall
			if (utilityObject instanceof InvisibleWall)
			{
				falseMove = true;
			}

			if (utilityObject instanceof Block)
			{
				if (((Block) utilityObject).isMovable())
				{
					falseMove = !((Block3D) utilityObject).move(this);
				}
			}

			if (utilityObject instanceof Treasure)
			{
				falseMove = true;
			}

			if (character instanceof AbstractEnemy)
			{
				falseMove = true;
			}
		}
		else
		{
			falseMove = true;
		}

		if (!falseMove)
		{
			if (typeDirection == Direction.UP)
			{
				model.move(0, 0, -movement);
			}
			else if (typeDirection == Direction.LEFT)
			{
				model.move(-movement, 0, 0);
			}
			else if (typeDirection == Direction.RIGHT)
			{
				model.move(movement, 0, 0);
			}
			else if (typeDirection == Direction.DOWN)
			{
				model.move(0, 0, movement);
			}
		}

		positionUpdate(falseMove, model.getLocalTranslation().getX(), model.getLocalTranslation()
				.getZ());

		if (sceneObject.getId() == 0)
		{
			setState(4);
		}

		return !falseMove;
	}

	private void manageChangeScreen()
	{
		// ///////////// GESTIONE CAMBIO SCHERMO //////////////
		ConcreteWorld concreteWorld = StaticConstantGame.gameManager.getConcreteWorld();
		int xPrint = concreteWorld.getxPrint();
		int yPrint = concreteWorld.getyPrint();

		if (getX() < xPrint)
		{
			concreteWorld.moveToUp(StaticConstantWorld.X_DIMENSION);

		}
		if (getX() >= (xPrint + StaticConstantWorld.X_DIMENSION))
		{
			concreteWorld.moveToDown(StaticConstantWorld.X_DIMENSION);

		}
		if (getY() >= (yPrint + StaticConstantWorld.Y_DIMENSION))
		{
			concreteWorld.moveToRight(StaticConstantWorld.Y_DIMENSION);

		}
		if (getY() < yPrint)
		{
			concreteWorld.moveToLeft(StaticConstantWorld.X_DIMENSION);
		}
	}

	public void positionUpdate(boolean falseAnimation, float xPosition, float zPosition)
	{
		StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(), getY(),
				new EmptyCharacter(getX(), getY()));
		setX((int) Math.ceil(zPosition));
		setY((int) Math.ceil(xPosition));
		StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(), getY(),
				this);
		// System.out.println("HERO: X,Y: " + getX() + ", " + getY() +
		// ", Life: " + getHearts());
	}

	@Override
	public void update()
	{
		switch (getState())
		{
			case CharacterState.STAND:
				indexAnimation = random.nextInt(2);
				break;
			case CharacterState.WALK: // movimento
				if (indexAnimation != 2)
				{
					indexAnimation = 2;
					if (random.nextInt(100) >= 90)
					{
						indexAnimation = 3;
					}
					animChannel.setAnim(animationsName[indexAnimation], 0.5f);
					animChannel.setLoopMode(LoopMode.DontLoop);
				}
				move();
				break;
			case CharacterState.PHYSIC_ATTACK: // attacco con la spada
				attack(0);
				animChannel.setAnim(animationsName[indexAnimation], 0.05f);
				animChannel.setLoopMode(LoopMode.DontLoop);
				SoundManager.playSound("Sounds/Effetcs/Sword.wav", 1, SoundManager.EFFECT);
				// setState(CharacterState.STAND);
				break;
			case CharacterState.DISTANCE_ATTACK: // lancio sfera magica
				animChannel.setAnim(animationsName[indexAnimation], 0.05f);
				animChannel.setLoopMode(LoopMode.DontLoop);
				int value = attack(1);
				if (value == 2)
				{
					AttackSphere3D attackSphere3D = new AttackSphere3D(this);
					attackSphere3D.setSphereControl();
					StaticConstantGame.rootNode.attachChild(attackSphere3D.getModel());
					StaticConstantGame.rootNode.attachChild(attackSphere3D.getEffect());
					setState(CharacterState.STAND);
					break;
				}
			case CharacterState.FALLING: // caduta
				// setState(0);
				break;
			case CharacterState.HITTED:
				break;
			case CharacterState.SPECIAL_ATTACK: // tornado
				if (getMagicEnergy() > 3)
				{
					modifyMagicEnergy(-3);
					GamePanel3D.updateGuiNode = true;
					indexAnimation = 9;
					animChannel.setAnim(animationsName[indexAnimation], 0.05f);
					animChannel.setLoopMode(LoopMode.DontLoop);
					setState(CharacterState.STAND);
					modifyFactorNear(+5);
					attack(2);
					modifyFactorNear(-5);
				}
				break;
			default:
				break;
		}
	}

	@Override
	public int attack(int typeAttack)
	{
		switch (typeAttack)
		{
			case 0:
				return attackZero();
			case 1:
				return attackOne();
			case 2:
				return attackTwo();
			default:
				return 0;
		}
	}

	@Override
	public int attackZero()
	{
		AbstractCharacter objectTarget[] = new AbstractCharacter[3];

		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();
		int typeDirection = getCurrentDirection().getTypeDirection();
		int targetHitted = 0;

		AbstractUtilityObject utilityTarget = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellObjectWorld(x, y);
		if (utilityTarget instanceof Treasure)
		{
			((Treasure) utilityTarget).manageContent(this);
			GamePanel3D.updateGuiNode = true;
			return targetHitted;
		}

		for (int i = -1; i < 2; i++)
		{
			if ((typeDirection == Direction.LEFT) || (typeDirection == Direction.RIGHT))
			{
				objectTarget[i + 1] = StaticConstantGame.gameManager.getConcreteWorld()
						.getCellCharacterWorld(x + i, y);
			}
			else if ((typeDirection == Direction.UP) || (typeDirection == Direction.DOWN))
			{
				objectTarget[i + 1] = StaticConstantGame.gameManager.getConcreteWorld()
						.getCellCharacterWorld(x, y + i);
			}
		}

		for (int i = 0; i < 3; i++)
		{
			if (objectTarget[i] instanceof AbstractEnemy)
			{
				AbstractEnemy enemy = (AbstractEnemy) objectTarget[i];
				int totalDamage = (int) (this.getDamageNear() * this.getFactorNear());
				enemy.modifyHearts(-totalDamage);
				enemy.setCurrentDirection(getCurrentDirection().getTypeDirection());
				enemy.setState(CharacterState.STAND);
				targetHitted += 1;
			}
		}
		return targetHitted;
	}

	public void manageChangeDirection(int newDirection)
	{
		float rotation = 0;

		switch (getCurrentDirection().getTypeDirection())
		{
			case Direction.UP:
			{
				switch (newDirection)
				{
					case Direction.DOWN:
					{
						rotation = FastMath.PI;
					}
						;
						break;
					case Direction.LEFT:
					{
						rotation = (FastMath.PI / 2);
					}
						;
						break;
					case Direction.RIGHT:
					{
						rotation = -(FastMath.PI / 2);
					}
						;
						break;
					default:
						break;
				}
			}
				;
				break;
			case Direction.DOWN:
			{
				switch (newDirection)
				{
					case Direction.UP:
					{
						rotation = FastMath.PI;
					}
						;
						break;
					case Direction.LEFT:
					{
						rotation = -(FastMath.PI / 2);
					}
						;
						break;
					case Direction.RIGHT:
					{
						rotation = (FastMath.PI / 2);
					}
						;
						break;
					default:
						break;
				}
			}
				;
				break;
			case Direction.LEFT:
			{
				switch (newDirection)
				{
					case Direction.DOWN:
					{
						rotation = (FastMath.PI / 2);
					}
						;
						break;
					case Direction.UP:
					{
						rotation = -(FastMath.PI / 2);
					}
						;
						break;
					case Direction.RIGHT:
					{
						rotation = FastMath.PI;
					}
						;
						break;
					default:
						break;
				}
			}
				;
				break;
			case Direction.RIGHT:
			{
				switch (newDirection)
				{
					case Direction.DOWN:
					{
						rotation = -(FastMath.PI / 2);
					}
						;
						break;
					case Direction.UP:
					{
						rotation = (FastMath.PI / 2);
					}
						;
						break;
					case Direction.LEFT:
					{
						rotation = FastMath.PI;
					}
						;
						break;
					default:
						break;
				}
			}
				;
				break;
			default:
				rotation = 0;
				break;
		}

		model.rotate(0, rotation, 0);
	}

	public AnimChannel getChannel()
	{
		return animChannel;
	}

	public void setChannel(AnimChannel channel)
	{
		this.animChannel = channel;
	}

	public AnimControl getControl()
	{
		return animControl;
	}

	public void setControl(AnimControl control)
	{
		this.animControl = control;
	}

	public int getIndexAnimation()
	{
		return indexAnimation;
	}

	public void setIndexAnimation(int indexAnimation)
	{
		this.indexAnimation = indexAnimation;
	}

	public float getMovement()
	{
		return movement;
	}

	public void setMovement(float movement)
	{
		this.movement = movement;
	}

	@Override
	public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName)
	{
		if (indexAnimation >= 4)
		{
			indexAnimation = random.nextInt(2);
			animChannel.setAnim(animationsName[indexAnimation], 0.05f);
			animChannel.setLoopMode(LoopMode.Loop);
		}

		if (indexAnimation < 4)
		{
			animChannel.setAnim(animationsName[indexAnimation], 0.05f);
			animChannel.setLoopMode(LoopMode.Cycle);
		}
	}

	@Override
	public void onAnimChange(AnimControl control, AnimChannel channel, String animName)
	{
	}

	@Override
	public int attackTwo()
	{
		int enemeyHitted = 0;
		for (int i = 0; i < 4; i++)
		{
			enemeyHitted += attackZero();
			setCurrentDirection((getCurrentDirection().getTypeDirection() + 2) % 8);
		}
		return enemeyHitted;
	}
}
