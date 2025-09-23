package com.eklepser.thelevel.logic.decoder.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.eklepser.thelevel.graphics.common.TextLabel;
import com.eklepser.thelevel.logic.decoder.execution.Executor;
import com.eklepser.thelevel.logic.interaction.collision.Entity;

public class NoneCommand extends Command {
    private final TextLabel toolTip;

    public NoneCommand(Executor executor) {
        this.toolTip = new TextLabel("Tooltip");
        toolTip.setVisible(false);
        executor.getEditor().getRootTable().getChildren().add(toolTip);
    }

    @Override
    public void execute(Entity target) {
        System.out.println("NONE");
    }

    @Override
    public Image[] getIcons(Entity target) {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/none.png")));
        image.addListener(new ClickListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                float stageX = event.getStageX() - 10;
                float stageY = event.getStageY();
                toolTip.setPosition(stageX, stageY);
                toolTip.setVisible(true);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                super.enter(event, x, y, pointer, fromActor);
                System.out.println("Left icon");
                toolTip.setVisible(false);
            }
        });
        return new Image[] {image};
    }
}
