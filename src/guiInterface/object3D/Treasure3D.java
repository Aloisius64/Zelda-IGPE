package guiInterface.object3D;

import com.jme3.animation.AnimChannel;
import com.jme3.animation.AnimControl;
import com.jme3.animation.AnimEventListener;
import com.jme3.animation.LoopMode;
import com.jme3.scene.Spatial;
import common3D.Builderable;
import common3D.URLModelProvider;
import gameLogic.character.Hero;
import gameLogic.object.Treasure;
import guiInterface.GamePanel3D;
import staticConstant.StaticConstantGame;

public class Treasure3D extends Treasure implements Builderable, AnimEventListener
{

	private Spatial model;
	private AnimChannel animChannel;
	private AnimControl animControl;

	public Treasure3D(final int xPosition, final int yPosition)
	{
		super(xPosition, yPosition, 500);
		initModelToScene(xPosition, yPosition);
	}

	public Treasure3D(final int xPosition, final int yPosition, final int score)
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
		model = StaticConstantGame.assetManager.loadModel(URLModelProvider.TREASURE_MODEL);
		model.setLocalScale(0.005f);
		model.move(yPosition, 0.0f, xPosition);
		StaticConstantGame.rootNode.attachChild(model);

		animControl = model.getControl(AnimControl.class);
		animControl.addListener(this);
		animChannel = animControl.createChannel();
		animChannel.setAnim("Stand");
	}

	public void onAnimCycleDone(AnimControl control, AnimChannel channel, String animName)
	{
		if (animName.equals("Opening"))
		{
			animChannel.setAnim("Open", 0.05f);
			animChannel.setLoopMode(LoopMode.Loop);
		}
	}

	public void onAnimChange(AnimControl control, AnimChannel channel, String animName)
	{
	}

	@Override
	public void manageContent(Hero hero)
	{
		super.manageContent(hero);
		GamePanel3D.updateGuiNode = true;
		animChannel.setAnim("Opening", 0.5f);
		animChannel.setLoopMode(LoopMode.Loop);
	}

}
