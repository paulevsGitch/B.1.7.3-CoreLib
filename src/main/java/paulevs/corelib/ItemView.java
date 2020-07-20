package paulevs.corelib;

import net.minecraft.entity.TileEntity;
import net.minecraft.level.TileView;
import net.minecraft.level.biome.Biome;
import net.minecraft.level.gen.BiomeSource;
import net.minecraft.level.gen.FixedBiomeSource;
import net.minecraft.tile.material.Material;

public class ItemView implements TileView
{
	private static final FixedBiomeSource BIOME_SRC = new FixedBiomeSource(Biome.PLAINS, 0.5D, 0.0D);
	private int tile;
	private int meta;

	public void setMeta(int meta)
	{
		this.meta = meta;
	}

	public void setTile(int tile)
	{
		this.meta = tile;
	}

	@Override
	public int getTileId(int x, int y, int z)
	{
		return x == 0 && y == 0 && z == 0 ? tile : 0;
	}

	@Override
	public TileEntity getTileEntity(int i, int j, int k)
	{
		return null;
	}

	@Override
	public float method_1784(int i, int j, int k, int i1)
	{
		return 15;
	}

	@Override
	public float getBrightness(int i, int j, int k)
	{
		return 1;
	}

	@Override
	public int getTileMeta(int x, int y, int z)
	{
		return meta;
	}

	@Override
	public Material getMaterial(int x, int y, int z)
	{
		return Material.AIR;
	}

	@Override
	public boolean isFullOpaque(int i, int j, int k)
	{
		return false;
	}

	@Override
	public boolean canSuffocate(int i, int j, int k)
	{
		return false;
	}

	@Override
	public BiomeSource getBiomeSource()
	{
		return BIOME_SRC;
	}
}