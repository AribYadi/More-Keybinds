package com.aribb.morekeybinds;

import java.lang.reflect.Method;

import org.lwjgl.glfw.GLFW;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;

public class MoreKeybindsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
    KeyBinding attackKb = KeyBindingHelper.registerKeyBinding(new KeyBinding(
      "key.morekeybinds.attack",
      InputUtil.Type.MOUSE,
      GLFW.GLFW_MOUSE_BUTTON_LEFT,
      "key.category.mousekeybinds"
    ));

    ClientTickEvents.END_CLIENT_TICK.register(client -> {
			while (attackKb.wasPressed()) {
				Method doAttack = MinecraftClient.getDeclaredMethod("doAttack");
        doAttack.setAccessible(true);
        doAttack.invoke(client);
			}
		});
	}
}
