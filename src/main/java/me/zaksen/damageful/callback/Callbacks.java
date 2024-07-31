package me.zaksen.damageful.callback;

import io.wispforest.owo.ui.core.Color;
import me.zaksen.damageful.number.TextParticle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;

import static me.zaksen.damageful.client.DamagefulClient.CONFIG;

public class Callbacks {

    public Callbacks() {
        registerDamageCallback();
    }

    private void registerDamageCallback() {
        EntityDamagedCallback.EVENT.register(((entity, damage) -> {
            if(!CONFIG.enabled()) {
                return ActionResult.PASS;
            }

            MinecraftClient client = MinecraftClient.getInstance();

            if(client == null || client.world == null || client.player == null) {
                return ActionResult.PASS;
            }

            if(entity == client.player && !CONFIG.showSelfDamage() ) {
                return ActionResult.PASS;
            }

            if(client.player.distanceTo(entity) > CONFIG.maxDistance()) {
                return ActionResult.PASS;
            }

            float damageScale = Math.max(0, Math.min(damage / entity.getMaxHealth(), 1.0f));
            float damagePercentage = (float) Math.floor(damageScale * 100);
            String text = getText(damage, damagePercentage);

            Vec3d particlePos = entity.getPos().add(CONFIG.offset().x, entity.getHeight() + CONFIG.offset().y, CONFIG.offset().z);
            Vec3d particleVelocity = CONFIG.velocity();

            if(CONFIG.randomVelocityXZ()) {
                double x = Math.random() * particleVelocity.x;
                double z = Math.random() * particleVelocity.z;
                particleVelocity = new Vec3d(x, particleVelocity.y, z);
            }

            float velocityMultiplier = CONFIG.velocityMultiplier();
            float gravityStrength = CONFIG.gravityStrength();
            int maxAge = CONFIG.lifeTime();

            Color textColor;

            if(damage > 0) {
                textColor = CONFIG.lowestDamageColor().interpolate(CONFIG.highestDamageColor(), damageScale);
            } else {
                textColor = CONFIG.healColor();
            }

            TextParticle particle = TextParticle.create()
            .text(Text.of(text))
            .color(textColor)
            .velocityMult(velocityMultiplier)
            .gravityStrength(gravityStrength)
            .age(maxAge)
            .scale(CONFIG.scale())
            .shadow(CONFIG.drawShadow())
            .build(client.world, particlePos, particleVelocity);

            client.particleManager.addParticle(particle);

            return ActionResult.SUCCESS;
        }));
    }

    private String getText(float damage, float damagePercentage) {
        String text;

        if(CONFIG.showPercentageText()) {
            String percenrageText = String.format("%.1f", damagePercentage);

            if(percenrageText.endsWith(".0")) {
                percenrageText = percenrageText.substring(0, percenrageText.length() - 2);
            }

            if (damage > 0) {
                text =  percenrageText + "%";
            } else {
                text = "+" +  percenrageText + "%";
            }
        } else {
            String roundedDamageText = String.format("%.1f", damage);

            if(roundedDamageText.endsWith(".0")) {
                roundedDamageText = roundedDamageText.substring(0, roundedDamageText.length() - 2);
            }

            if (damage > 0) {
                text = roundedDamageText;
            } else {
                text = "+" + roundedDamageText.substring(1);
            }
        }

        return text;
    }
}
