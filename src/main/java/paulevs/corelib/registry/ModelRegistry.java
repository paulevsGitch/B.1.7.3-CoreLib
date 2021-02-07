package paulevs.corelib.registry;

import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import io.github.minecraftcursedlegacy.api.registry.Id;
import io.github.minecraftcursedlegacy.api.registry.Registries;
import net.minecraft.item.ItemType;
import net.minecraft.tile.Tile;
import paulevs.corelib.model.Model;

public class ModelRegistry {
	private static final Map<Id, Map<Integer, Model>> TILE_REGISTRY = Maps.newHashMap();
	private static final Map<Id, Map<Integer, Model>> ITEM_REGISTRY = Maps.newHashMap();

	public static void addTileModel(Tile tile, Model BlockModel) {
		Map<Integer, Model> models = Maps.newHashMap();
		models.put(0, BlockModel);
		TILE_REGISTRY.put(Registries.TILE.getId(tile), models);
	}

	public static void addTileModel(Tile tile, int meta, Model BlockModel) {
		Id id = Registries.TILE.getId(tile);
		Map<Integer, Model> models = TILE_REGISTRY.get(id);
		if (models == null) {
			models = Maps.newHashMap();
			TILE_REGISTRY.put(id, models);
		}
		models.put(meta, BlockModel);
	}

	public static Model getTileModel(Tile tile, int meta) {
		Map<Integer, Model> models = TILE_REGISTRY.get(Registries.TILE.getId(tile));
		return models == null ? null : models.get(meta);
	}

	public static void addItemModel(ItemType item, Model itemModel) {
		Map<Integer, Model> models = Maps.newHashMap();
		models.put(0, itemModel);
		ITEM_REGISTRY.put(Registries.ITEM_TYPE.getId(item), models);
	}

	public static void addItemModel(ItemType item, int meta, Model itemModel) {
		Id id = Registries.ITEM_TYPE.getId(item);
		Map<Integer, Model> models = ITEM_REGISTRY.get(id);
		if (models == null) {
			models = Maps.newHashMap();
			ITEM_REGISTRY.put(id, models);
		}
		models.put(meta, itemModel);
	}

	public static Model getItemModel(ItemType item, int meta) {
		Map<Integer, Model> models = ITEM_REGISTRY.get(Registries.ITEM_TYPE.getId(item));
		return models == null ? null : models.get(meta);
	}

	public static List<Model> getTileModels() {
		List<Model> list = Lists.newArrayList();
		TILE_REGISTRY.forEach((id, models) -> {
			models.forEach((meta, model) -> {
				list.add(model);
			});
		});
		return list;
	}

	public static List<Model> getItemModels() {
		List<Model> list = Lists.newArrayList();
		ITEM_REGISTRY.forEach((id, models) -> {
			models.forEach((meta, model) -> {
				list.add(model);
			});
		});
		return list;
	}
}