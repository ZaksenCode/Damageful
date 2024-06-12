package me.zaksen.damageful.mixin;

import me.zaksen.damageful.callback.EntityDamagedCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(LivingEntity.class)
public class EntityMixin {

    @Unique
    private float lastHealth = 0.0f;

    @Inject(method = "tick()V", at = @At("TAIL"))
    private void onTick(CallbackInfo info) {
        LivingEntity entity = (LivingEntity) (Object) this;
        World world = entity.getWorld();

        if (world == null || !world.isClient()) {
            return;
        }

        if (lastHealth != entity.getHealth()) {
            if(lastHealth > 0.0f) {
                EntityDamagedCallback.EVENT.invoker().damage(entity, lastHealth - entity.getHealth());
            }
            lastHealth = entity.getHealth();
        }
    }
}
