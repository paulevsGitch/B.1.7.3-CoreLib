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
import paulevs.corelib.api.ModelRegistry;
import paulevs.corelib.model.Model;
import paulevs.corelib.model.shape.Shape;

@Mixin(TileRenderer.class)
public class TileRendererMixin {
	@Shadow
	private TileView field_82;

	@Shadow
	public boolean field_81;

	@Inject(method = "method_57", at = @At("HEAD"), cancellable = true)
	private void renderBlock(Tile tile, int x, int y, int z, CallbackInfoReturnable<Boolean> info) {
		int meta = field_82.getTileMeta(x, y, z);
		Model model = ModelRegistry.getBlockModel(tile, meta);
		if (model != null) {
			Shape.setTileView(field_82);
			Shape.setPos(x, y, z);
			Shape.setTile(tile);
			Shape.setRenderer((TileRenderer) (Object) this);
			Shape.setMeta(meta);
			Shape.setWorldCulling(false);
			Shape.resetOffset();
			Shape.drawAll();

			model.renderBlock();

			info.setReturnValue(true);
			info.cancel();
		}
	}

	@Inject(method = "method_48", at = @At("HEAD"), cancellable = true)
	private void renderItem(Tile tile, int meta, float f, CallbackInfo info) {
		Model model = ModelRegistry.getBlockModel(tile, meta);
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