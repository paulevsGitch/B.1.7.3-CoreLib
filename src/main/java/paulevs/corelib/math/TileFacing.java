package paulevs.corelib.math;

public enum TileFacing {
	NEG_Y(0, 0, -1, 0),
	POS_Y(1, 0, 1, 0),
	POS_Z(2, 0, 0, 1),
	NEG_Z(3, 0, 0, -1),
	NEG_X(4, -1, 0, 0),
	POS_X(5, 1, 0, 0);
	
	private static final TileFacing[] VALUES = TileFacing.values();
	private final int id;
	private final int x;
	private final int y;
	private final int z;
	
	TileFacing(int id, int x, int y, int z) {
		this.id = id;
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public int getID() {
		return id;
	}
	
	public int offsetX(int x) {
		return x + this.x;
	}
	
	public int offsetY(int y) {
		return y + this.y;
	}
	
	public int offsetZ(int z) {
		return z + this.z;
	}
	
	public static TileFacing[] getValues() {
		return VALUES;
	}
	
	public static TileFacing fromID(int id) {
		return VALUES[id];
	}
}
