package game.scene.level.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.execution.Executor;
import game.common.logic.entity.Entity;

public class EndCommand extends Command {
    private final Executor executor;

    public EndCommand(int lineNum, String[] args, Executor executor) {
        super(lineNum);

        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("END");
        executor.stop();
    }

    @Override
    public Image[] getIcons() {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/end.png")));
        return new Image[] {image};
    }
}
