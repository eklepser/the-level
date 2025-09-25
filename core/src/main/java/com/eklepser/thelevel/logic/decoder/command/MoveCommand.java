package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.util.Direction;

public class MoveCommand extends Command {
    private final Direction direction;

    public MoveCommand(String[] args) {
        direction = Direction.getByName(args[0]);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("MOVE " + direction.name());
        target.setTargetWorldPos(direction);
    }

    @Override
    public Image[] getIcons(Entity target) {
        String iconPath;
        if (direction.name.equals("forward")) {
            iconPath = "ui/icon/move_forward_" + target.getFacingDirection() + ".png";
        } else iconPath = "ui/icon/move_" + direction.name + ".png";
        Image image = new Image(new Texture(Gdx.files.internal(iconPath)));
        return new Image[] {image};
    }
}
