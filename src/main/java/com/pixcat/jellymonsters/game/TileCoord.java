package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Value;

@Getter
@Value(staticConstructor = "of")
public class TileCoord {

    private static int tileSize = 60;
    private static int margin = 10;

    int x;
    int y;

    @JsonCreator
    private TileCoord(@JsonProperty("x") int x, @JsonProperty("y") int y) {
        this.x = x;
        this.y = y;
    }

    public int getScreenX() {
        return x * tileSize + margin;
    }

    public int getScreenY() {
        return y * tileSize + margin;
    }

    public int getWidth() {
        return tileSize;
    }

    public int getHeight() {
        return tileSize;
    }
}
