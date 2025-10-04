package game.scene.level.logic.editor.condition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.collision.zone.Zone;
import game.common.logic.entity.Entity;
import game.common.logic.collision.zone.ColoredZone;
import game.scene.level.logic.Level;

public class FacingCondition extends Condition {
    private final String targetObject;

    public FacingCondition(String arg) {
        if (arg.equals("r")) arg = "red";
        if (arg.equals("g")) arg = "green";
        if (arg.equals("b")) arg = "blue";
        this.targetObject = arg;
    }

    @Override
    public boolean check(Entity target, Level level) {
        int targetX = (int) (target.getWorldPos().x + target.getFacingDirection().vector.x);
        int targetY = (int) (target.getWorldPos().y + target.getFacingDirection().vector.y);

        System.out.println(target.getFacingDirection());

        if (targetObject.equals("wall")) {
            return level.getMap().collision[targetY][targetX] == 1;
        }

        for (Zone zone : level.getZones()) {
            if (!(zone instanceof ColoredZone colored)) continue;
            if (!colored.getColorName().equals(targetObject)) continue;
            return (targetX == zone.getX() && targetY == zone.getY());
        }
        return false;
    }

    @Override
    public Image getIcon() {
        String iconPath = "ui/icon/facing_" + targetObject + ".png";
        return new Image(new Texture(Gdx.files.internal(iconPath)));
    }
}
