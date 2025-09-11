package com.eklepser.thelevel.util;

import com.eklepser.thelevel.graphics.ui.editor.CodeTable;

import java.util.List;

public class CodeTemplates {
    public static final List<String> CODE_EXAMPLE_1 = List.of(";Code example #1",
        "MOVE UP ;go up!",
        ";boo ;boo ;boo",
        "MOVE RIGHT",
        "",
        "MOVE RIGHT",
        "MOVE DOWN",
        ";yoo",
        "MOVE LEFT",
        "MOVE UP",
        "",
        "GOTO 5"
    );

    public static final List<String> MOVE_FORWARD_CYCLE = List.of(";Move forward cycle",
        "MOVE F",
        "MOVE F",
        "MOVE F",
        "ROT R",
        "GOTO 2"
    );

    public static void setTemplate(CodeTable codeTable, List<String> template) {
        for (int i = 0; i < template.size(); i++) {
            if (i < codeTable.getCodeLines().size()) {
                codeTable.getCodeLines().get(i).setText(template.get(i));
            }
        }
        for (int i = template.size(); i < codeTable.getCodeLines().size(); i++) {
            codeTable.getCodeLines().get(i).setText("");
        }
    }
}
