package paulevs.corelib.math;

import java.util.Locale;

public class Vec3I
{
	private int x;
	private int y;
	private int z;

	public Vec3I()
	{
		x = 0;
		y = 0;
		z = 0;
	}

	public Vec3I(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}

	public Vec3I(Vec3I vec)
	{
		this.x = vec.x;
		this.y = vec.y;
		this.z = vec.z;
	}

	public int getX()
	{
		return x;
	}

	public Vec3I setX(int x)
	{
		this.x = x;
		return this;
	}

	public int getY()
	{
		return y;
	}

	public Vec3I setY(int y)
	{
		this.y = y;
		return this;
	}

	public int getZ()
	{
		return z;
	}

	public Vec3I setZ(int z)
	{
		this.z = z;
		return this;
	}

	public Vec3I clone()
	{
		return new Vec3I(x, y, z);
	}

	public Vec3I invert()
	{
		x = -x;
		y = -y;
		z = -z;
		return this;
	}

	public float getLengthSqared()
	{
		return x * x + y * y + z * z;
	}

	public float getLength()
	{
		return (float) Math.sqrt(getLengthSqared());
	}

	public String toString()
	{
		return String.format(Locale.ROOT, "[%d, %d, %d]", x, y, z);
	}

	public Vec3I multiple(int n)
	{
		x *= n;
		y *= n;
		z *= n;
		return this;
	}

	public Vec3I add(Vec3I vec)
	{
		return add(vec.x, vec.y, vec.z);
	}

	public Vec3I add(int x, int y, int z)
	{
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	public Vec3I add(int n)
	{
		return add(n, n, n);
	}

	public Vec3I set(Vec3I vec)
	{
		x = vec.x;
		y = vec.y;
		z = vec.z;
		return this;
	}

	public Vec3I setFloor(Vec3F vec)
	{
		x = MHelper.floor(vec.getX());
		y = MHelper.floor(vec.getY());
		z = MHelper.floor(vec.getZ());
		return this;
	}

	public Vec3I set(int x, int y, int z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
		return this;
	}

	public Vec3I subtract(Vec3I vec)
	{
		return subtract(vec.x, vec.y, vec.z);
	}

	public Vec3I subtract(int x, int y, int z)
	{
		this.x -= x;
		this.y -= y;
		this.z -= z;
		return this;
	}

	public Vec3I subtract(int n)
	{
		return subtract(n, n, n);
	}
}
