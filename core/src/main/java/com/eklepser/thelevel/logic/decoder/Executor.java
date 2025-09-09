package com.eklepser.thelevel.logic.decoder;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.actions.SequenceAction;
import com.eklepser.thelevel.graphics.ui.code_editor.CodeLine;
import com.eklepser.thelevel.logic.Cat;
import com.eklepser.thelevel.logic.Direction;

import java.util.List;
import java.util.Map;

public class Executor {
    private final Translator translator;
    private final Actor gameField;
    private final List<CodeLine> codeLines;
    private Map<CodeLine, Command> codeMap;
    private final Cat target; // need remove and be taken from gamefield

    public Executor(List<CodeLine> codeLines, Actor gameField, Cat target) {
        this.codeLines = codeLines;
        this.gameField = gameField;
        this.target = target;
        translator = new Translator(codeLines, target, gameField);
    }

    public void executeAll(int start, Map<CodeLine, Command> codeMap) {
        System.out.println("Running");
        gameField.clearActions();
        SequenceAction sequence = new SequenceAction();
        for (int i = start; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            Command currentCommand = codeMap.get(currentLine);
            if (currentCommand == null) continue;

            sequence.addAction(Actions.run(() -> currentLine.setCompleting(true)));
            sequence.addAction(Actions.delay(0.25f));
            sequence.addAction(Actions.run(() -> executeCommand(currentCommand, target)));
            sequence.addAction(Actions.run(() -> currentLine.setCompleting(false)));
        }
        gameField.addAction(sequence);
    }

    private void executeCommand(Command command, Cat cat) {
        {
            String text = command.getCmd();
            String[] parts = text.split("\\s+");
            if (parts[0].equals("MOVE")) {
                switch (parts[1]) {
                    case "UP":
                        System.out.println("UP");
                        cat.move(Direction.UP);
                        break;
                    case "RIGHT":
                        System.out.println("RIGHT");
                        cat.move(Direction.RIGHT);
                        break;
                    case "DOWN":
                        System.out.println("DOWN");
                        cat.move(Direction.DOWN);
                        break;
                    case "LEFT":
                        System.out.println("LEFT");
                        cat.move(Direction.LEFT);
                        break;
                }
            }
            else if (parts[0].equals("GOTO")) {
                int lineNum = Integer.parseInt(parts[1]);
                System.out.println("GOTO " + lineNum);
                executeAll(lineNum - 1, codeMap);
                command.getCodeLine().setCompleting(false);
            }
            else System.out.println("NONE");
        }
    }

    public String translateAll() {
        TranslationResult result = translator.translateAll();
        if (result.success()) {
            codeMap = result.getCodeMap();
            System.out.println(result.message());
            executeAll(0, codeMap);
        }
        else {
            System.out.println(result.message());
        }
        return result.message();
    }

    public void stop() {
        gameField.clearActions();
    }
}
