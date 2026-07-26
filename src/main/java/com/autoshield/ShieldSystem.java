package com.autoshield;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.network.packet.c2s.play.PlayerInteractItemC2SPacket;
import net.minecraft.util.Hand;

public class ShieldSystem {
    private static boolean isEnabled = true;
    private static int lastHeldSlot = -1;
    private static boolean shieldEquipped = false;

    public static void initialize() {
        ClientTickEvents.START_CLIENT_TICK.register(ShieldSystem::onClientTick);
    }

    private static void onClientTick(MinecraftClient client) {
        if (client.player == null || client.world == null) return;

        // Check for toggle keybinding
        while (AutoShieldMod.toggleKeyBinding.wasPressed()) {
            isEnabled = !isEnabled;
            String status = isEnabled ? "Enabled" : "Disabled";
            client.player.sendMessage(
                net.minecraft.text.Text.literal("§6[Auto-Shield: " + status + "]"),
                true
            );
        }

        if (!isEnabled) return;

        // Detect threats
        if (detectThreat(client.player)) {
            equipShield(client.player);
            raiseShield(client.player, client);
        }
    }

    private static boolean detectThreat(PlayerEntity player) {
        // Scan for nearby hostile players
        double threatRadius = 16.0;
        for (Entity entity : player.getWorld().getOtherEntities(player, player.getBoundingBox().expand(threatRadius))) {
            if (entity instanceof PlayerEntity hostilePlayer && hostilePlayer.isAttackingWithWeapon()) {
                double distance = player.distanceTo(hostilePlayer);
                if (distance < 8.0) {
                    return true;
                }
            }
        }

        // Check for End Crystals
        for (Entity entity : player.getWorld().getOtherEntities(player, player.getBoundingBox().expand(16.0))) {
            if (entity.getType().toString().contains("end_crystal")) {
                double distance = player.distanceTo(entity);
                if (distance < 6.0) {
                    return true;
                }
            }
        }

        return false;
    }

    private static void equipShield(PlayerEntity player) {
        // Check off-hand first
        if (player.getOffHandStack().getItem() == Items.SHIELD) {
            shieldEquipped = true;
            return;
        }

        // Scan hotbar for shield
        for (int i = 0; i < 9; i++) {
            if (player.getInventory().getStack(i).getItem() == Items.SHIELD) {
                lastHeldSlot = player.getInventory().selectedSlot;
                player.getInventory().selectedSlot = i;
                shieldEquipped = true;
                return;
            }
        }
    }

    private static void raiseShield(PlayerEntity player, MinecraftClient client) {
        if (shieldEquipped && client.getNetworkHandler() != null) {
            // Send right-click packet to raise shield
            client.getNetworkHandler().sendPacket(
                new PlayerInteractItemC2SPacket(Hand.MAIN_HAND)
            );
        }
    }

    public static boolean isEnabled() {
        return isEnabled;
    }
}
