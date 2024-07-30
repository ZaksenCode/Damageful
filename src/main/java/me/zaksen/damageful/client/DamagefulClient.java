package me.zaksen.damageful.client;

import me.zaksen.damageful.callback.Callbacks;
import me.zaksen.damageful.config.MainConfig;
import me.zaksen.damageful.keybind.CustomKeyBindingContainer;
import net.fabricmc.api.ClientModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DamagefulClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(DamagefulClient.class);
    public static final MainConfig CONFIG = MainConfig.createAndLoad();

    @Override
    public void onInitializeClient() {
        new Callbacks();
        new CustomKeyBindingContainer();
    }
}
