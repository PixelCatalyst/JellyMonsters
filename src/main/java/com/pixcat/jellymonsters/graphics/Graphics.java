package com.pixcat.jellymonsters.graphics;

public interface Graphics {

    void clear(Color color);

    void text(String text);

    void circle(int x, int y, int radius);

    void image(int x, int y, int width, int height, int[] pixels);
}
