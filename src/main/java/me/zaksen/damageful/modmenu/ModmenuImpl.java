package me.zaksen.damageful.modmenu;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.zaksen.damageful.clothconfig.MainConfigScreenBuilder;

public class ModmenuImpl implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return MainConfigScreenBuilder::create;
    }
}
