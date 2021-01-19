package com.pixcat.jellymonsters.game;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.pixcat.jellymonsters.Application;
import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawImage;
import com.pixcat.jellymonsters.graphics.Drawable;
import com.pixcat.jellymonsters.resource.Image;
import lombok.Getter;

import java.util.Collection;
import java.util.List;

public class Prop implements Drawable {

    private static final int heightMargin = 10;

    @Getter
    private final TileCoord coord;

    private final Image image;

    @JsonCreator
    public Prop(
            @JsonProperty("coord") TileCoord coord,
            @JsonProperty("imageFilename") String imageFilename) {
        this.coord = coord;
        this.image = Application.getResourceLoader().getImage(imageFilename);
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return List.of(new DrawImage(coord.getScreenX(), coord.getScreenY() - heightMargin, image));
    }
}
