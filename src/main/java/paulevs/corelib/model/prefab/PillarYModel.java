package paulevs.corelib.model.prefab;

import paulevs.corelib.math.TileFacing;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.texture.UVPair;

public class PillarYModel extends Model {
	public PillarYModel(String textureTop, String textureSide) {
		this.addTexture("textureSide", textureSide);
		this.addTexture("textureTop", textureTop);
	}

	@Override
	public void renderBlock() {
		render();
	}

	@Override
	public void renderItem() {
		render();
	}
	
	private void render() {
		this.setTexture("textureSide");
		Shape.setFaceRendering(TileFacing.NEG_Y, false);
		Shape.setFaceRendering(TileFacing.POS_Y, false);
		FULL_CUBE.render();
		this.setTexture("textureTop");
		Shape.setFaceRendering(TileFacing.NEG_X, false);
		Shape.setFaceRendering(TileFacing.POS_X, false);
		Shape.setFaceRendering(TileFacing.POS_Y, true);
		Shape.setFaceRendering(TileFacing.NEG_Y, true);
		Shape.setFaceRendering(TileFacing.POS_Z, false);
		Shape.setFaceRendering(TileFacing.NEG_Z, false);
		FULL_CUBE.render();
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
		return this.getUV("textureSide");
	}
}
