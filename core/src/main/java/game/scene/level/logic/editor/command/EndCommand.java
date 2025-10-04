package game.scene.level.logic.editor.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.editor.execution.Executor;
import game.scene.common.logic.entity.Entity;

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
