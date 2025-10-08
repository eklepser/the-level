package game.scene.level.logic.command;

import game.common.logic.entity.Entity;

public abstract class Command {
    protected int lineNum;

    public Command(int lineNum) {
        this.lineNum = lineNum;
    }

    public abstract void execute(Entity target);

    public int getLineNum() {
        return lineNum;
    }
}
