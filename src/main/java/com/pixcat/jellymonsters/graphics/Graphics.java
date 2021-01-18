package com.pixcat.jellymonsters.graphics;

public interface Graphics {

    void clear(Color color);

    void text(int x, int y, String text);

    void circle(int x, int y, int radius);

    void rect(int x, int y, int width, int height, Color color);

    void image(int x, int y, int width, int height, int[] pixels);
}
