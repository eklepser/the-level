package game.scene.level.logic.condition;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import game.common.logic.collision.CollisionContext;
import game.common.logic.entity.Entity;

import java.lang.reflect.Constructor;

public abstract class Condition {
    public abstract boolean check(Entity target, CollisionContext collisionContext);

    public static Condition from(String conditionName, String arg) {
        ConditionPattern pattern = ConditionPattern.from(conditionName);
        Class<? extends Condition> conditionClass = pattern.conditionClass;
        try {
            Constructor<? extends Condition> constructor = conditionClass.getConstructor(String.class);
            return constructor.newInstance(arg);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException("No suitable constructor found for condition: " + conditionName, e);
        }
        catch (Exception e) {
            throw new RuntimeException("Failed to instantiate condition: " + conditionName, e);
        }
    }
}
