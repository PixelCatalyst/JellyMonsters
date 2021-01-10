package com.pixcat.jellymonsters.graphics;

import lombok.Getter;
import lombok.Value;

@Value(staticConstructor = "of")
public class Color {

    @Getter
    int rgb;

    public static Color white() {
        return new Color(0xFFFFFFFF);
    }
}
