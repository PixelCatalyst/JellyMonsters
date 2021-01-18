package com.pixcat.jellymonsters.graphics;

import com.fasterxml.jackson.annotation.JsonCreator;
import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
public class Color {

    @Getter
    int rgb;

    public static Color white() {
        return new Color(0xFFFFFFFF);
    }

    @JsonCreator
    private Color(int rgb) {
        this.rgb = rgb;
    }

    @JsonCreator
    private Color(String hexRgb) {
        rgb = (int)Long.parseLong(hexRgb, 16);
    }
}
