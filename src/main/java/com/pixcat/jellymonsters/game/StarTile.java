package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixcat.jellymonsters.Application;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawImage;
import com.pixcat.jellymonsters.graphics.Drawable;
import com.pixcat.jellymonsters.resource.Image;

import java.util.Collection;
import java.util.List;

public class StarTile implements Drawable {

    private final TileCoord coord;
    private final Image image = Application.getResourceLoader().getImage("star_tile.png");

    @JsonCreator
    private StarTile(@JsonProperty("coord") TileCoord coord) {
        this.coord = coord;
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return List.of(new DrawImage(coord.getScreenX(), coord.getScreenY(), image));
    }
}
