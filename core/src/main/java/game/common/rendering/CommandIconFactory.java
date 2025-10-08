package game.common.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.Direction;
import game.scene.level.logic.command.*;

public final class CommandIconFactory {
    private CommandIconFactory() { }

    public static Image[] commandIcon(Command command) {
        if (command instanceof MoveCommand moveCommand) {
            return moveIcon(moveCommand);
        }
        if (command instanceof RotateCommand rotateCommand) {
            return rotateIcon(rotateCommand);
        }
        if (command instanceof GotoCommand gotoCommand) {
            return gotoIcon(gotoCommand);
        }
        if (command instanceof IfCommand ifCommand) {
            return ifIcon(ifCommand);
        }
        if (command instanceof EndCommand endCommand) {
            return endIcon(endCommand);
        }
        return noneIcon();
    }

    private static Image[] moveIcon(MoveCommand moveCommand) {
        Direction direction = moveCommand.getDirection();
        String iconPath;
        if (direction.name.equals("forward")) {
            iconPath = "ui/icon/move_forward_" + "up" + ".png";
        } else iconPath = "ui/icon/move_" + direction.name + ".png";
        Image image = new Image(new Texture(Gdx.files.internal(iconPath)));
        return new Image[] {image};
    }

    private static Image[] rotateIcon(RotateCommand rotateCommand) {
        Direction direction = rotateCommand.getDirection();
        String iconPath = "ui/icon/rot_" + direction.name + ".png";
        Image image = new Image(new Texture(Gdx.files.internal(iconPath)));
        return new Image[] {image};
    }

    private static Image[] gotoIcon(GotoCommand gotoCommand) {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/goto.png")));
        return new Image[] {image};
    }

    private static Image[] ifIcon(IfCommand ifCmd) {
        Image ifImage = new Image(new Texture(Gdx.files.internal("ui/icon/if.png")));
        Image conditionIcon = ConditionIconFactory.conditionIcon(ifCmd.getCondition());
        return new Image[] {ifImage, conditionIcon};
    }

    private static Image[] endIcon(EndCommand endCommand) {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/end.png")));
        return new Image[] {image};
    }

    private static Image[] noneIcon() {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/none.png")));
        return new Image[] {image};
    }
}
