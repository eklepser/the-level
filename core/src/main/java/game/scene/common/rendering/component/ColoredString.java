package game.scene.common.rendering.component;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.HorizontalGroup;
import com.badlogic.gdx.utils.Align;
import game.resources.Assets;

import java.util.ArrayList;
import java.util.List;

public class ColoredString extends HorizontalGroup {
    private final String text;
    private final BitmapFont font;
    private final List<TextLabel> labels;

    public ColoredString(String text) {
        this.text = text;
        this.font = Assets.getSkin().getFont("default");
        labels = parseText(text);
        labels.forEach(this::addActor);
    }

    public ColoredString() {
        this.text = "";
        this.font = Assets.getSkin().getFont("default");
        labels = new ArrayList<>();
    }

    private List<TextLabel> parseText(String text) {
        if (!text.startsWith("/ ")) text = "/ " + text;
        String[] parts = text.split("/");
        List<TextLabel> labels = new ArrayList<>();
        for (String part : parts) {
            String cmd;
            String[] words = part.split("\\s+");
            if (words.length > 0) cmd = words[0];
            else continue;

            String colorName = "white";
            float scale = 1.0f;
            String[] args = cmd.split("_");
            if (args.length > 0) colorName = args[0];
            if (args.length > 1) scale = Float.parseFloat(args[1]);

            Color color = parseColor(colorName);
            TextLabel textLabel = new TextLabel(part.replaceFirst(cmd + " ", ""));
            textLabel.setColor(color);
            textLabel.setFontScale(scale);
            textLabel.setAlignment(Align.center);
            labels.add(textLabel);
        }
        return labels;
    }

    private Color parseColor(String colorName) {
        return switch (colorName) {
            case "red" -> Color.RED;
            case "green" -> Color.GREEN;
            case "blue" -> Color.BLUE;
            case "black" -> Color.BLACK;
            case "yellow" -> Color.YELLOW;
            case "cyan" -> Color.CYAN;
            case "magenta" -> Color.MAGENTA;
            case "light-gray" -> new Color(0.85f, 0.85f, 0.85f, 1);
            case "gray" -> Color.GRAY;
            case "orange" -> Color.ORANGE;
            case "pink" -> Color.PINK;
            case "brown" -> Color.BROWN;
            default -> Color.WHITE;
        };
    }

    // Getters & setters:
    public String getText() { return text; }

    public void setText(String text) {
        labels.forEach(this::removeActor);
        labels.clear();
        labels.addAll(parseText(text));
        labels.forEach(this::addActor);
    }
}
