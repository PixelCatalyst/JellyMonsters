package com.pixcat.jellymonsters.gui.button;

import com.pixcat.jellymonsters.graphics.Drawable;

public interface Button extends Drawable {

    int getX();

    int getY();

    void setX(int x);

    void setY(int y);

    int getWidth();

    int getHeight();

    void onClick(int mouseX, int mouseY);
}
