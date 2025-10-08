package game.common.rendering;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.Direction;
import game.scene.level.logic.command.*;
import game.scene.level.logic.condition.Condition;
import game.scene.level.logic.condition.FacingCondition;

public final class ConditionIconFactory {
    private ConditionIconFactory() { }

    public static Image conditionIcon(Condition condition) {
        if (condition instanceof FacingCondition facingCondition) {
            return facingIcon(facingCondition);
        }
        return null;
    }

    private static Image facingIcon(FacingCondition condition) {
        String iconPath = "ui/icon/facing_" + condition.getTargetObject() + ".png";
        return new Image(new Texture(Gdx.files.internal(iconPath)));
    }
}
