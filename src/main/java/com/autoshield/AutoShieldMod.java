package com.autoshield;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

public class AutoShieldMod implements ClientModInitializer {
    public static final String MOD_ID = "auto-shield";
    public static KeyBinding toggleKeyBinding;

    @Override
    public void onInitializeClient() {
        // Register the toggle keybinding
        toggleKeyBinding = KeyBindingHelper.registerKeyBinding(
            new KeyBinding(
                "key.auto-shield.toggle",
                InputUtil.Type.KEYSYM,
                GLFW.GLFW_KEY_R,
                "category.auto-shield"
            )
        );

        // Initialize the shield system
        ShieldSystem.initialize();
    }
}
