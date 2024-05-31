package me.zaksen.damageful.client;

import me.zaksen.damageful.callback.Callbacks;
import me.zaksen.damageful.option.Options;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.MinecraftClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class DamagefulClient implements ClientModInitializer {

    public static final Logger LOGGER = LoggerFactory.getLogger(DamagefulClient.class);
    public static final Options OPTIONS_DATA = new Options(new File(MinecraftClient.getInstance().runDirectory, "config"), LOGGER);

    @Override
    public void onInitializeClient() {
        new Callbacks();
    }
}
