/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package guiInterface.character3D;

import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterSphereShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.post.FilterPostProcessor;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import common.Direction;
import common3D.Builderable;
import common3D.UpdaterControl;
import gameLogic.character.AbstractCharacter;
import gameLogic.character.AbstractEnemy;
import gameLogic.character.AttackSphere;
import gameLogic.character.Hero;
import gameLogic.scene.AbstractObjectScene;
import staticConstant.StaticConstantGame;
import staticConstant.StaticConstantID;

/**
 * 
 * @author Aloisius
 */
public class AttackSphere3D extends AttackSphere implements Builderable
{

	private Spatial model;
	// explosion
	ParticleEmitter effect;
	FilterPostProcessor fpp;

	public AttackSphere3D(AbstractCharacter throwingCharacter)
	{
		super(throwingCharacter);
		initModelToScene(throwingCharacter.getX()
				+ throwingCharacter.getCurrentDirection().getXSelected(), throwingCharacter.getY()
				+ throwingCharacter.getCurrentDirection().getYSelected());
	}

	public AttackSphere3D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		// initModelToScene(xPosition, yPosition);
	}

	private void initModelToScene(int xPosition, int yPosition)
	{
		Sphere sphere = new Sphere(10, 10, 0.15f, true, false);
		sphere.setTextureMode(Sphere.TextureMode.Projected);

		Material matBullet = new Material(StaticConstantGame.assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		matBullet.setColor("Color", ColorRGBA.Orange);
		matBullet.setColor("GlowColor", ColorRGBA.Orange);

		Geometry geom = new Geometry("Sphere", sphere);
		geom.setMaterial(matBullet);

		model = geom;
		model.move(yPosition, 1.0f, xPosition);

		prepareEffect();
	}

	public void setSphereControl()
	{
		// Controllo per l'aggiornamento
		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);
	}

	public ParticleEmitter getEffect()
	{
		return effect;
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
		int xTarget = getX();
		int yTarget = getY();

		if (!isExploded())
		{
			AbstractCharacter characterTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellCharacterWorld(xTarget, yTarget);

			AbstractObjectScene sceneTarget = StaticConstantGame.gameManager.getConcreteWorld()
					.getCellBaseWorld(xTarget, yTarget);

			if (sceneTarget.getId() < StaticConstantID.LIMITS_ACCESSIBLE)
			{
				explosion();
				return;
			}

			if (isFromHero())
			{ // se una sfera dell'eroe colpisce un nemico
				if (characterTarget instanceof AbstractEnemy)
				{
					AbstractEnemy enemy = (AbstractEnemy) characterTarget;
					int totalDamage = (int) (getThrowingCharacter().getDamageFar() * getThrowingCharacter()
							.getFactorFar());
					enemy.modifyHearts(-totalDamage);
					explosion();
					return;
				}
			}
			else if (isAnEnemyOfHero())
			{ // se una sfera nemica colpisce l'eroe
				if (characterTarget instanceof Hero)
				{
					Hero hero = (Hero) characterTarget;
					int totalDamage = (int) (getThrowingCharacter().getDamageFar() * getThrowingCharacter()
							.getFactorFar());
					hero.modifyHearts(-totalDamage);
					hero.setState(5);
					explosion();
					return;
				}
			}

			float movement = 0.05f;
			int typeDirection = getCurrentDirection().getTypeDirection();

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

			positionUpdate(model.getLocalTranslation().getX(), model.getLocalTranslation().getZ());

		}
		else
		{
			effect.setLocalTranslation(getY(), 1.5f, getX());
			effect.emitAllParticles();
			StaticConstantGame.rootNode.detachChild(model);
		}
	}

	public void positionUpdate(float xPosition, float zPosition)
	{
		setX((int) Math.ceil(zPosition));
		setY((int) Math.ceil(xPosition));
	}

	private void prepareEffect()
	{
		int COUNT_FACTOR = 1;
		float COUNT_FACTOR_F = 1f;
		effect = new ParticleEmitter("Flame", ParticleMesh.Type.Triangle, 32 * COUNT_FACTOR);
		effect.setSelectRandomImage(true);
		effect.setStartColor(new ColorRGBA(1f, 0.4f, 0.05f, (float) (1f / COUNT_FACTOR_F)));
		effect.setEndColor(new ColorRGBA(.4f, .22f, .12f, 0f));
		effect.setStartSize(1.3f);
		effect.setEndSize(2f);
		effect.setShape(new EmitterSphereShape(Vector3f.ZERO, 1f));
		effect.setParticlesPerSec(0);
		effect.setGravity(0, -5, 0);
		effect.setLowLife(.4f);
		effect.setHighLife(.5f);
		effect.setInitialVelocity(new Vector3f(0, 7, 0));
		effect.setVelocityVariation(1f);
		effect.setImagesX(2);
		effect.setImagesY(2);
		Material mat = new Material(StaticConstantGame.assetManager,
				"Common/MatDefs/Misc/Particle.j3md");
		mat.setTexture("Texture",
				StaticConstantGame.assetManager.loadTexture("Effects/Explosion/flame.png"));
		effect.setMaterial(mat);
		// effect.setLocalScale(100);

	}
}
