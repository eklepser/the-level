package game.data.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class UserProgressData {
    public List<CompletionStatus> completionList;

    private Map<String, LevelStatus> statusMap;

    public void setStatus(String levelTag, LevelStatus levelStatus) {
        for (CompletionStatus entry : completionList) {
            if (entry.tag.equals(levelTag)) {
                if (entry.status.priority < levelStatus.priority) {
                    entry.status = levelStatus;
                }
                return;
            }
        }
        completionList.add(new CompletionStatus(levelTag, levelStatus));
    }

    public Map<String, LevelStatus> getStatusMap() {
        if (statusMap == null) {
            statusMap = new HashMap<>();
            for (CompletionStatus entry : completionList) {
                if (entry.tag != null && entry.status != null) {
                    statusMap.put(entry.tag, entry.status);
                }
            }
        }
        return statusMap;
    }
}
