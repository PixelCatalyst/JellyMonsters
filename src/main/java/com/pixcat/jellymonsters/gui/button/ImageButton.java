package com.pixcat.jellymonsters.gui.button;

import com.pixcat.jellymonsters.graphics.DrawCommand;
import com.pixcat.jellymonsters.graphics.DrawImage;
import com.pixcat.jellymonsters.resource.Image;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@RequiredArgsConstructor(staticName = "of")
public class ImageButton implements Button {

    private final Button button;
    private final Image image;

    @Override
    public int getX() {
        return button.getX();
    }

    @Override
    public int getY() {
        return button.getY();
    }

    @Override
    public void setX(int x) {
        button.setX(x);
    }

    @Override
    public void setY(int y) {
        button.setY(y);
    }

    @Override
    public int getWidth() {
        return button.getWidth();
    }

    @Override
    public int getHeight() {
        return button.getHeight();
    }

    @Override
    public void onClick(int mouseX, int mouseY) {
        button.onClick(mouseX, mouseY);
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        DrawCommand command = new DrawImage(button.getX(), button.getY(), image);
        Collection<DrawCommand> subCommands = button.getDrawCommands();
        if (subCommands != null) {
            return Stream.concat(Stream.of(command), button.getDrawCommands().stream())
                    .collect(Collectors.toList());
        }
        return List.of(command);
    }
}
