package paulevs.corelib.model.shape;

import net.minecraft.client.render.Tessellator;
import paulevs.corelib.math.TileFacing;
import paulevs.corelib.math.Vec3F;
import paulevs.corelib.texture.UVPair;

public class ShapeQuad extends Shape {
	final Vec3F[] positions = new Vec3F[4];
	UVPair pair = new UVPair();
	float colorRed = 1;
	float colorGreen = 1;
	float colorBlue = 1;
	boolean flat = false;
	TileFacing facing;

	public ShapeQuad() {}
	
	public ShapeQuad setFacing(TileFacing facing) {
		this.facing = facing;
		return this;
	}

	public ShapeQuad setFlat(boolean flat) {
		this.flat = flat;
		return this;
	}

	public ShapeQuad setPoint(int index, float x, float y, float z) {
		positions[index] = new Vec3F(x, y, z);
		return this;
	}

	@Override
	public void render() {
		if (flat) {
			renderFlat();
		}
		else {
			renderSmooth();
		}
	}

	private void renderFlat() {
		int x = facing == null ? POS.getX() : facing.offsetX(POS.getX());
		int y = facing == null ? POS.getY() : facing.offsetY(POS.getY());
		int z = facing == null ? POS.getZ() : facing.offsetZ(POS.getZ());
		float light = tile.method_1604(tileView, x, y, z);
		float r = light * colorRed;
		float g = light * colorGreen;
		float b = light * colorBlue;
		
		Tessellator.INSTANCE.colour(r, g, b);
		int i = 0;
		for (Vec3F pos: positions) {
			float u = (((i + 1) >> 1) & 1) == 0 ? pair.getStart().getX() : pair.getEnd().getX();
			float v = ((i >> 1) & 1) == 0 ? pair.getStart().getY() : pair.getEnd().getY();
			Tessellator.INSTANCE.vertex(pos.getX(), pos.getY(), pos.getZ(), u, v);
		}
	}

	private void renderSmooth() {
		
	}
	
	@Override
	protected boolean shouldRenderFace(TileFacing facing) {
		if (facing == null) {
			return true;
		}
		return super.shouldRenderFace(facing);
	}
}