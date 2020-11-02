package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;

@RequiredArgsConstructor
public class ProcessingGraphics implements Graphics {
    private final PApplet processing;

    @Override
    public void text(String text) {
        processing.background(255, 255, 255);

        processing.fill(0, 0, 0);
        processing.text(text, 100, 100);
    }
}
