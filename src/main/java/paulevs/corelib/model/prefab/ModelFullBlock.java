package paulevs.corelib.model.prefab;

import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.model.shape.ShapeBoxCustom;
import paulevs.corelib.texture.UVPair;

public class ModelFullBlock extends Model {
	private static final Shape BOX = new ShapeBoxCustom();
	private final String texture;
	private UVPair pair;

	public ModelFullBlock(String texture, String path) {
		this.addTexture(texture, path);
		this.texture = texture;
	}

	@Override
	public void renderBlock() {
		this.setTexture(texture);
		Shape.setWorldCulling(false);
		Shape.setColorFromWorld();
		Shape.setLightFromWorld();
		Shape.setUV(getBlockUV());
		BOX.render();
	}

	@Override
	public void renderItem() {
		this.setTexture(texture);
		Shape.setOffsetItem();
		Shape.drawAll();
		Shape.setWorldCulling(true);
		Shape.setColorFromWorld();
		Shape.setLightFromWorld();
		Shape.setUV(getBlockUV());
		BOX.render();
		Shape.resetOffset();
	}
	
	@Override
	public boolean hasItem() {
		return true;
	}

	@Override
	public UVPair particleUV() {
		return getBlockUV();
	}

	private UVPair getBlockUV() {
		if (pair == null) {
			pair = this.getUV(texture);
		}
		return pair;
	}
}
