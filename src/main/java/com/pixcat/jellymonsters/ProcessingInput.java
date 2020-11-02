package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;
import processing.core.PConstants;

@RequiredArgsConstructor
public class ProcessingInput implements Input {
    private final PApplet processing;

    @Override
    public void fetchKeyboardState() {
        if (processing.keyCode == PConstants.UP) {
            return;
        }
    }
}
