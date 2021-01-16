package com.pixcat.jellymonsters.gui.button;

import com.pixcat.jellymonsters.graphics.DrawCommand;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Collection;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ActionableButton implements Button {

    @Setter
    @Getter
    private int x;

    @Setter
    @Getter
    private int y;

    @Getter
    private final int width;

    @Getter
    private final int height;
    private final Runnable action;

    @Override
    public void onClick(int mouseX, int mouseY) {
        final int horizontalBound = x + width;
        final int verticalBound = y + height;
        if ((mouseX >= x) && (mouseX <= horizontalBound) &&
                (mouseY >= y) && (mouseY <= verticalBound)) {
            action.run();
        }
    }

    @Override
    public Collection<DrawCommand> getDrawCommands() {
        return null;
    }

    public static ButtonBuilder builderForAction(Runnable action) {
        return new ButtonBuilder().action(action);
    }

    public static class ButtonBuilder {

        private int x = 0;
        private int y = 0;
        private int width = 0;
        private int height = 0;
        private Runnable action;

        public ButtonBuilder x(int x) {
            this.x = x;
            return this;
        }

        public ButtonBuilder y(int y) {
            this.y = y;
            return this;
        }

        public ButtonBuilder width(int width) {
            this.width = width;
            return this;
        }

        public ButtonBuilder height(int height) {
            this.height = height;
            return this;
        }

        public ButtonBuilder action(Runnable action) {
            this.action = action;
            return this;
        }

        public ActionableButton build() {
            return new ActionableButton(x, y, width, height, action);
        }
    }
}
