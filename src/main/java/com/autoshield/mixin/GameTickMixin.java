package com.autoshield.mixin;

import net.minecraft.client.MinecraftClient;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(MinecraftClient.class)
public class GameTickMixin {
    @Inject(at = @At("HEAD"), method = "tick")
    private void onClientTick(CallbackInfo info) {
        // Threat detection and shield raising happens in ShieldSystem
    }
}
