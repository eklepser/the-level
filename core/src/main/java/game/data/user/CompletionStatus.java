package game.data.user;

public final class CompletionStatus {
    public String levelTag;
    public LevelStatus levelStatus;

    public CompletionStatus() { }

    public CompletionStatus(String levelTag, LevelStatus levelStatus) {
        this.levelTag = levelTag;
        this.levelStatus = levelStatus;
    }
}
