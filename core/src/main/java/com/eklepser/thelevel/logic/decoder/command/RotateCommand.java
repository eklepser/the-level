package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.collision.Entity;
import com.eklepser.thelevel.util.Direction;

public class RotateCommand extends Command {
    private final Direction direction;
    public RotateCommand(String[] args) {
        this.direction = Direction.getByName(args[0]);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("ROTATE " + direction.name());
        target.rotate(direction);
    }

    @Override
    public Image[] getIcons(Entity target) {
        String iconPath = "ui/icon/rot_" + direction.name + ".png";
        Image image = new Image(new Texture(Gdx.files.internal(iconPath)));
        return new Image[] {image};
    }

    public Direction getDirection() { return direction; }
}
