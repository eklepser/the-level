package game.data.user;

public final class CompletionStatus {
    public String tag;
    public LevelStatus status;

    public CompletionStatus() { }

    public CompletionStatus(String tag, LevelStatus status) {
        this.tag = tag;
        this.status = status;
    }
}
