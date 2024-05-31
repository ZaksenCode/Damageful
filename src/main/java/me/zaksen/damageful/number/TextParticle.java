package me.zaksen.damageful.number;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleTextureSheet;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.Tessellator;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import org.joml.Matrix4f;

import java.awt.*;

public class TextParticle extends Particle {

    private final Text text;
    private final float size;
    private final boolean drawShadow;

    public static Builder create() {
        return new Builder();
    }

    protected TextParticle(ClientWorld world, Vec3d pos, Vec3d velocity, Text text, float alpha, float red, float green, float blue, float velocityMultiplier, float gravityStrength, int maxAgeTicks, float scale, boolean drawShadow) {
        super(world, pos.x, pos.y, pos.z, velocity.x, velocity.y, velocity.z);
        this.text = text;
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
        this.velocityMultiplier = velocityMultiplier;
        this.gravityStrength = gravityStrength;
        this.maxAge = maxAgeTicks;
        this.size = 0.03f * scale;
        this.drawShadow = drawShadow;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float tickDelta) {
        var cameraPos = camera.getPos();

        float particleX = (float) (prevPosX + (x - prevPosX) * tickDelta - cameraPos.x);
        float particleY = (float) (prevPosY + (y - prevPosY) * tickDelta - cameraPos.y);
        float particleZ = (float) (prevPosZ + (z - prevPosZ) * tickDelta - cameraPos.z);

        Matrix4f matrix = new Matrix4f();
        matrix = matrix.translation(particleX, particleY, particleZ);
        matrix = matrix.rotate(camera.getRotation());
        matrix = matrix.scale(-size, -size, -size);

        var textRenderer = MinecraftClient.getInstance().textRenderer;
        var vertexConsumerProvider = VertexConsumerProvider.immediate(Tessellator.getInstance().getBuffer());

        float textX = textRenderer.getWidth(text) / -2.0F;
        float textY = 0.0F;

        int textColor = new Color(red, green, blue, alpha).getRGB();

        textRenderer.draw(text, textX, textY, textColor, drawShadow, matrix, vertexConsumerProvider, TextRenderer.TextLayerType.NORMAL, 0, 15728880);
        vertexConsumerProvider.draw();
    }

    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.CUSTOM;
    }

    public static class Builder {
        Text text = Text.empty();
        float alpha = 1.0f;
        float red = 1.0f;
        float green = 1.0f;
        float blue = 1.0f;
        float velocityMultiplier = 0.95f;
        float gravityStrength = 0.8f;
        int maxAge = 30;
        float scale = 1.0f;
        boolean drawShadow = true;

        public Builder() {

        }

        public Builder color(Color color) {
            return color(color.getAlpha(), color.getRed(), color.getGreen(), color.getBlue());
        }

        public Builder color(float alpha, float red, float green, float blue) {
            this.alpha = alpha;
            this.red = red;
            this.green = green;
            this.blue = blue;
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

        public Builder text(String text) {
            return text(Text.of(text));
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
            return new TextParticle(world, pos, velocity, text, alpha, red, green, blue, velocityMultiplier, gravityStrength, maxAge, scale, drawShadow);
        }
    }
}
