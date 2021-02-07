package paulevs.corelib.model.prefab;

import paulevs.corelib.math.TileFacing;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.texture.UVPair;

public class PillarModel extends Model {
	public PillarModel(String textureTop, String textureSide) {
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
		if (Shape.getMeta() == 0) { // Oriented Y-Axis
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
		else if (Shape.getMeta() == 1) { // Oriented X-Axis
			this.setTexture("textureSide");
			Shape.setFaceRendering(TileFacing.NEG_X, false);
			Shape.setFaceRendering(TileFacing.POS_X, false);
			FULL_CUBE.render();
			this.setTexture("textureTop");
			Shape.setFaceRendering(TileFacing.NEG_X, true);
			Shape.setFaceRendering(TileFacing.POS_X, true);
			Shape.setFaceRendering(TileFacing.POS_Y, false);
			Shape.setFaceRendering(TileFacing.NEG_Y, false);
			Shape.setFaceRendering(TileFacing.POS_Z, false);
			Shape.setFaceRendering(TileFacing.NEG_Z, false);
			FULL_CUBE.render();
		}
		else { // Oriented Z-Axis
			this.setTexture("textureSide");
			Shape.setFaceRendering(TileFacing.NEG_Z, false);
			Shape.setFaceRendering(TileFacing.POS_Z, false);
			FULL_CUBE.render();
			this.setTexture("textureTop");
			Shape.setFaceRendering(TileFacing.NEG_X, false);
			Shape.setFaceRendering(TileFacing.POS_X, false);
			Shape.setFaceRendering(TileFacing.POS_Y, false);
			Shape.setFaceRendering(TileFacing.NEG_Y, false);
			Shape.setFaceRendering(TileFacing.POS_Z, true);
			Shape.setFaceRendering(TileFacing.NEG_Z, true);
			FULL_CUBE.render();
		}
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
