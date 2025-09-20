package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class NoneCommand extends Command {
    @Override
    public void execute(Entity target) {
        System.out.println("NONE");
    }

    @Override
    public Image[] getIcons(Entity target) {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/none.png")));
        return new Image[] {image};
    }
}
