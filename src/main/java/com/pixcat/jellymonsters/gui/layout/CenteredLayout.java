package com.pixcat.jellymonsters.gui.layout;

import com.pixcat.jellymonsters.gui.button.Button;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor(staticName = "of")
public class CenteredLayout implements Layout {

    private final Button button;

    @Override
    public void setViewport(int width, int originY) {
        button.setX((width - button.getWidth()) / 2);
        button.setY(originY);
    }

    @Override
    public Collection<Button> getButtons() {
        return List.of(button);
    }

    @Override
    public int getHeight() {
        return button.getHeight();
    }
}
