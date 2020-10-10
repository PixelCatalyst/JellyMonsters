package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;

@RequiredArgsConstructor
public class ProcessingGraphics implements Graphics {
    private final PApplet processing;

    @Override
    public void draw() {
        processing.background(255, 255, 1);
    }
}
