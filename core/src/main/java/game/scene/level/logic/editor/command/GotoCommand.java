package game.scene.level.logic.editor.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.editor.execution.Executor;
import game.scene.common.logic.entity.Entity;

public class GotoCommand extends Command {
    private final int lineNum;
    private final Executor executor;

    public GotoCommand(String[] args, Executor executor) {
        this.lineNum = Integer.parseInt(args[0]);
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("GOTO " + lineNum);
        executor.execute(lineNum - 1, executor.getCodeMap());
    }

    @Override
    public Image[] getIcons(Entity target) {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/goto.png")));
        return new Image[] {image};
    }

    public int getLineNum() { return lineNum; }
}
