package com.eklepser.thelevel.logic.decoder.execution;

import com.badlogic.gdx.math.Vector2;
import com.eklepser.thelevel.graphics.game.editor.CodeLine;
import com.eklepser.thelevel.logic.decoder.command.*;
import com.eklepser.thelevel.logic.decoder.condition.Condition;
import com.eklepser.thelevel.logic.decoder.condition.FacingCond;
import com.eklepser.thelevel.util.Direction;

import java.util.*;

public class Translator {
    private final Executor executor;
    private final List<CodeLine> codeLines;
    private final List<Instruction> allowedInstructions;
    private final List<String> allowedInstructionNames;

    public Translator(List<Instruction> allowedInstructions, List<CodeLine> codeLines, Executor executor) {
        this.executor = executor;
        this.codeLines = codeLines;
        this.allowedInstructions = allowedInstructions;
        allowedInstructionNames = allowedInstructions.stream()
            .map(instruction -> instruction.name).toList();
    }

    public TranslationResult translateAll() {
        Map<CodeLine, Command> codeMap = new HashMap<>();
        for (int i = 0; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            codeMap.put(currentLine, null);
            String text = uncomment(currentLine.getText());
            if (text.isEmpty()) continue;

            TranslationResult transResult = check(text, i);
            if (!transResult.success()) return transResult;

            Command cmd = translate(text, i);
            codeMap.put(currentLine, cmd);
        }
        if (codeMap.values().stream().allMatch(Objects::isNull)) return new TranslationResult(false, "Code field is empty", codeMap);
        return new TranslationResult(true, "Successfully running", codeMap);
    }

    private String uncomment(String text) {
        if (text.isBlank() || text.startsWith(";")) return "";
        if (!text.contains(";")) return text;
        String[] commentarySplited = text.split(";");
        if (commentarySplited.length > 1) {
            return commentarySplited[0];
        }
        else return "";
    }

    private TranslationResult check(String text, int lineNum) {
        // Instruction existing check:
        String[] words = text.split("\\s+");
        String instructionName = words[0].toLowerCase();
        Instruction instruction;
        if (allowedInstructionNames.contains(instructionName)) {
            instruction = Instruction.fromName(instructionName);
        }
        else {
            String message = String.format("Line %d: instruction %s is not allowed", lineNum + 1, words[0]);
            return new TranslationResult(false, message);
        }

        // Instruction format check:
        if (instruction.equals(Instruction.IF) && (words.length <= 2)) {
            String message = String.format("Line %d: instruction format is not valid", lineNum + 1);
            return new TranslationResult(false, message);
        }
        if ((instruction.argsNum != words.length - 1)
            && !instruction.equals(Instruction.IF)) {
            String message = String.format("Line %d: instruction format is not valid", lineNum + 1);
            return new TranslationResult(false, message);
        }

        // Instruction arguments check:
        if (instruction.argsType.equals("word") &&
            !instruction.allowedArgs.contains(words[1].toLowerCase())) {
            String message = String.format("Line %d: argument %s is not allowed", lineNum + 1, words[1]);
            return new TranslationResult(false, message);
        }

        if (instruction.argsType.equals("num")) {
            for (int i = 0; i < instruction.argsNum; i++)
            {
                try {
                    int num = Integer.parseInt(words[1 + i]);
                    if ((num < 1) || (num > codeLines.size())) {
                        String message = String.format("Line %d: argument %d is out of range", lineNum + 1, num);
                        return new TranslationResult(false, message);
                    }
                }
                catch (NumberFormatException e) {
                    String message = String.format("Line %d: argument %s is not allowed", lineNum + 1, words[1 + i]);
                    return new TranslationResult(false, message);
                }
            }
        }

        if (instruction.argsType.equals("cond")) {
            String conditionType = words[1].toLowerCase();
            String conditionArg = words[2].toLowerCase();
            if (!instruction.allowedArgs.contains(conditionType)) {
                String message = String.format("Line %d: condition %s is not allowed", lineNum + 1, words[1]);
                return new TranslationResult(false, message);
            }
            Condition cond = Condition.from(conditionType, conditionArg);
            String[] newWords = Arrays.copyOfRange(words, 3, words.length);
            String commandText = String.join(" ", newWords);
            TranslationResult result = check(commandText, lineNum);
            if (!result.success()) return result;
        }
        return new TranslationResult(true, "Successfully translated");
    }

    private Command translate(String text, int lineNum) {
        String[] words = text.split("\\s+");
        String instructionName = words[0].toLowerCase();
        Instruction instruction = Instruction.fromName(instructionName);

        Command cmd = null;
        switch (instruction) {
            case MOVE:
                cmd = new MoveCmd(Direction.getByName(words[1].toLowerCase()));
                break;
            case ROT:
                cmd = new RotateCmd(Direction.getByName(words[1].toLowerCase()));
                break;
            case GOTO:
                cmd = new GotoCmd(executor, Integer.parseInt(words[1]));
                break;
            case TP:
                int posX = Integer.parseInt(words[1]);
                int posY = Integer.parseInt(words[2]);
                cmd = new TeleportCmd(new Vector2(posX, posY));
                break;
            case IF:
                String conditionType = words[1].toLowerCase();
                String conditionArg = words[2].toLowerCase();
                Condition condition = Condition.from(conditionType, conditionArg);
                String[] newWords = Arrays.copyOfRange(words, 3, words.length);
                String commandText = String.join(" ", newWords);

                Command conditionCmd = translate(commandText, lineNum);
                cmd = new IfCmd(condition, conditionCmd, executor.getZones());
                break;
            case END:
                cmd = new EndCmd(executor);
                break;
            case NONE:
                cmd = new NoneCmd();
        }
        return cmd;
    }
}
