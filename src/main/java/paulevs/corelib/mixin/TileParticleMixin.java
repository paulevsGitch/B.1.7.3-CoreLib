package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

import net.minecraft.client.render.particle.TileParticle;
import paulevs.corelib.CoreLib;

@Mixin(TileParticle.class)
public class TileParticleMixin {
	@ModifyConstant(method = "method_2002", constant = @Constant(floatValue = 16.0F), expect = 3)
	private float changeSizeAtlas(float original) {
		return CoreLib.getAtlasSize() / 16F;
	}

	@ModifyConstant(method = "method_2002", constant = @Constant(floatValue = 0.015609375F), expect = 3)
	private float changeSizeOffset(float original) {
		return  3.999F / CoreLib.getAtlasSize();
	}
}
