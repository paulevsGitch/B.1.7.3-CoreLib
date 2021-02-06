package paulevs.corelib.mixin.common;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.class_445;
import net.minecraft.level.Level;
import net.minecraft.level.chunk.Chunk;
import net.minecraft.util.io.CompoundTag;
import paulevs.corelib.storage.DoubleNibbleArray;

@Mixin(class_445.class)
public class Class445Mixin {
	@Inject(method = "method_1479", at = @At("RETURN"))
	private static void replaceArray(Level arg, CompoundTag arg1, CallbackInfoReturnable<Chunk> info) {
		Chunk chunk = info.getReturnValue();
		chunk.field_957 = new DoubleNibbleArray(arg1.getByteArray("Data"));
		if (!chunk.field_957.method_1702()) {
			chunk.field_957 = new DoubleNibbleArray(chunk.tiles.length);
		}
	}
}
