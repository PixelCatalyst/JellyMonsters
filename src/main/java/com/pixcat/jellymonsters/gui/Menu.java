package com.pixcat.jellymonsters.gui;

import com.pixcat.jellymonsters.graphics.DrawCommand;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.stream.Collectors;

@RequiredArgsConstructor(access = AccessLevel.PRIVATE)
public class Menu {

    private final ArrayList<Button> buttons;

    public void onClick(int mouseX, int mouseY) {
        for (Button b : buttons) {
            b.onClick(mouseX, mouseY);
        }
    }

    public Collection<DrawCommand> getDrawCommands() {
        return buttons.stream()
                .map(Button::getDrawCommands)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    public static MenuBuilder builderForViewport(int viewportWidth) {
        return new MenuBuilder(viewportWidth);
    }

    @RequiredArgsConstructor(access = AccessLevel.PRIVATE)
    public static class MenuBuilder {

        private final int viewportWidth;

        private int topMargin = 0;
        private int elementSpacing = 0;
        private int currentY = 0;

        private final ArrayList<Button> buttons = new ArrayList<>();

        public MenuBuilder topMargin(int topMargin) {
            this.topMargin = topMargin;
            return this;
        }

        public MenuBuilder elementSpacing(int elementSpacing) {
            this.elementSpacing = elementSpacing;
            return this;
        }

        public MenuBuilder addCenteredButton(Button button) {
            currentY = (currentY == 0 ? topMargin : currentY + elementSpacing);
            buttons.add(button);
            button.setX((viewportWidth - button.getWidth()) / 2);
            button.setY(currentY);
            currentY += button.getHeight();
            return this;
        }

        public Menu build() {
            return new Menu(buttons);
        }
    }
}
