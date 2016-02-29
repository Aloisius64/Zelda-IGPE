package guiInterface.object3D;

import com.jme3.effect.ParticleEmitter;
import com.jme3.effect.ParticleMesh;
import com.jme3.effect.shapes.EmitterBoxShape;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Spatial;
import com.jme3.scene.shape.Sphere;
import common3D.Builderable;
import common3D.UpdaterControl;
import gameLogic.character.Hero;
import gameLogic.object.EmptyUtility;
import gameLogic.object.MagicSphere;
import guiInterface.GamePanel3D;
import staticConstant.StaticConstantGame;

public class MagicSphere3D extends MagicSphere implements Builderable
{

	private Spatial model;
	private ParticleEmitter emit;

	public MagicSphere3D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition);
		initModelToScene(xPosition, yPosition);
	}

	public MagicSphere3D(final int xPosition, final int yPosition, final int score)
	{
		super(xPosition, yPosition, score);
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
		// Effetto particelle
		emit = new ParticleEmitter("Emitter", ParticleMesh.Type.Point, 100);
		emit.setShape(new EmitterBoxShape(new Vector3f(yPosition, 0.0f, xPosition), new Vector3f(
				yPosition, 0.7f, xPosition)));
		emit.setGravity(0, 0, 0);
		emit.setLowLife(5);
		emit.setHighLife(50);
		emit.getParticleInfluencer().setInitialVelocity(new Vector3f(0, 0.5f, 0));
		emit.setImagesX(15);
		emit.setStartSize(0.05f);
		emit.setEndSize(0.05f);
		emit.setStartColor(ColorRGBA.Blue);
		emit.setEndColor(ColorRGBA.White);
		emit.setSelectRandomImage(true);

		Material mat = new Material(StaticConstantGame.assetManager,
				"Common/MatDefs/Misc/Particle.j3md");
		mat.setBoolean("PointSprite", true);
		mat.setTexture("Texture",
				StaticConstantGame.assetManager.loadTexture("Effects/Smoke/Smoke.png"));
		emit.setMaterial(mat);

		// Sfera dell'oggetto
		Sphere sphere = new Sphere(10, 10, 0.2f);
		sphere.setTextureMode(Sphere.TextureMode.Projected);

		Material matBullet = new Material(StaticConstantGame.assetManager,
				"Common/MatDefs/Misc/Unshaded.j3md");
		matBullet.setColor("Color", ColorRGBA.Blue);
		matBullet.setColor("GlowColor", ColorRGBA.Blue);

		Geometry geom = new Geometry("Sphere", sphere);
		geom.setMaterial(matBullet);

		model = geom;
		model.move(yPosition, 0.0f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);
		StaticConstantGame.rootNode.attachChild(emit);
		emit.emitAllParticles();

		// Controllo per l'aggiornamento
		UpdaterControl control = new UpdaterControl(this);
		model.addControl(control);
	}

	@Override
	public void update()
	{
		Hero hero = StaticConstantGame.gameManager.getHero();
		if ((getX() == hero.getX()) && (getY() == hero.getY()))
		{
			if (hero.getMagicEnergy() < hero.getMaximumMagicEnergy())
			{
				hero.modifyMagicEnergy(1);
			}
			emit.killAllParticles();
			GamePanel3D.updateGuiNode = true;
			StaticConstantGame.rootNode.detachChild(model);
			StaticConstantGame.rootNode.detachChild(emit);
			StaticConstantGame.gameManager.getConcreteWorld().setCellObjectWorld(getX(), getY(),
					new EmptyUtility(getX(), getY()));
			StaticConstantGame.gameManager.getUtilityObjectCollection().remove(this);
		}
	}
}
