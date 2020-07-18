package paulevs.corelib.math;

import java.util.Arrays;

public class MHelper {
	public static final float PI = 3.1415926535897932384626433832795F;
	public static final float MAX_ANGLE = PI * 2.0F;
	public static final float HALF_PI = PI * 0.5F;
	private static final float RAD_TO_DEG = 180F / PI;

	public static float clamp(float x, float min, float max) {
		return x < min ? min : x > max ? max : x;
	}

	public static int clamp(int x, int min, int max) {
		return x < min ? min : x > max ? max : x;
	}

	public static int floor(float x) {
		return x < 0 ? (int) (x - 1) : (int) x;
	}

	public static float clampAngle(float a) {
		return wrap(a, MAX_ANGLE);
	}

	public static float clampAngleDegrees(float a) {
		return wrap(a, 360);
	}

	public static float wrap(float x, float side) {
		return x - floor(x / side) * side;
	}

	public static float[] makeArray(int size, float value) {
		float[] array = new float[size];
		Arrays.fill(array, value);
		return array;
	}

	public static float sign(float x) {
		return x < 0 ? -1 : 1;
	}

	public static int sign(int x) {
		return x < 0 ? -1 : 1;
	}

	public static float signZ(float x) {
		return x == 0 ? 0 : x < 0 ? -1 : 1;
	}

	public static int signZ(int x) {
		return x == 0 ? 0 : x < 0 ? -1 : 1;
	}

	public static float max(float a, float b) {
		return b < a ? a : b;
	}

	public static float max(float a, float b, float c) {
		return max(max(a, b), c);
	}

	public static int max(int a, int b) {
		return b < a ? a : b;
	}

	public static int max(int a, int b, int c) {
		return max(max(a, b), c);
	}

	public static Vec3F max(Vec3F a, Vec3F b, Vec3F result) {
		result.setX(max(a.getX(), b.getX()));
		result.setY(max(a.getY(), b.getY()));
		result.setZ(max(a.getZ(), b.getZ()));
		return result;
	}

	public static Vec3F min(Vec3F a, Vec3F b, Vec3F result) {
		result.setX(min(a.getX(), b.getX()));
		result.setY(min(a.getY(), b.getY()));
		result.setZ(min(a.getZ(), b.getZ()));
		return result;
	}

	public static float lerp(float a, float b, float mix) {
		return a - (a - b) * mix;
	}

	public static Vec3F lerp(Vec3F a, Vec3F b, Vec3F output, float mix) {
		output.setX(lerp(a.getX(), b.getX(), mix));
		output.setY(lerp(a.getY(), b.getY(), mix));
		output.setZ(lerp(a.getZ(), b.getZ(), mix));
		return output;
	}

	public static float getSpeedTicks(float speedMS) {
		return speedMS / 20F;
	}

	public static float radToDeg(float angle) {
		return angle * RAD_TO_DEG;
	}

	public static float min(float a, float b) {
		return b > a ? a : b;
	}

	public static float min(float a, float b, float c) {
		return min(min(a, b), c);
	}

	public static int min(int a, int b) {
		return b > a ? a : b;
	}

	public static int min(int a, int b, int c) {
		return max(max(a, b), c);
	}
}