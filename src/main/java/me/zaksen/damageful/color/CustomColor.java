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

    public CustomColor gradient(CustomColor color2, float progress) {
        float alphaProgress = getAlpha() + progress * (color2.getAlpha() - getAlpha());
        float redProgress = getRed() + progress * (color2.getRed() - getRed());
        float greenProgress = getGreen() + progress * (color2.getGreen() - getGreen());
        float blueProgress = getBlue() + progress * (color2.getBlue() - getBlue());
        return new CustomColor(alphaProgress / 255f, redProgress / 255f, greenProgress / 255f, blueProgress / 255f);
    }

    public static CustomColor gradient(CustomColor color1, CustomColor color2, float progress) {
        float alphaProgress = color1.getAlpha() + progress * (color2.getAlpha() - color1.getAlpha());
        float redProgress = color1.getRed() + progress * (color2.getRed() - color1.getRed());
        float greenProgress = color1.getGreen() + progress * (color2.getGreen() - color1.getGreen());
        float blueProgress = color1.getBlue() + progress * (color2.getBlue() - color1.getBlue());
        return new CustomColor(alphaProgress / 255f, redProgress / 255f, greenProgress / 255f, blueProgress / 255f);
    }
}
