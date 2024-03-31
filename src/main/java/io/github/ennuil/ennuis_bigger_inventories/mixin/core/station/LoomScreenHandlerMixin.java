package io.github.ennuil.ennuis_bigger_inventories.mixin.core.station;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.screen.LoomScreenHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LoomScreenHandler.class)
public abstract class LoomScreenHandlerMixin {
	@ModifyExpressionValue(
		method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V",
		at = @At(value = "CONSTANT", args = "intValue=143")
	)
	private int modifyOutputSlotX(int original, int syncId, PlayerInventory playerInventory) {
		return playerInventory.isTenfoursized() ? 161 : original;
	}

	// I'm fixing a Mojang bug that was already fixed on newer version?? oh my god
	@ModifyExpressionValue(
		method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V",
		at = @At(value = "CONSTANT", args = "intValue=58")
	)
	private int modifyOutputSlotY(int original, int syncId, PlayerInventory playerInventory) {
		// Yes, we unfix it on 9x4 mode, for Vanilla compat!
		return playerInventory.isTenfoursized() ? 57 : original;
	}

	@ModifyExpressionValue(
		method = "<init>(ILnet/minecraft/entity/player/PlayerInventory;Lnet/minecraft/screen/ScreenHandlerContext;)V",
		at = @At(value = "CONSTANT", args = "intValue=9")
	)
	private int modifyNines(int original, int syncId, PlayerInventory playerInventory) {
		return playerInventory.isTenfoursized() ? 10 : original;
	}

	@ModifyExpressionValue(method = "quickTransfer", at = @At(value = "CONSTANT", args = "intValue=31"))
	private int modify31(int original, PlayerEntity player) {
		return player.getWorld().inferTenfoursized() ? 4 + 10 * 3 : original;
	}

	@ModifyExpressionValue(method = "quickTransfer", at = @At(value = "CONSTANT", args = "intValue=40"))
	private int modify40(int original, PlayerEntity player) {
		return player.getWorld().inferTenfoursized() ? 4 + 10 * 4 : original;
	}
}
