package paulevs.corelib;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.ItemType;
import paulevs.corelib.api.ModelRegistry;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.model.shape.ShapeBoxCustom;
import paulevs.corelib.texture.TextureAtlas;
import paulevs.corelib.texture.UVPair;

public class CoreLib implements ModInitializer {
	public static final int FACE_POS_X = 5;
	public static final int FACE_NEG_X = 4;
	public static final int FACE_POS_Y = 1;
	public static final int FACE_NEG_Y = 0;
	public static final int FACE_POS_Z = 2;
	public static final int FACE_NEG_Z = 3;

	public static final ItemView ITEM_VIEW = new ItemView();

	public static TextureAtlas blocksAtlas;
	public static TextureAtlas itemsAtlas;
	public static TextureAtlas selectedAtlas;

	@Override
	public void onInitialize() {
		System.out.println("CoreLib Initialized");

		// ModelRegistry.addItemModel(ItemType.stick, new
		// ModelOBJ("/assets/corelib/test.obj",
		// "/assets/corelib/lum_icon.png"));

		Model test = new Model() {
			Shape box = new ShapeBoxCustom();

			@Override
			public void renderBlock() {
				Shape.setUV(particleUV());
				box.render();
			}

			@Override
			public void renderItem() {
				Shape.setUV(particleUV());
				Shape.setOffsetItem();
				box.render();
			}

			@Override
			public UVPair particleUV() {
				return this.getUV("texture");
			}

		};
		test.addTexture("texture", "/assets/corelib/lum_icon.png");
		// ModelRegistry.addItemModel(ItemType.stick, new
		// ModelOBJ("/assets/corelib/test.obj",
		// "/assets/corelib/lum_icon.png"));
		ModelRegistry.addItemModel(ItemType.stick, test);
	}

	public static float getAtlasSize() {
		return selectedAtlas == null ? 256 : selectedAtlas.getSize();
	}
}
