package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import net.minecraft.level.TileView;
import net.minecraft.tile.Tile;
import paulevs.corelib.CoreLib;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;
import paulevs.corelib.registry.ModelRegistry;

@Mixin(TileRenderer.class)
public class TileRendererMixin {
	@Shadow
	private TileView field_82;

	@Shadow
	public boolean field_81;
	
	/**
	 * Destruction stage, if = -1 then normal block render, otherwise used as index
	 */
	@Shadow
	private int field_83;

	/**
	 * Main method to render block in the world
	 */
	@Inject(method = "method_57", at = @At("HEAD"), cancellable = true)
	private void renderBlock(Tile tile, int x, int y, int z, CallbackInfoReturnable<Boolean> info) {
		int meta = field_82.getTileMeta(x, y, z);
		Model model = ModelRegistry.getTileModel(tile, meta);
		if (model != null) {
			Shape.setTileView(field_82);
			Shape.setPos(x, y, z);
			Shape.setTile(tile);
			Shape.setRenderer((TileRenderer) (Object) this);
			Shape.setWorldCulling(false);
			Shape.setColorFromWorld();
			Shape.setLightFromWorld();
			Shape.setMeta(meta);
			Shape.resetOffset();
			Shape.drawAll();

			if (field_83 > -1) {
				Shape.setDestruction(field_83 - 240);
			}
			
			model.renderBlock();

			Shape.setDestruction(-1);
			info.setReturnValue(true);
			info.cancel();
		}
	}

	/**
	 * Main method to render block item in player hand
	 */
	@Inject(method = "method_48", at = @At("HEAD"), cancellable = true)
	private void renderItem(Tile tile, int meta, float f, CallbackInfo info) {
		Model model = ModelRegistry.getTileModel(tile, meta);
		if (model != null && model.hasItem()) {
			CoreLib.ITEM_VIEW.setTile(tile.id);
			CoreLib.ITEM_VIEW.setMeta(meta);
			field_82 = CoreLib.ITEM_VIEW;
			Shape.setTileView(CoreLib.ITEM_VIEW);
			Shape.setPos(0, 0, 0);
			Shape.setTile(tile);
			Shape.setRenderer((TileRenderer) (Object) this);
			Shape.setMeta(meta);
			Shape.setWorldCulling(true);
			Shape.resetOffset();
			Shape.drawAll();

			Tessellator.INSTANCE.start();
			Tessellator.INSTANCE.method_1697(0.0F, -1.0F, 0.0F);
			model.renderItem();
			Tessellator.INSTANCE.draw();

			info.cancel();
		}
	}
	
	@ModifyConstant(method = "*", constant = @Constant(floatValue = 256.0F), expect = 3)
	private float changeSizeF(float original) {
		return CoreLib.blocksAtlas.getSize();
	}

	@ModifyConstant(method = "*", constant = @Constant(doubleValue = 256.0D), expect = 3)
	private double changeSizeD(double original) {
		return CoreLib.blocksAtlas.getSize();
	}
}