package game.scene.level.logic.command;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.entity.Entity;

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
