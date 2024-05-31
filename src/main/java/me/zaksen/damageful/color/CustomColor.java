package me.zaksen.damageful.color;

import java.awt.*;

public class CustomColor {
    private float alpha;
    private float red;
    private float green;
    private float blue;

    public static CustomColor from(Color color) {
        CustomColor newColor = new CustomColor();
        newColor.setAlpha(color.getAlpha() / 255f);
        newColor.setRed(color.getRed() / 255f);
        newColor.setGreen(color.getGreen() / 255f);
        newColor.setBlue(color.getBlue() / 255f);
        return newColor;
    }

    public CustomColor(float alpha, float red, float green, float blue) {
        this.alpha = alpha;
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public CustomColor() {

    }

    public float getAlpha() {
        return alpha;
    }

    public void setAlpha(float alpha) {
        this.alpha = alpha;
    }

    public float getRed() {
        return red;
    }

    public void setRed(float red) {
        this.red = red;
    }

    public float getGreen() {
        return green;
    }

    public void setGreen(float green) {
        this.green = green;
    }

    public float getBlue() {
        return blue;
    }

    public void setBlue(float blue) {
        this.blue = blue;
    }

    public Color toColor() {
        return new Color(alpha / 255f, red / 255f, green / 255f, blue / 255f);
    }
}
