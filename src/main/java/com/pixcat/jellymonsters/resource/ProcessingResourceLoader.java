package com.pixcat.jellymonsters.resource;

import lombok.RequiredArgsConstructor;
import processing.core.PApplet;

@RequiredArgsConstructor
public class ProcessingResourceLoader implements ResourceLoader {

    private final PApplet processing;

    @Override
    public Image getImage(String filename) {
        return new ProcessingImage(processing.loadImage(filename));
    }
}
