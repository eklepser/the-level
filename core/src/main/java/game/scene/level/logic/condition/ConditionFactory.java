package game.scene.level.logic.condition;

public final class ConditionFactory {
    private ConditionFactory() { }

    public static Condition condition(String conditionName, String arg) {
        if (conditionName.equals(ConditionPattern.FACING.name)) {
            return facingCondition(arg);
        }
        return new FalseCondition();
    }

    private static FacingCondition facingCondition(String arg) {
        if (arg.equals("r")) arg = "red";
        if (arg.equals("g")) arg = "green";
        if (arg.equals("b")) arg = "blue";
        return new FacingCondition(arg);
    }
}
