package paulevs.corelib.model.shape;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.client.render.TileRenderer;
import net.minecraft.level.TileView;
import net.minecraft.tile.Tile;
import paulevs.corelib.math.LocationRandom;
import paulevs.corelib.math.MHelper;
import paulevs.corelib.math.TileFacing;
import paulevs.corelib.math.Vec3F;
import paulevs.corelib.math.Vec3I;
import paulevs.corelib.texture.UVPair;

public abstract class Shape {
	protected static final boolean[] RENDER_WORLD = new boolean[6];
	protected static final boolean[] RENDER_FACE = new boolean[6];
	protected static final UVPair[] DESTRUCTION = new UVPair[10];

	private static final LocationRandom RANDOM = new LocationRandom();
	protected static final Vec3F RENDER_POS = new Vec3F();
	protected static final Vec3F OFFSET = new Vec3F();
	protected static final Vec3I POS = new Vec3I();
	protected static TileRenderer renderer;
	protected static TileView tileView;
	protected static float light = 1;
	protected static int color = 0xFFFFFF;
	protected static Tile tile = Tile.STONE;
	protected static UVPair uv = new UVPair();
	protected static int meta = 0;
	protected static int destruction = -1;

	public static void setPos(int x, int y, int z) {
		POS.set(x, y, z);
	}

	public static void setOffset(float x, float y, float z) {
		OFFSET.set(x, y, z);
		updateRenderPos();
	}

	public static void setOffsetItem() {
		setOffset(-0.5F, -0.5F, -0.5F);
	}

	public static void resetOffset() {
		setOffset(0, 0, 0);
	}

	public static void setRenderer(TileRenderer renderer) {
		Shape.renderer = renderer;
	}

	public static void setTileView(TileView tileView) {
		Shape.tileView = tileView;
	}

	public static void setColor(int color) {
		Shape.color = color;
	}

	public static void setColorWhite() {
		Shape.color = 0xFFFFFF;
	}

	public static void setColorFromWorld() {
		Shape.color = tile.method_1600(tileView, POS.getX(), POS.getY(), POS.getZ());
	}

	public static void setUV(UVPair uv) {
		Shape.uv = destruction < 0 ? uv : getDestruction();
	}

	public static void setLight(float light) {
		Shape.light = light;
	}

	public static void setLightFromWorld() {
		Shape.light = tile.method_1604(tileView, POS.getX(), POS.getY(), POS.getZ());
	}

	public static void setTile(Tile tile) {
		Shape.tile = tile;
	}

	public static Random getRandomForLocation() {
		RANDOM.setSeed(POS.getX(), POS.getY(), POS.getZ());
		return RANDOM;
	}

	protected static void updateRenderPos() {
		RENDER_POS.set(POS).add(OFFSET);
	}

	public abstract void render();

	public static int getX() {
		return POS.getX();
	}

	public static int getY() {
		return POS.getY();
	}

	public static int getZ() {
		return POS.getZ();
	}

	public static TileView getTileView() {
		return tileView;
	}

	public static void setMeta(int meta) {
		Shape.meta = meta;
	}

	public static int getMeta() {
		return meta;
	}

	public static void setWorldCulling(boolean ignore) {
		if (ignore) {
			Arrays.fill(RENDER_WORLD, true);
		}
		else {
			for (TileFacing facing: TileFacing.getValues()) {
				int id = facing.getID();
				int x = facing.offsetX(POS.getX());
				int y = facing.offsetY(POS.getY());
				int z = facing.offsetZ(POS.getZ());
				RENDER_WORLD[id] = tile.method_1618(tileView, x, y, z, id);
			}
		}
	}

	public static void setFaceRendering(TileFacing facing, boolean renderFace) {
		RENDER_FACE[facing.getID()] = renderFace;
	}

	public static void drawAll() {
		Arrays.fill(RENDER_FACE, true);
	}

	protected boolean shouldRenderFace(TileFacing facing) {
		int index = facing.getID();
		return RENDER_WORLD[index] && RENDER_FACE[index];
	}

	public static Tile getTile() {
		return tile;
	}
	
	public static void setDestruction(int destruction) {
		Shape.destruction = destruction;
	}
	
	private static UVPair getDestruction() {
		if (DESTRUCTION[0] == null) {
			for (int i = 0; i < 10; i++) {
				DESTRUCTION[i] = UVPair.getVanillaUV(240 + i);
			}
		}
		return DESTRUCTION[MHelper.clamp(destruction, 0, 9)];
	}
}