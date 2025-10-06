package game.common.logic.zone;

import game.scene.level.window.WinWindow;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.entity.Entity;

public class WinZone extends Zone {
    private final WinWindow window;
    private boolean activated = false;

    public WinZone(int x, int y, WinWindow window) {
        super(x, y);
        this.window = window;
    }

    @Override
    public void onCollision(Entity entity) {
        if (!activated) {
            System.out.println("WIN COLLISION DETECTED");
            //executor.win();
        }
        activated = true;
    }

    public void setActivated(boolean activated) { this.activated = activated; }
}
