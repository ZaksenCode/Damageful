package me.zaksen.damageful.mixin;

import me.zaksen.damageful.callback.EntityDamagedCallback;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LivingEntity.class)
public abstract class EntityMixin {

    @Inject(at = @At("TAIL"), method = "damage")
    public void damage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {

        if(cir.getReturnValue()) {
            EntityDamagedCallback.EVENT.invoker().interact((LivingEntity) (Object) this, source, amount, false);
            return;
        }

        EntityDamagedCallback.EVENT.invoker().interact((LivingEntity) (Object) this, source, amount, true);
    }
}
