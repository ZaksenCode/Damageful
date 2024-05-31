package me.zaksen.damageful.option;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.zaksen.damageful.color.CustomColor;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;

import java.io.*;

public class Options {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    private final File settingsFile;
    private final Logger logger;

    private Data optionsData = new Options.Data();

    public Options(File settingsFolder, Logger logger) {
        this.settingsFile = new File(settingsFolder, "damageful.json");
        this.logger = logger;
        if(!settingsFile.exists()) {
            settingsFolder.mkdirs();
            try {
                settingsFile.createNewFile();
                processSave();
            } catch (IOException e) {
                logger.error("Unable to create config file: ", e);
            }
        }
        processLoad();
    }

    public void processSave() {
        String result = GSON.toJson(optionsData);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(settingsFile))) {
            writer.write(result);
        } catch (IOException e) {
            logger.error("Unable to save settings file:", e);
        }
    }

    public void processLoad() {
        try(BufferedReader reader = new BufferedReader(new FileReader(settingsFile))) {
            optionsData = GSON.fromJson(reader, Options.Data.class);
        } catch (IOException e) {
            logger.error("Unable to load settings file:", e);
        }
    }

    public boolean isEnabled() {
        return optionsData.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.optionsData.enabled = enabled;
    }

    public boolean isShowPercentageText() {
        return optionsData.showPercentageText;
    }

    public void setShowPercentageText(boolean showPercentageText) {
        this.optionsData.showPercentageText = showPercentageText;
    }

    public boolean isShowSelfDamage() {
        return optionsData.showSelfDamage;
    }

    public void setShowSelfDamage(boolean showSelfDamage) {
        this.optionsData.showSelfDamage = showSelfDamage;
    }

    public double getMaxDistance() {
        return optionsData.maxDistance;
    }

    public void setMaxDistance(double maxDistance) {
        this.optionsData.maxDistance = maxDistance;
    }

    public float getVelocityMultiplier() {
        return optionsData.velocityMultiplier;
    }

    public void setVelocityMultiplier(float velocityMultiplier) {
        this.optionsData.velocityMultiplier = velocityMultiplier;
    }

    public float getGravityStrength() {
        return optionsData.gravityStrength;
    }

    public void setGravityStrength(float gravityStrength) {
        this.optionsData.gravityStrength = gravityStrength;
    }

    public int getLifeTime() {
        return optionsData.lifeTime;
    }

    public void setLifeTime(int lifeTime) {
        this.optionsData.lifeTime = lifeTime;
    }

    public boolean isRandomVelocityXZ() {
        return optionsData.randomVelocityXZ;
    }

    public void setRandomVelocityXZ(boolean randomVelocityXZ) {
        this.optionsData.randomVelocityXZ = randomVelocityXZ;
    }

    public Vec3d getVelocity() {
        return optionsData.velocity;
    }

    public void setVelocity(Vec3d velocity) {
        this.optionsData.velocity = velocity;
    }

    public Vec3d getOffset() {
        return optionsData.offset;
    }

    public void setOffset(Vec3d offset) {
        this.optionsData.offset = offset;
    }

    public float getScale() {
        return optionsData.scale;
    }

    public void setScale(float scale) {
        this.optionsData.scale = scale;
    }

    public boolean isDrawShadow() {
        return optionsData.drawShadow;
    }

    public void setDrawShadow(boolean drawShadow) {
        this.optionsData.drawShadow = drawShadow;
    }

    public CustomColor getLowestDamageColor() {
        return optionsData.lowestDamageColor;
    }

    public void setLowestDamageColor(CustomColor lowestDamageColor) {
        this.optionsData.lowestDamageColor = lowestDamageColor;
    }

    public CustomColor getHighestDamageColor() {
        return optionsData.highestDamageColor;
    }

    public void setHighestDamageColor(CustomColor highestDamageColor) {
        this.optionsData.highestDamageColor = highestDamageColor;
    }

    public static class Data {
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
        public CustomColor lowestDamageColor = new CustomColor(255, 255, 0, 0);
        public CustomColor highestDamageColor = new CustomColor(255, 0, 255, 0);
    }
}
