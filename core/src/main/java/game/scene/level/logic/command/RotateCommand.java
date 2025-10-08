package game.scene.level.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.entity.Entity;
import game.common.logic.Direction;

public class RotateCommand extends Command {
    private final Direction direction;

    public RotateCommand(int lineNum, Direction direction) {
        super(lineNum);
        this.direction = direction;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("ROTATE " + direction.name());
        target.rotate(direction);
    }

    public Direction getDirection() { return direction; }
}
