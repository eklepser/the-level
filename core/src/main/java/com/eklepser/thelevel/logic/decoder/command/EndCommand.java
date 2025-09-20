package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.world.collision.Entity;

public class EndCommand extends Command {
    private final Executor executor;

    public EndCommand(String[] args, Executor executor) {
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("END");
        executor.getEditor().stop();
    }

    @Override
    public Image[] getIcons(Entity target) {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/end.png")));
        return new Image[] {image};
    }
}
