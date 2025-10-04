package game.scene.menu.rendering.component;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.selection.rendering.BuilderSelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.resources.Assets;

public class BuilderButton extends TextButton {
    public BuilderButton(Game game) {
        super("Build level", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SelectionScreen(game, BuilderSelectionLayout.class));
            }
        });
    }
}
