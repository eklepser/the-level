package com.eklepser.thelevel.logic.decoder.condition;

import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.eklepser.thelevel.logic.world.entity.Entity;
import com.eklepser.thelevel.logic.world.collision.Collidable;
import com.eklepser.thelevel.logic.world.level.Level;

import java.lang.reflect.Constructor;
import java.util.List;

public abstract class Condition {
    public abstract Image getIcon();

    public abstract boolean check(Entity target, Level level);

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
