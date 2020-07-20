package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.TileRenderer;
import net.minecraft.client.render.entity.ItemRenderer;
import paulevs.corelib.CoreLib;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin
{
	@Shadow
	private TileRenderer field_1708;

	@Inject(method = "method_1483", at = @At("HEAD"), cancellable = true)
	public void method_1483(int x, int y, int u, int v, int width, int height, CallbackInfo info)
	{
		float scale = CoreLib.getAtlasSize();
		if (scale != 256)
		{
			CoreLib.selectedAtlas.bind();
			Tessellator.INSTANCE.start();
			Tessellator.INSTANCE.vertex(x, y + height, 0, u / scale, (v + height) / scale);
			Tessellator.INSTANCE.vertex(x + width, y + height, 0, (u + width) / scale, (v + height) / scale);
			Tessellator.INSTANCE.vertex(x + width, y, 0, (u + width) / scale, v / scale);
			Tessellator.INSTANCE.vertex(x, y, 0, u / scale, v / scale);
			Tessellator.INSTANCE.draw();
			info.cancel();
		}
	}

	/*
	 * @Inject(method = "render", at = @At("HEAD"), cancellable = true) public
	 * void renderCustom(ItemEntity entity, double d, double d1, double d2,
	 * float f, float f1, CallbackInfo info) { ItemType item =
	 * entity.item.getType(); int meta = entity.item.getDamage(); Model model =
	 * ModelRegistry.getItemModel(item, meta); if (model != null) {
	 * Shape.setTileView(CoreLib.ITEM_VIEW); Shape.setPos(0, 0, 0);
	 * //Shape.setTile(null); Shape.setRenderer(field_1708);
	 * Shape.setMeta(meta); Shape.setWorldCulling(true); Shape.resetOffset();
	 * Shape.drawAll();
	 * 
	 * Tessellator.INSTANCE.start(); Tessellator.INSTANCE.method_1697(0.0F,
	 * -1.0F, 0.0F); model.renderItem(); Tessellator.INSTANCE.draw();
	 * info.cancel(); } }
	 */

	@ModifyConstant(method = "render", constant = @Constant(floatValue = 256.0F), expect = 3)
	private float changeSizeF(float original)
	{
		return CoreLib.getAtlasSize();
	}
}
