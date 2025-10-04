package game.scene.menu.rendering.component;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.scene.selection.rendering.PlaySelectionLayout;
import game.scene.selection.rendering.SelectionScreen;
import game.resources.Assets;

public class LevelsButton extends TextButton {
    public LevelsButton(Game game) {
        super("Play user levels", Assets.getSkin());
        addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                game.setScreen(new SelectionScreen(game, PlaySelectionLayout.class));
            }
        });
    }
}
