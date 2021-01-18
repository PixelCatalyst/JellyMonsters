package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixcat.jellymonsters.graphics.DrawCircle;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.Drawable;

import java.util.Collection;
import java.util.List;

public class Monster implements Drawable {

    private final TileCoord coord;

    @JsonCreator
    public Monster(@JsonProperty("coord") TileCoord coord) {
        this.coord = coord;
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return List.of(new DrawCircle(coord.getScreenX() + 20, coord.getScreenY() + 20, 20));
    }
}
