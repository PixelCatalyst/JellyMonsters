package com.pixcat.jellymonsters.graphics;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawCircle implements DrawCommand {

    private final int x;
    private final int y;
    private final int radius;

    @Override
    public void execute(Graphics graphics) {

        graphics.circle(x, y, radius);
    }
}
