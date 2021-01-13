package com.pixcat.jellymonsters.graphics;

import com.pixcat.jellymonsters.resource.Image;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawImage implements DrawCommand {

    private final int x;
    private final int y;
    private final Image image;

    @Override
    public void execute(Graphics graphics) {
        graphics.image(x, y, image.getWidth(), image.getHeight(), image.getPixels());
    }
}
