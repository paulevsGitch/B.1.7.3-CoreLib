package paulevs.corelib.model.shape;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.client.render.TileRenderer;
import net.minecraft.level.TileView;
import net.minecraft.tile.Tile;
import paulevs.corelib.CoreLib;
import paulevs.corelib.math.LocationRandom;
import paulevs.corelib.math.Vec3F;
import paulevs.corelib.math.Vec3I;
import paulevs.corelib.texture.UVPair;

public abstract class ShapeItem {
	protected static final boolean[] RENDER_FACE = new boolean[6];

	private static final LocationRandom RANDOM = new LocationRandom();
	protected static final Vec3F RENDER_POS = new Vec3F();
	protected static final Vec3F OFFSET = new Vec3F();
	protected static final Vec3I POS = new Vec3I();
	protected static TileRenderer renderer;
	protected static TileView tileView = CoreLib.ITEM_VIEW;
	protected static float light = 1;
	protected static int color = 0xFFFFFFFF;
	protected static Tile tile = Tile.STONE;
	protected static UVPair uv = new UVPair();
	protected static int meta = 0;

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
		ShapeItem.renderer = renderer;
	}

	public static void setTileView(TileView tileView) {
		ShapeItem.tileView = tileView;
	}

	public static void setColor(int color) {
		ShapeItem.color = color;
	}

	public static void setColorWhite() {
		ShapeItem.color = 0xFFFFFFFF;
	}

	public static void setColorFromWorld() {
		ShapeItem.color = tile.method_1600(tileView, POS.getX(), POS.getY(), POS.getZ());
	}

	public static void setUV(UVPair uv) {
		ShapeItem.uv = uv;
	}

	public static void setLight(float light) {
		ShapeItem.light = light;
	}

	public static void setLightFromWorld() {
		ShapeItem.light = tile.method_1604(tileView, POS.getX(), POS.getY(), POS.getZ());
	}

	public static void setTile(Tile tile) {
		ShapeItem.tile = tile;
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
		ShapeItem.meta = meta;
	}

	public static int getMeta() {
		return meta;
	}

	public static void setFaceRendering(int index, boolean renderFace) {
		RENDER_FACE[index] = renderFace;
	}

	public static void drawAll() {
		Arrays.fill(RENDER_FACE, true);
	}

	protected static boolean shouldRenderFace(int index) {
		return RENDER_FACE[index];
	}
}