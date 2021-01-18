package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixcat.jellymonsters.graphics.Color;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawRect;
import com.pixcat.jellymonsters.graphics.Drawable;

import java.util.Collection;
import java.util.List;

public class GroundTile implements Drawable {

    private final TileCoord coord;
    private final Color color;

    @JsonCreator
    public GroundTile(@JsonProperty("coord") TileCoord coord, @JsonProperty("color") Color color) {
        this.coord = coord;
        this.color = color;
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return List.of(
                new DrawRect(coord.getScreenX(), coord.getScreenY(), coord.getWidth(), coord.getHeight(), color));
    }
}
