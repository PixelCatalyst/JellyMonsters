package com.pixcat.jellymonsters.graphics;

import java.util.Collection;

public interface Drawable {

    Collection<DrawCommand> getDrawCommands();
}
