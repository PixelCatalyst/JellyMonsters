package com.pixcat.jellymonsters;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DrawText implements DrawCommand {

    private final String text;

    @Override
    public void execute(Graphics graphics) {
        graphics.text(text);
    }
}
