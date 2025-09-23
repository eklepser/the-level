package com.eklepser.thelevel.logic.decoder.execution;

import com.eklepser.thelevel.graphics.level.editor.CodeLine;
import com.eklepser.thelevel.logic.decoder.command.Command;
import com.eklepser.thelevel.logic.decoder.command.Instruction;
import com.eklepser.thelevel.logic.decoder.condition.ConditionPattern;

import java.util.*;

public class Translator {
    private final Executor executor;
    private final List<CodeLine> codeLines;
    private final Map<CodeLine, Command> codeMap;
    private final List<String> allowedInstructionNames;

    public Translator(List<Instruction> allowedInstructions, List<CodeLine> codeLines, Executor executor) {
        this.executor = executor;
        this.codeLines = codeLines;
        allowedInstructionNames = allowedInstructions.stream()
            .map(instruction -> instruction.name).toList();
        codeMap = new HashMap<>();
    }

    public TranslationResult translateAll() {
        codeMap.clear();
        for (int i = 0; i < codeLines.size(); i++) {
            CodeLine currentLine = codeLines.get(i);
            codeMap.put(currentLine, null);

            String text = uncomment(currentLine.getText());
            if (text.isEmpty()) continue;

            TranslationResult transResult = translate(text, i, false, currentLine);
            if (!transResult.success()) return transResult;
        }
        if (codeMap.values().stream().allMatch(Objects::isNull))
            return new TranslationResult(false, "Code field is empty");
        return new TranslationResult(true, "Successfully running");
    }

    public TranslationResult translate(String text, int lineNum, boolean isConditionCommand, CodeLine codeLine) {
        // Instruction existing check:
        String[] words = text.split("\\s+");
        String instructionName = words[0].toLowerCase();
        Instruction instruction;
        String[] args;

        if (allowedInstructionNames.contains(instructionName)) {
            instruction = Instruction.from(instructionName);
            args = Arrays.copyOfRange(words, 1, words.length);
        }
        else {
            String message = String.format("Line %d: instruction %s is not allowed", lineNum + 1, words[0]);
            return new TranslationResult(false, message);
        }

        // Instruction format check:
        if (instruction.equals(Instruction.IF) && isConditionCommand) {
            String message = String.format("Line %d: instruction format is not valid", lineNum + 1);
            return new TranslationResult(false, message);
        }
        if ((instruction.argsNum != args.length)
            && !instruction.equals(Instruction.IF)) {
            String message = String.format("Line %d: instruction format is not valid", lineNum + 1);
            return new TranslationResult(false, message);
        }

        // Instruction arguments check:
        if (instruction.argsType.equals("word") &&
            !instruction.allowedArgs.contains(args[0].toLowerCase())) {
            String message = String.format("Line %d: argument %s is not allowed", lineNum + 1, args[0]);
            return new TranslationResult(false, message);
        }

        if (instruction.argsType.equals("num")) {
            for (int i = 0; i < instruction.argsNum; i++)
            {
                try {
                    int num = Integer.parseInt(args[i]);
                    if ((num < 1) || (num > codeLines.size())) {
                        String message = String.format("Line %d: argument %d is out of range", lineNum + 1, num);
                        return new TranslationResult(false, message);
                    }
                }
                catch (NumberFormatException e) {
                    String message = String.format("Line %d: argument %s is not allowed", lineNum + 1, args[i]);
                    return new TranslationResult(false, message);
                }
            }
        }

        if (instruction.argsType.equals("cond")) {
            String conditionName = args[0].toLowerCase();
            String conditionArg = args[1].toLowerCase();
            if (!instruction.allowedArgs.contains(conditionName)) {
                String message = String.format("Line %d: condition %s is not allowed", lineNum + 1, args[0]);
                return new TranslationResult(false, message);
            }
            ConditionPattern cond = ConditionPattern.from(conditionName);
            String commandText = String.join(" ", Arrays.copyOfRange(args, 2, args.length));
            TranslationResult result = translate(commandText, lineNum, true, codeLine);
            if (!result.success()) return result;
        }

        Command command = Command.from(instructionName, args, executor);
        codeMap.put(codeLine, command);

        return new TranslationResult(true, "Successfully translated");
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

    public Map<CodeLine, Command> getCodeMap() { return codeMap; }
}
