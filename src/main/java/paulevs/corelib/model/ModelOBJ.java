package paulevs.corelib.model;

import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.model.shape.ShapeOBJ;
import paulevs.corelib.texture.UVPair;

public class ModelOBJ extends Model
{
	private final ShapeOBJ shape;

	public ModelOBJ(String model, String texture)
	{
		super();
		this.addTexture("texture", texture);
		shape = new ShapeOBJ(model);
	}

	@Override
	public void renderBlock()
	{
		Shape.setLightFromWorld();
		Shape.setColorFromWorld();
		Shape.resetOffset();
		shape.render();
	}

	@Override
	public void renderItem()
	{
		Shape.setOffsetItem();
		Shape.setColorFromWorld();
		Shape.setLight(1);
		shape.render();
	}

	@Override
	public void init()
	{
		super.init();
		shape.changeUV(this.getUV("texture"));
	}

	@Override
	public UVPair particleUV()
	{
		return getUV("texture");
	}
}