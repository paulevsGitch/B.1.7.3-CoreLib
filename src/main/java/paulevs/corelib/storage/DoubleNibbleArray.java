package paulevs.corelib.storage;

import net.minecraft.class_257;

public class DoubleNibbleArray extends class_257
{
	public DoubleNibbleArray(int i)
	{
		super(i << 1);
	}

	public DoubleNibbleArray(byte[] bs)
	{
		this(32768);
		if (bs.length < 32768) // If chunk is in old format
		{
			for (int i = 0; i < 32768; i++)
			{
				int var5 = i >> 1;
				int var6 = i & 1;

				if (var6 == 0)
				{
					this.field_2103[i] = (byte) (bs[var5] & 240 | i & 15);
				}
				else
				{
					this.field_2103[i] = (byte) (bs[var5] & 15 | (i & 15) << 4);
				}
			}
		}
		else
		{
			System.arraycopy(bs, 0, field_2103, 0, bs.length);
		}
	}

	@Override
	public int method_1703(int x, int y, int z)
	{
		int index = x << 11 | z << 7 | y;
		return ((int) this.field_2103[index]) & 255;
	}

	@Override
	public void method_1704(int x, int y, int z, int data)
	{
		int index = x << 11 | z << 7 | y;
		this.field_2103[index] = (byte) data;
	}
}
