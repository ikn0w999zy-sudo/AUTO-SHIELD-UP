package com.autoshield.mixin;

import net.minecraft.client.network.ClientPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientPlayerEntity.class)
public class ClientPlayerInteractionMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onPlayerTick(CallbackInfo info) {
        // Player tick event
    }
}
