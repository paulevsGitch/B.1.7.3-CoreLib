package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.entity.ItemRenderer;
import paulevs.corelib.CoreLib;

@Mixin(ItemRenderer.class)
public class ItemRendererMixin {
	@Inject(method = "method_1483", at = @At("HEAD"), cancellable = true)
	public void method_1483(int x, int y, int u, int v, int width, int height, CallbackInfo info) {
		float scale = CoreLib.getAtlasSize();
		if (scale != 256) {
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

	@ModifyConstant(method = "render", constant = @Constant(floatValue = 256.0F), expect = 3)
	private float changeSizeF(float original) {
		return CoreLib.getAtlasSize();
	}
}
