package com.pixcat.jellymonsters.resource;

import lombok.RequiredArgsConstructor;
import processing.core.PImage;

@RequiredArgsConstructor
class ProcessingImage implements Image {

    private final PImage internalImage;

    @Override
    public int getWidth() {
        return internalImage.width;
    }

    @Override
    public int getHeight() {
        return internalImage.height;
    }

    @Override
    public int[] getPixels() {
        return internalImage.pixels;
    }
}
