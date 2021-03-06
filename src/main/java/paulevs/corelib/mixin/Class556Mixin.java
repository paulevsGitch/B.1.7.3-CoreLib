package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.render.HandItemRenderer;
import paulevs.corelib.CoreLib;

@Mixin(HandItemRenderer.class)
public class Class556Mixin {
	@ModifyConstant(method = "method_1862", constant = @Constant(floatValue = 256.0F), expect = 3)
	private float changeSizeF(float original) {
		return CoreLib.getAtlasSize();
	}

	@ModifyConstant(method = "method_1862", constant = @Constant(floatValue = 0.001953125F), expect = 3)
	private float changeOffset(float original) {
		return 0;
	}
}
