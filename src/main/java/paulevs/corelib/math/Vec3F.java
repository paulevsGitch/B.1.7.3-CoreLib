package paulevs.corelib.math;

import java.util.Locale;

public class Vec3F {
	private float x;
	private float y;
	private float z;

	public Vec3F() {
		x = 0;
		y = 0;
		z = 0;
	}

	public Vec3F(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3F(Vec3F vec) {
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public float getX() {
		return x;
	}

	public Vec3F setX(float x) {
		this.x = x;
		return this;
	}

	public float getY() {
		return y;
	}

	public Vec3F setY(float y) {
		this.y = y;
		return this;
	}

	public float getZ() {
		return z;
	}

	public Vec3F setZ(float z) {
		this.z = z;
		return this;
	}

	public Vec3F setFloor(Vec3F vec) {
		x = MHelper.floor(vec.getX());
		y = MHelper.floor(vec.getY());
		z = MHelper.floor(vec.getZ());
		return this;
	}

	public Vec3F clone() {
		return new Vec3F(x, y, z);
	}

	public Vec3F invert() {
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	public float getLengthSqared() {
		return x * x + y * y + z * z;
	}

	public float getLength() {
		return (float) Math.sqrt(getLengthSqared());
	}

	public Vec3F normalize() {
		float l = getLengthSqared();
		if (l > 0) {
			l = (float) Math.sqrt(l);
			x /= l;
			y /= l;
			z /= l;
		}
		return this;
	}

	public Vec3F cross(Vec3F vec) {
		float cx = y * vec.z - z * vec.y;
		float cy = z * vec.x - x * vec.z;
		float cz = x * vec.y - y * vec.x;
		return this.set(cx, cy, cz);
	}

	public Vec3F crossNew(Vec3F vec) {
		float cx = y * vec.z - z * vec.y;
		float cy = z * vec.x - x * vec.z;
		float cz = x * vec.y - y * vec.x;
		return new Vec3F(cx, cy, cz);
	}

	public String toString() {
		return String.format(Locale.ROOT, "[%f, %f, %f]", x, y, z);
	}

	public Vec3F multiple(float n) {
		x *= n;
		y *= n;
		z *= n;
		return this;
	}

	public Vec3F add(Vec3F vec) {
		return add(vec.x, vec.y, vec.z);
	}

	public Vec3F add(Vec3F vec, float power) {
		return add(vec.x * power, vec.y * power, vec.z * power);
	}

	public Vec3F add(float x, float y, float z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vec3F add(float n) {
		return add(n, n, n);
	}

	public Vec3F set(Vec3F vec) {
		x = vec.x;
		y = vec.y;
		z = vec.z;
		return this;
	}

	public Vec3F set(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vec3F subtract(Vec3F vec) {
		return subtract(vec.x, vec.y, vec.z);
	}

	public Vec3F subtract(Vec3F vec, float power) {
		return subtract(vec.x * power, vec.y * power, vec.z * power);
	}

	public Vec3F subtract(float n) {
		return subtract(n, n, n);
	}

	public Vec3F subtract(float x, float y, float z) {
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public float dot(Vec3F vec) {
		return x * vec.x + y * vec.y + z * vec.z;
	}

	public float angle(Vec3F vec) {
		return (float) Math.acos(dot(vec) / Math.sqrt(getLengthSqared() * vec.getLengthSqared()));
	}

	public boolean equals(Object obj) {
		Vec3F vec = (Vec3F) obj;
		return vec == null ? false : x == vec.x && y == vec.y && z == vec.z;
	}

	public boolean isZero() {
		return x == 0 && y == 0 && z == 0;
	}

	public Vec3F divide(Vec3F vec) {
		x /= vec.x;
		y /= vec.y;
		z /= vec.z;
		return this;
	}

	public Vec3F set(Vec3I vec) {
		return set(vec.getX(), vec.getY(), vec.getZ());
	}
}
