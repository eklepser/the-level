package game.scene.level.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.entity.Entity;

public final class NoneCommand extends Command {

    public NoneCommand(int lineNum) {
        super(lineNum);
    }

    @Override
    public void execute( Entity target) {
        System.out.println("NONE");
    }
}
