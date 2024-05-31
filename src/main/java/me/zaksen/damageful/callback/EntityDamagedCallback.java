package me.zaksen.damageful.callback;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.util.ActionResult;

public interface EntityDamagedCallback {

    Event<EntityDamagedCallback> EVENT = EventFactory.createArrayBacked(EntityDamagedCallback.class, (listeners) -> (entity, source, damage, isBlocked) -> {
        for (EntityDamagedCallback listener : listeners) {
            ActionResult result = listener.interact(entity, source, damage, isBlocked);

            if(result != ActionResult.PASS) {
                return result;
            }
        }

        return ActionResult.PASS;
    });

    ActionResult interact(LivingEntity entity, DamageSource source, float damage, boolean isBlocked);

}
