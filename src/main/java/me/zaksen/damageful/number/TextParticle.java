package me.zaksen.damageful.number;

import io.wispforest.owo.ui.core.Color;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.*;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

public class TextParticle extends Particle {

    private final Text text;
    private final float size;
    private final boolean drawShadow;
    private final Color color;

    public static Builder create() {
        return new Builder();
    }

    protected TextParticle(ClientWorld world, Vec3d pos, Vec3d velocity, Text text, Color color, float velocityMultiplier, float gravityStrength, int maxAgeTicks, float scale, boolean drawShadow) {
        super(world, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z);
        this.text = text;
        this.color = color;
        this.velocityMultiplier = velocityMultiplier;
        this.gravityStrength = gravityStrength;
        this.maxAge = maxAgeTicks;
        this.size = 0.03f * scale;
        this.drawShadow = drawShadow;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        float particleX = (float) (prevPosX + (x - prevPosX) * tickDelta - camera.getPos().x);
        float particleY = (float) (prevPosY + (y - prevPosY) * tickDelta - camera.getPos().y);
        float particleZ = (float) (prevPosZ + (z - prevPosZ) * tickDelta - camera.getPos().z);

        Matrix4f matrix = new Matrix4f();
        matrix = matrix.translation(particleX, particleY, particleZ);
        matrix = matrix.rotate(camera.getRotation());
        matrix = matrix.rotate((float) Math.PI, 0.0F, 1.0F, 0.0F);
        matrix = matrix.scale(-size, -size, -size);

        TextRenderer textRenderer = MinecraftClient.getInstance().textRenderer;
        VertexConsumerProvider.Immediate vertexProvider = MinecraftClient.getInstance().getBufferBuilders().getEntityVertexConsumers();

        float textX = textRenderer.getWidth(text) / -2.0F;
        float textY = 0.0F;

        int textColor = color.argb();

        textRenderer.draw(text, textX, textY, textColor, drawShadow, matrix, vertexProvider, TextRenderer.TextLayerType.NORMAL, 0, 15728880);
        vertexProvider.draw();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.CUSTOM;
    }

    public static class Builder {
        Text text = Text.empty();
        Color color;
        float velocityMultiplier = 0.95f;
        float gravityStrength = 0.8f;
        int maxAge = 30;
        float scale = 1.0f;
        boolean drawShadow = true;

        public Builder() {

        }

        public Builder color(Color color) {
            this.color = color;
            return this;
        }

        public Builder age(int age) {
            this.maxAge = age;
            return this;
        }

        public Builder text(Text text) {
            this.text = text;
            return this;
        }

        public Builder velocityMult(float velocityMultiplier) {
            this.velocityMultiplier = velocityMultiplier;
            return this;
        }

        public Builder gravityStrength(float gravityStrength) {
            this.gravityStrength = gravityStrength;
            return this;
        }

        public Builder scale(float scale) {
            this.scale = scale;
            return this;
        }

        public Builder shadow(boolean drawShadow) {
            this.drawShadow = drawShadow;
            return this;
        }

        public TextParticle build(ClientWorld world, Vec3d pos, Vec3d velocity) {
            return new TextParticle(world, pos, velocity, text, color, velocityMultiplier, gravityStrength, maxAge, scale, drawShadow);
        }
    }
}
