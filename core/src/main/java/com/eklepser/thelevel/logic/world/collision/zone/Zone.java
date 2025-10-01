package com.eklepser.thelevel.logic.world.collision.zone;

import com.eklepser.thelevel.graphics.render.ZoneTile;
import com.eklepser.thelevel.graphics.screen.level.LevelLayout;
import com.eklepser.thelevel.graphics.screen.level.window.WinWindow;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.level.Level;

public abstract class Zone implements Collidable {
    private final int x;
    private final int y;

    public Zone(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public static Zone from(ZoneTile tile, LevelLayout levelLayout) {
        int x = tile.x;
        int y = tile.y;
        String[] properties = tile.properties;
        Executor executor = levelLayout.getEditor().getExecutor();

        Zone zone = switch (tile.type) {
            case "win" -> {
                WinWindow winWindow = levelLayout.getScreen().getWinWindow();
                yield new WinZone(x, y, winWindow, executor);
            }
            case "colored" -> new ColoredZone(x, y, executor, properties[0]);
            //case "level" -> new LevelZone(x, y, winWindow, executor);
            default -> null;
        };
        return zone;
    }

    // Getters:
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
}
