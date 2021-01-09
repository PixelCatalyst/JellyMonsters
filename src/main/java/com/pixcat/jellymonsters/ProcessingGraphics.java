package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;

@RequiredArgsConstructor
public class ProcessingGraphics implements Graphics {

    private final PApplet processing;

    @Override
    public void clear(Color color) {
        processing.background(color.getRgb());
    }

    @Override
    public void text(String text) {
        processing.fill(0, 0, 0);
        processing.text(text, 100, 100);
    }

    @Override
    public void circle(int x, int y, int radius) {
        processing.ellipse(x, y, radius, radius);
    }
}
