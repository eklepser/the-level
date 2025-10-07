package game.scene.level.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.scene.level.logic.execution.Executor;
import game.common.logic.entity.Entity;

public class GotoCommand extends Command {
    private final int targetLineNum;
    private final Executor executor;

    public GotoCommand(int lineNum, String[] args, Executor executor) {
        super(lineNum);

        this.targetLineNum = Integer.parseInt(args[0]);
        this.executor = executor;
    }

    @Override
    public void execute(Entity target) {
        System.out.println("GOTO " + targetLineNum);
        executor.execute(targetLineNum - 1, executor.getCodeMap());
    }

    @Override
    public Image[] getIcons() {
        Image image = new Image(new Texture(Gdx.files.internal("ui/icon/goto.png")));
        return new Image[] {image};
    }

    public int getLineNum() { return lineNum; }
}
