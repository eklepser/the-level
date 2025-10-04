package game.scene.menu.rendering.component;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.resources.Assets;

public class ExitButton extends TextButton {
    public ExitButton() {
        super("EXIT", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("App terminated");
                Gdx.app.exit();
            }
        });
    }
}
