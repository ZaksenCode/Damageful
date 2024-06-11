package me.zaksen.damageful.callback;

import me.zaksen.damageful.color.CustomColor;
import me.zaksen.damageful.number.TextParticle;
import net.minecraft.client.MinecraftClient;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;

import static me.zaksen.damageful.client.DamagefulClient.OPTIONS_DATA;

public class Callbacks {

    public Callbacks() {
        registerDamageCallback();
    }

    private void registerDamageCallback() {
        EntityDamagedCallback.EVENT.register(((entity, source, damage, isBlocked) -> {
            if(!OPTIONS_DATA.isEnabled()) {
                return ActionResult.PASS;
            }

            if(isBlocked) {
                return ActionResult.PASS;
            }

            MinecraftClient client = MinecraftClient.getInstance();

            if(client == null || client.world == null || client.player == null) {
                return ActionResult.PASS;
            }

            if(entity == client.player && !OPTIONS_DATA.isShowSelfDamage() ) {
                return ActionResult.PASS;
            }

            if(client.player.distanceTo(entity) > OPTIONS_DATA.getMaxDistance()) {
                return ActionResult.PASS;
            }

            float damageScale = Math.min(damage / entity.getMaxHealth(), 0.99f);
            float damagePercentage = (float) Math.floor(damageScale * 100);
            String text = getText(damage, damagePercentage);

            Vec3d particlePos = entity.getPos().add(OPTIONS_DATA.getOffset().x, entity.getHeight() + OPTIONS_DATA.getOffset().y, OPTIONS_DATA.getOffset().z);
            Vec3d particleVelocity = OPTIONS_DATA.getVelocity();

            if(OPTIONS_DATA.isRandomVelocityXZ()) {
                double x = Math.random() * particleVelocity.x;
                double z = Math.random() * particleVelocity.z;
                particleVelocity = new Vec3d(x, particleVelocity.y, z);
            }

            float velocityMultiplier = OPTIONS_DATA.getVelocityMultiplier();
            float gravityStrength = OPTIONS_DATA.getGravityStrength();
            int maxAge = OPTIONS_DATA.getLifeTime();

            CustomColor textColor = OPTIONS_DATA.getLowestDamageColor().gradient(OPTIONS_DATA.getHighestDamageColor(), damageScale);

            TextParticle particle = TextParticle.create()
            .text(Text.of(text))
            .color(
                    textColor.getAlpha(),
                    textColor.getRed(),
                    textColor.getGreen(),
                    textColor.getBlue()

            )
            .velocityMult(velocityMultiplier)
            .gravityStrength(gravityStrength)
            .age(maxAge)
            .scale(OPTIONS_DATA.getScale())
            .shadow(OPTIONS_DATA.isDrawShadow())
            .build(client.world, particlePos, particleVelocity);

            client.particleManager.addParticle(particle);

            return ActionResult.SUCCESS;
        }));
    }

    private String getText(float damage, float damagePercentage) {
        String text;

        if(OPTIONS_DATA.isShowPercentageText()) {
            String percenrageText = String.format("%.1f", damagePercentage);
            if(percenrageText.endsWith(".0")) {
                percenrageText = percenrageText.substring(0, percenrageText.length() - 2);
            }
            text = percenrageText + "%";
        } else {
            String roundedDamageText = String.format("%.1f", damage);
            if(roundedDamageText.endsWith(".0")) {
                roundedDamageText = roundedDamageText.substring(0, roundedDamageText.length() - 2);
            }
            text = roundedDamageText;
        }

        return text;
    }
}
