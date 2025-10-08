package game.scene.level.rendering.component.editor;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.utils.FocusListener;
import game.common.logic.Direction;
import game.common.rendering.TableLayout;
import game.common.rendering.component.TextLabel;

import java.util.ArrayList;
import java.util.List;

public final class CodeField extends TableLayout {
    private final EditorLayout root;
    private final int linesAmount;

    private ScrollPane codeScrollPane;
    private final List<CodeLine> codeLines = new ArrayList<>();
    private int selectedLine = -1;

    public CodeField(EditorLayout root, int linesAmount) {
        this.root = root;
        this.linesAmount = linesAmount;
    }

    @Override
    public void setup() {
        clear();
        for (int i = 0; i < linesAmount; i++) {
            row();
            CodeLine codeline = new CodeLine();
            TextLabel label = new TextLabel((String.valueOf(i + 1)));
            int finalI = i;
            codeline.addListener(new FocusListener() {
                @Override
                public void keyboardFocusChanged(FocusEvent event, Actor actor, boolean focused) {
                    if (focused) selectedLine = finalI;
                    codeScrollPane.scrollTo(codeline.getX(), codeline.getY() + codeline.getHeight(),
                        codeline.getWidth(), codeline.getHeight(), true, true);
                }
            });
            add(label).width(20).height(20).left().fillX().padRight(10);
            add(codeline).height(20).right().padBottom(1).colspan(3).fillX().expand().colspan(3);
            codeLines.add(codeline);
        }
    }

    // Class logic:
    public void clearCode() {
        codeLines.forEach(codeLine -> codeLine.setText(""));
    }

    public void setFocusOnLine(Direction direction) {
        switch (direction) {
            case UP -> setFocusOnLine(selectedLine - 1);
            case DOWN -> setFocusOnLine(selectedLine + 1);
        }
    }

    public void setFocusOnLine(int lineNum) {
        if ((lineNum >= 0) && (lineNum < codeLines.size())) {
            root.getRoot().getStage().setKeyboardFocus(codeLines.get(lineNum));
        }
    }

    public void setCompletingLine(int lineNum, boolean isCompleting) {
        if (lineNum < 0 || lineNum >= codeLines.size()) return;
        codeLines.get(lineNum).setCompleting(isCompleting);
    }

    public void clearCompleting() {
        codeLines.forEach(codeline -> codeline.setCompleting(false));
    }

    // Getters & setters:
    public void setCodeScrollPane(ScrollPane codeScrollPane) {
        this.codeScrollPane = codeScrollPane;
        setup();
    }

    public List<CodeLine> getCodeLines() { return codeLines; }

    public List<String> getCodeLinesText() {
        List<String> inputLines = new ArrayList<>();
        for (CodeLine codeLine : codeLines) {
            String text = codeLine.getText() == null ? "" : codeLine.getText();
            inputLines.add(text);
        }
        return inputLines;
    }
}
