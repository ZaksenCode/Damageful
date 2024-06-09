package me.zaksen.damageful.keybind;

import me.zaksen.damageful.clothconfig.MainConfigScreenBuilder;
import org.lwjgl.glfw.GLFW;

public class CustomKeyBindingContainer {

    public CustomKeyBindingContainer() {
        CustomKeyBindingRegister register = new CustomKeyBindingRegister("category.damageful");

        register.registerKeyBind("key.damageful.config_screen", GLFW.GLFW_KEY_H, (client) -> client.setScreen(
                MainConfigScreenBuilder.create(client.currentScreen)
        ));

        register.registerKeyInputs();
    }
}