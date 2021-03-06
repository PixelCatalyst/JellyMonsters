package com.pixcat.jellymonsters.graphics;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawText implements DrawCommand {

    private final int x;
    private final int y;
    private final String text;

    @Override
    public void execute(Graphics graphics) {
        graphics.text(x, y, text);
    }
}
