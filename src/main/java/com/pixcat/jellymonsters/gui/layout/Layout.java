package com.pixcat.jellymonsters.gui.layout;

import com.pixcat.jellymonsters.gui.button.Button;

import java.util.Collection;

public interface Layout {

    void setViewport(int width, int originY);

    Collection<Button> getButtons();

    int getHeight();
}
