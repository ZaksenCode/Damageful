package me.zaksen.damageful.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.ActionResult;

public interface EntityDamagedCallback {

    Event<EntityDamagedCallback> EVENT = EventFactory.createArrayBacked(EntityDamagedCallback.class, (listeners) -> (entity, damage) -> {
        for (EntityDamagedCallback listener : listeners) {
            ActionResult result = listener.damage(entity, damage);

            if(result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult damage(LivingEntity entity, float damage);

}
