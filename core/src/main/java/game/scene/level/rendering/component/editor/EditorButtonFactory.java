package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import game.common.rendering.ButtonFactory;
import game.scene.level.window.HelpWindow;

public final class EditorButtonFactory extends ButtonFactory {
    public static TextButton createClearButton(EditorLayout editorLayout) {
        return createButton("Clear", editorLayout::clearRunning);
    }

    public static TextButton createHelpButton(HelpWindow helpWindow) {
        return createButton("Help", helpWindow::toggle);
    }

    public static TextButton createResetButton(EditorLayout editorLayout) {
        return createButton("Reset", editorLayout::resetRunning);
    }

    public static TextButton createRunButton(EditorLayout editorLayout) {
        return createButton("Run", editorLayout::run);
    }
}
