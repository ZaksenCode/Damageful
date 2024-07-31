package me.zaksen.damageful.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import io.wispforest.owo.ui.core.Color;
import net.minecraft.util.math.Vec3d;

@Modmenu(modId = "damageful")
@Config(name = "damageful", wrapperName = "MainConfig")
public class MainConfigModel {
    public boolean enabled = true;
    public boolean showPercentageText = false;
    public boolean showSelfDamage = false;
    public double maxDistance = 16;
    public float velocityMultiplier = 0.95f;
    public float gravityStrength = 0.8f;
    public int lifeTime = 30;
    public boolean randomVelocityXZ = true;
    public Vec3d velocity = new Vec3d(0.5, 0.25, 0.5);
    public Vec3d offset = new Vec3d(0, 0.25, 0);
    public float scale = 1.0f;
    public boolean drawShadow = true;
    public Color lowestDamageColor = new Color(255, 255, 0);
    public Color highestDamageColor = new Color(255, 0, 255);
    public Color healColor = new Color(50, 200, 50);
}
