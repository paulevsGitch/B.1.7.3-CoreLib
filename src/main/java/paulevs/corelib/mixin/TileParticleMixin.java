package paulevs.corelib.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.particle.TileParticle;
import net.minecraft.entity.ParticleEntity;
import net.minecraft.level.Level;
import net.minecraft.tile.Tile;
import paulevs.corelib.CoreLib;
import paulevs.corelib.model.Model;
import paulevs.corelib.registry.ModelRegistry;
import paulevs.corelib.texture.UVPair;

@Mixin(TileParticle.class)
public abstract class TileParticleMixin extends ParticleEntity {
	@Shadow
	private Tile field_2383;

	@Shadow
	private int field_2384;

	public TileParticleMixin(Level level, double x, double y, double z, double d3, double d4, double d5) {
		super(level, x, y, z, d3, d4, d5);
	}

	@ModifyConstant(method = "method_2002", constant = @Constant(floatValue = 16.0F), expect = 3)
	private float changeSizeAtlas(float original) {
		return CoreLib.getAtlasSize() / 16F;
	}

	@ModifyConstant(method = "method_2002", constant = @Constant(floatValue = 0.015609375F), expect = 3)
	private float changeSizeOffset(float original) {
		return 3.999F / CoreLib.getAtlasSize();
	}

	@Inject(method = "method_2002", at = @At("HEAD"), cancellable = true)
	private void replaceParticles(Tessellator tesselator, float f, float f1, float f2, float f3, float f4, float f5,
			CallbackInfo info) {
		Model model = ModelRegistry.getBlockModel(field_2383, field_2384);
		if (model != null) {
			customParticle(model.particleUV(), tesselator, f, f1, f2, f3, f4, f5);
			info.cancel();
		}
	}

	private void customParticle(UVPair uv, Tessellator tesselator, float f, float f1, float f2, float f3, float f4,
			float f5) {
		float dx = uv.getEnd().getX() - uv.getStart().getX();
		float dy = uv.getEnd().getY() - uv.getStart().getY();
		float d = 4F / CoreLib.getAtlasSize();

		float u1 = uv.getStart().getX() + this.field_2636 * dx / 4F;
		float u2 = uv.getStart().getX() + d;
		float v1 = uv.getStart().getY() + this.field_2636 * dy / 4F;
		float v2 = uv.getStart().getY() + d;

		float var12 = 0.1F * this.field_2640;
		float px = (float) (this.prevX + (this.x - this.prevX) * f - field_2645);
		float py = (float) (this.prevY + (this.y - this.prevY) * f - field_2646);
		float pz = (float) (this.prevZ + (this.z - this.prevZ) * f - field_2647);
		float light = this.getBrightnessAtEyes(f);

		tesselator.colour(light * this.field_2642, light * this.field_2643, light * this.field_2644);
		tesselator.vertex((px - f1 * var12 - f4 * var12), (py - f2 * var12), (pz - f3 * var12 - f5 * var12), u1, v2);
		tesselator.vertex((px - f1 * var12 + f4 * var12), (py + f2 * var12), (pz - f3 * var12 + f5 * var12), u1, v1);
		tesselator.vertex((px + f1 * var12 + f4 * var12), (py + f2 * var12), (pz + f3 * var12 + f5 * var12), u2, v1);
		tesselator.vertex((px + f1 * var12 - f4 * var12), (py - f2 * var12), (pz + f3 * var12 - f5 * var12), u2, v2);
	}
}
