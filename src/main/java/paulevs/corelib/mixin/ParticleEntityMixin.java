package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.entity.ParticleEntity;

@Mixin(ParticleEntity.class)
public class ParticleEntityMixin {
	@ModifyConstant(method = "method_2002", constant = @Constant(floatValue = 16.0F), expect = 3)
	private float changeSizeAtlas(float original) {
		return 1;// CoreLib.getAtlasSize() / 16F;
	}

	@ModifyConstant(method = "method_2002", constant = @Constant(floatValue = 0.0624375F), expect = 3)
	private float changeSizeOffset(float original) {
		return 1;// 16F / CoreLib.getAtlasSize();
	}
}
