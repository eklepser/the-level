package game.data.user;

public enum LevelStatus {
    LOCKED(0),
    UNLOCKED(1),
    COMPLETED(2);

    public final int priority;

    LevelStatus(int priority) {
        this.priority = priority;
    }
}
