package me.zaksen.damageful.keybind;

import io.wispforest.owo.config.ui.ConfigScreen;
import org.lwjgl.glfw.GLFW;

public class CustomKeyBindingContainer {

    public CustomKeyBindingContainer() {
        CustomKeyBindingRegister register = new CustomKeyBindingRegister("category.damageful");

        register.registerKeyBind("key.damageful.config_screen", GLFW.GLFW_KEY_H, (client) -> {
            var provider = ConfigScreen.getProvider("damageful");
            client.setScreen(provider.apply(null));
        });

        register.registerKeyInputs();
    }
}