package com.pixcat.jellymonsters.graphics;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawRect implements DrawCommand {

    private final int x;
    private final int y;
    private final int width;
    private final int height;
    private final Color color;

    @Override
    public void execute(Graphics graphics) {
        graphics.rect(x, y, width, height, color);
    }
}
