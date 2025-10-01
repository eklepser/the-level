package com.eklepser.thelevel.logic.world.collision.zone;

import com.eklepser.thelevel.graphics.screen.level.window.WinWindow;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.entity.Entity;

public class WinZone extends Zone {
    private final WinWindow window;
    private final Executor executor;
    private boolean activated = false;

    public WinZone(int x, int y, WinWindow window, Executor executor) {
        super(x, y);
        this.window = window;
        this.executor = executor;
    }

    @Override
    public void onCollision(Entity entity) {
        if (!activated) {
            System.out.println("WIN COLLISION DETECTED");
            executor.win();
        }
        activated = true;
    }

    public void setActivated(boolean activated) { this.activated = activated; }
}
