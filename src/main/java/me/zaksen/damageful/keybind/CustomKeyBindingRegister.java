package me.zaksen.damageful.keybind;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.util.InputUtil;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Consumer;

public class CustomKeyBindingRegister {

    private final Set<CustomKeyBinding> keyBinds = new HashSet<>();
    private final String category;

    public CustomKeyBindingRegister(String category) {
        this.category = category;
    }

    public void registerBind(String keyName, int keyCode, InputUtil.Type type, Consumer<MinecraftClient> keyFunction) {
        keyBinds.add(
                new CustomKeyBinding(
                        keyName,
                        type,
                        keyCode,
                        category,
                        keyFunction
                )
        );
    }

    public void registerKeyBind(String keyName, int keyCode, Consumer<MinecraftClient> keyFunction) {
        this.registerBind(keyName, keyCode, InputUtil.Type.KEYSYM, keyFunction);
    }

    public void registerMouseBind(String keyName, int keyCode, Consumer<MinecraftClient> keyFunction) {
        this.registerBind(keyName, keyCode, InputUtil.Type.MOUSE, keyFunction);
    }

    public void registerKeyInputs() {
        keyBinds.forEach(KeyBindingHelper::registerKeyBinding);
        ClientTickEvents.END_CLIENT_TICK.register((client) -> keyBinds.forEach((bind) -> bind.process(client)));
    }
}
