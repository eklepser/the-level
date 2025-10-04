package game.scene.level.logic.editor.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.common.rendering.component.TextLabel;
import game.scene.level.logic.editor.execution.Executor;
import game.common.logic.entity.Entity;

public class NoneCommand extends Command {
    private final TextLabel toolTip;

    public NoneCommand(Executor executor) {
        this.toolTip = new TextLabel("Tooltip");
        toolTip.setVisible(false);
        executor.getEditor().getRoot().getChildren().add(toolTip);
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
