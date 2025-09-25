package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class MoveCommand extends Command {
    private final Direction direction;

    public MoveCommand(String[] args) {
        direction = Direction.getByName(args[0]);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        target.setMoving(direction);
    }

    @Override
    public Image[] getIcons(Entity target) {
        String iconPath = switch (direction.name) {
            case "forward" -> "ui/icon/move_forward_" + target.getFacingDirection() + ".png";
            default -> "ui/icon/move_" + direction.name + ".png";
        };
        Image image = new Image(new Texture(Gdx.files.internal(iconPath)));
        return new Image[] {image};
    }
}
