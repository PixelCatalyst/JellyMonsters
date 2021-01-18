package com.pixcat.jellymonsters.graphics;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

@RequiredArgsConstructor
public class ProcessingGraphics implements Graphics {

    private final PApplet processing;

    @Override
    public void clear(Color color) {
        processing.background(color.getRgb());
    }

    @Override
    public void text(int x, int y, String text) {
        processing.fill(0, 0, 0);
        processing.text(text, x, y);
    }

    @Override
    public void circle(int x, int y, int radius) {
        processing.ellipse(x, y, radius, radius);
    }

    @Override
    public void rect(int x, int y, int width, int height, Color color) {
        processing.fill(color.getRgb());
        processing.stroke(color.getRgb());
        processing.noStroke();
        processing.rect(x, y, width, height);
    }

    @Override
    public void image(int x, int y, int width, int height, int[] pixels) {
        PImage image = processing.createImage(width, height, PConstants.ARGB);
        image.pixels = pixels;
        image.updatePixels();
        processing.image(image, x, y);
    }
}
