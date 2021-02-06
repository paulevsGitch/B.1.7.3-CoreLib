package paulevs.corelib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemType;
import net.minecraft.tile.Tile;
import paulevs.corelib.api.ModelRegistry;
import paulevs.corelib.model.prefab.ModelFullBlock;
import paulevs.corelib.texture.TextureAtlas;

public class CoreLib implements ModInitializer {
	public static final int FACE_NEG_Y = 0;
	public static final int FACE_POS_Y = 1;
	public static final int FACE_POS_Z = 2;
	public static final int FACE_NEG_Z = 3;
	public static final int FACE_NEG_X = 4;
	public static final int FACE_POS_X = 5;

	public static final ItemView ITEM_VIEW = new ItemView();

	public static TextureAtlas blocksAtlas;
	public static TextureAtlas itemsAtlas;
	public static TextureAtlas selectedAtlas;

	@Override
	public void onInitialize() {
		System.out.println("CoreLib Initialized");
		ModelRegistry.addItemModel(ItemType.stick, new ModelFullBlock("icon", "/assets/corelib/icon.png"));
		ModelRegistry.addItemModel(ItemType.byId[Tile.WOOD.id], new ModelFullBlock("icon", "/assets/corelib/icon.png"));
	}

	public static float getAtlasSize() {
		return selectedAtlas == null ? 256 : selectedAtlas.getSize();
	}
}
