package me.zaksen.damageful.keybind;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;

import java.util.function.Consumer;

public class CustomKeyBinding extends KeyBinding {

    private final Consumer<MinecraftClient> function;

    public CustomKeyBinding(String translationKey, InputUtil.Type type, int code, String category, Consumer<MinecraftClient> function) {
        super(translationKey, type, code, category);
        this.function = function;
    }

    public void process(MinecraftClient client) {
        if(isPressed()) {
            function.accept(client);
        }
    }
}
