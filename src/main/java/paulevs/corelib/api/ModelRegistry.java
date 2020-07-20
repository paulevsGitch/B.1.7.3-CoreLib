package paulevs.corelib.api;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.minecraft.item.ItemType;
import net.minecraft.tile.Tile;
import paulevs.corelib.model.Model;

public class ModelRegistry
{
	private static final Map<Integer, Map<Integer, Model>> BLOCK_REGISTRY = Maps.newHashMap();
	private static final Map<Integer, Map<Integer, Model>> ITEM_REGISTRY = Maps.newHashMap();

	public static void addBlockModel(Tile tile, Model BlockModel)
	{
		Map<Integer, Model> models = Maps.newHashMap();
		models.put(0, BlockModel);
		BLOCK_REGISTRY.put(tile.id, models);
	}

	public static void addBlockModel(Tile tile, int meta, Model BlockModel)
	{
		Map<Integer, Model> models = BLOCK_REGISTRY.get(tile.id);
		if (models == null)
		{
			models = Maps.newHashMap();
			BLOCK_REGISTRY.put(tile.id, models);
		}
		models.put(meta, BlockModel);
	}

	public static Model getBlockModel(Tile tile, int meta)
	{
		Map<Integer, Model> models = BLOCK_REGISTRY.get(tile.id);
		return models == null ? null : models.get(meta);
	}

	public static void addItemModel(ItemType tile, Model itemModel)
	{
		Map<Integer, Model> models = Maps.newHashMap();
		models.put(0, itemModel);
		ITEM_REGISTRY.put(tile.id, models);
	}

	public static void addItemModel(ItemType item, int meta, Model itemModel)
	{
		Map<Integer, Model> models = ITEM_REGISTRY.get(item.id);
		if (models == null)
		{
			models = Maps.newHashMap();
			ITEM_REGISTRY.put(item.id, models);
		}
		models.put(meta, itemModel);
	}

	public static Model getItemModel(ItemType item, int meta)
	{
		Map<Integer, Model> models = ITEM_REGISTRY.get(item.id);
		return models == null ? null : models.get(meta);
	}

	public static List<Model> getBlockModels()
	{
		List<Model> list = Lists.newArrayList();
		BLOCK_REGISTRY.forEach((id, models) -> {
			models.forEach((meta, model) -> {
				list.add(model);
			});
		});
		return list;
	}

	public static List<Model> getItemModels()
	{
		List<Model> list = Lists.newArrayList();
		ITEM_REGISTRY.forEach((id, models) -> {
			models.forEach((meta, model) -> {
				list.add(model);
			});
		});
		return list;
	}
}