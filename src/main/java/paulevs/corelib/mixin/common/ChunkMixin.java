package paulevs.corelib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.class_257;
import net.minecraft.level.Level;
import net.minecraft.level.chunk.Chunk;
import paulevs.corelib.storage.DoubleNibbleArray;

@Mixin(Chunk.class)
public class ChunkMixin {
	@Shadow
	public class_257 field_957; // Metadata array

	@Inject(method = "<init>*", at = @At("RETURN"))
	private void onConstructed(Level arg, byte[] bs, int i, int j, CallbackInfo ci) {
		field_957 = new DoubleNibbleArray(bs.length);
	}

	/*
	 * @Redirect(method = "<init>*", at = @At(value = "FIELD", target =
	 * "Lnet/minecraft/level/chunk/Chunk;field_957:Lnet/minecraft/class_257;",
	 * opcode = Opcodes.PUTFIELD)) private void onConstructed(Chunk chunk,
	 * class_257 value, Level arg, byte[] bs, int i, int j) { if (!(value
	 * instanceof DoubleNibbleArray)) field_957 = new
	 * DoubleNibbleArray(bs.length); }
	 */
}
