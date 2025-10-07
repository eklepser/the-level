package game.scene.level.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.entity.Entity;
import game.common.logic.Direction;

public class MoveCommand extends Command {
    private final Direction direction;

    public MoveCommand(int lineNum, String[] args) {
        super(lineNum);

        direction = Direction.getByName(args[0].toLowerCase());
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        target.setTargetWorldPos(direction);
    }

    @Override
    public Image[] getIcons() {
        String iconPath;
        if (direction.name.equals("forward")) {
            iconPath = "ui/icon/move_forward_" + "up" + ".png";
        } else iconPath = "ui/icon/move_" + direction.name + ".png";
        Image image = new Image(new Texture(Gdx.files.internal(iconPath)));
        return new Image[] {image};
    }
}
