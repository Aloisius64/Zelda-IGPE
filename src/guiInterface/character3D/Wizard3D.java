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
import common3D.Builderable;
import common3D.URLModelProvider;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.CharacterState;
import gameLogic.character.EmptyCharacter;
import gameLogic.character.Hero;
import gameLogic.character.Wizard;
import gameLogic.object.AbstractUtilityObject;
import gameLogic.object.Block;
import gameLogic.object.InvisibleWall;
import gameLogic.scene.AbstractObjectScene;
import guiInterface.GamePanel3D;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.logging.Level;
import java.util.logging.Logger;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

/**
 * 
 * @author Aloisius
 */
public class Wizard3D extends Wizard implements Builderable, AnimEventListener, Runnable, Startable
{

	private Spatial model;
	private AnimChannel animChannel;
	private AnimControl animControl;
	private int oldTypeDirection;
	private boolean alive = true;
	private int indexAnimation = 0;
	private String[] animationsName = { "Stand1", "Stand2", "Attack1", "Attack2" };
	// Utility
	final private Random random = new Random();

	public Wizard3D(int xPosition, int yPosition)
	{
		super(xPosition, yPosition);
		initModelToScene(xPosition, yPosition);
		setCurrentDirection(Direction.DOWN);
		oldTypeDirection = getCurrentDirection().getTypeDirection();
	}

	private void initModelToScene(int xPosition, int yPosition)
	{
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.WIZARD_MODEL);
		model.setLocalScale(0.5f);
		model.move(yPosition, 1.5f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		animControl = model.getControl(AnimControl.class);
		animControl.addListener(this);
		animChannel = animControl.createChannel();
		animChannel.setAnim(animationsName[indexAnimation]);
	}

	public void start()
	{
		new Thread(this, "Wizard").start();
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
	public void update()
	{
		if (logicUpdate())
		{
			switch (getState())
			{
				case CharacterState.STAND:
				{
					if (indexAnimation >= 2)
					{
						indexAnimation = random.nextInt(2);
						animChannel.setAnim(animationsName[indexAnimation], 0.50f);
						animChannel.setLoopMode(LoopMode.Loop);
					}
					break;
				}
				case CharacterState.PHYSIC_ATTACK:
				{
					if (indexAnimation < 2)
					{
						indexAnimation = 2;
						animChannel.setAnim(animationsName[indexAnimation], 0.50f);
						animChannel.setLoopMode(LoopMode.DontLoop);
						int value = attack(1);
						if (value == 2)
						{
							final AttackSphere3D attackSphere3D = new AttackSphere3D(this);
							StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
							{
								public Spatial call() throws Exception
								{
									StaticConstantGame.rootNode.attachChild(attackSphere3D
											.getModel());
									return attackSphere3D.getModel();
								}
							});
							StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
							{
								public Spatial call() throws Exception
								{
									StaticConstantGame.rootNode.attachChild(attackSphere3D
											.getEffect());
									return attackSphere3D.getEffect();
								}
							});
							attackSphere3D.setSphereControl();
						}
						try
						{
							Thread.sleep(StaticConstantGame.timeWizard * 2);
						}
						catch (InterruptedException ex)
						{
							Logger.getLogger(Warrior3D.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
					setState(CharacterState.STAND);
				}
					break;
				case CharacterState.DISTANCE_ATTACK:
				{
					if (indexAnimation < 2)
					{
						indexAnimation = 3;
						animChannel.setAnim(animationsName[indexAnimation], 0.50f);
						animChannel.setLoopMode(LoopMode.DontLoop);
						int value = attack(1);
						if (value == 2)
						{
							final AttackSphere3D attackSphere3D = new AttackSphere3D(this);
							StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
							{
								public Spatial call() throws Exception
								{
									StaticConstantGame.rootNode.attachChild(attackSphere3D
											.getModel());
									return attackSphere3D.getModel();
								}
							});
							StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
							{
								public Spatial call() throws Exception
								{
									StaticConstantGame.rootNode.attachChild(attackSphere3D
											.getEffect());
									return attackSphere3D.getEffect();
								}
							});
							attackSphere3D.setSphereControl();
						}
						try
						{
							Thread.sleep(StaticConstantGame.timeWizard * 2);
						}
						catch (InterruptedException ex)
						{
							Logger.getLogger(Warrior3D.class.getName()).log(Level.SEVERE, null, ex);
						}
					}
				}
					setState(CharacterState.STAND);
					break;
				case CharacterState.WALK:
				{
					if (indexAnimation > 1)
					{
						indexAnimation = 0;
						animChannel.setAnim(animationsName[indexAnimation], 0.50f);
						animChannel.setLoopMode(LoopMode.Cycle);
					}
					move();
				}
					break;
				default:
					setState(CharacterState.STAND);
					break;
			}
		}
		else
		{
			StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
			{
				public Spatial call() throws Exception
				{
					StaticConstantGame.rootNode.detachChild(model);
					return model;
				}
			});
			setAlive(false);
		}
	}

	public void manageChangeDirection(int newDirection)
	{
		float rotation = 0;

		switch (oldTypeDirection)
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

		oldTypeDirection = newDirection;

		final float finalRotation = rotation;
		StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
		{
			public Spatial call() throws Exception
			{
				model.rotate(0, finalRotation, 0);
				return model;
			}
		});
	}

	@Override
	public int attackOne()
	{
		if (getMagicEnergy() != 0)
		{

			int xTarget = getX() + getCurrentDirection().getXSelected();
			int yTarget = getY() + getCurrentDirection().getYSelected();

			AbstractObjectScene sceneTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellBaseWorld(xTarget, yTarget);

			AbstractCharacter characterTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellCharacterWorld(xTarget, yTarget);

			if (sceneTarget.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
			{
				return 0;
			}

			int returnValue = 0;
			if (characterTarget instanceof EmptyCharacter)
			{
				returnValue = 2;
			}
			else if (characterTarget instanceof Hero)
			{
				int totalDamage = (int) (getFactorFar() * getDamageFar());
				((Hero) characterTarget).modifyHearts(-totalDamage);
				returnValue = 1;
			}
			else
			{
				return 0;
			}

			modifyMagicEnergy(-1);
			return returnValue;
		}
		return 0;
	}

	@Override
	public boolean move()
	{
		boolean falseMove = false;
		final float movement = 0.05f;
		int typeDirection = getCurrentDirection().getTypeDirection();

		manageChangeDirection(typeDirection);

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
				falseMove = false;
			}

			if (!(character instanceof EmptyCharacter))
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
			StaticConstantGame.application3D.enqueue(new Callable<Spatial>()
			{
				public Spatial call() throws Exception
				{
					if (getCurrentDirection().getTypeDirection() == Direction.UP)
					{
						model.move(0, 0, -movement);
						return model;
					}
					else if (getCurrentDirection().getTypeDirection() == Direction.LEFT)
					{
						model.move(-movement, 0, 0);
						return model;
					}
					else if (getCurrentDirection().getTypeDirection() == Direction.RIGHT)
					{
						model.move(movement, 0, 0);
						return model;
					}
					else if (getCurrentDirection().getTypeDirection() == Direction.DOWN)
					{
						model.move(0, 0, movement);
						return model;
					}
					else
					{
						return model;
					}
				}
			});
		}

		positionUpdate(falseMove, model.getLocalTranslation().getX(), model.getLocalTranslation()
				.getZ());

		return !falseMove;
	}

	public void positionUpdate(boolean falseAnimation, float xPosition, float zPosition)
	{
		StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(), getY(),
				new EmptyCharacter(getX(), getY()));
		setX((int) Math.ceil(zPosition));
		setY((int) Math.ceil(xPosition));
		StaticConstantGame.gameManager.getConcreteWorld().setCellCharacterWorld(getX(), getY(),
				this);
	}

	// attacco fisico
	@Override
	public int attackZero()
	{
		int x = getX() + getCurrentDirection().getXSelected();
		int y = getY() + getCurrentDirection().getYSelected();

		AbstractCharacter objectTarget = StaticConstantGame.gameManager.getConcreteWorld()
				.getCellCharacterWorld(x, y);

		if (objectTarget instanceof Hero)
		{
			Hero enemy = (Hero) objectTarget;
			int totalDamage = (int) (this.getDamageNear() * this.getFactorNear());
			enemy.modifyHearts(-totalDamage);
			enemy.setState(5);
			GamePanel3D.updateGuiNode = true;
			return 1;
		}
		return 0;
	}

	public void run()
	{
		while (isAlive())
		{
			update();
			try
			{
				Thread.sleep(StaticConstantGame.timeWarrior / 10);
			}
			catch (InterruptedException ex)
			{
				Logger.getLogger(Warrior3D.class.getName()).log(Level.SEVERE, null, ex);
			}
		}
	}

	public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName)
	{
		if (indexAnimation >= 2)
		{
			indexAnimation = random.nextInt(2);
			animChannel.setAnim(animationsName[indexAnimation], 0.05f);
			animChannel.setLoopMode(LoopMode.Loop);
		}
		else
		{
			animChannel.setAnim(animationsName[indexAnimation], 0.05f);
			animChannel.setLoopMode(LoopMode.Cycle);
		}
	}

	public void onAnimChange(AnimControl control, AnimChannel channel, String animName)
	{
	}

	public boolean isAlive()
	{
		return alive;
	}

	public void setAlive(boolean alive)
	{
		this.alive = alive;
	}
}
