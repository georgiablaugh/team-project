package use_case.take_quiz;

import java.util.LinkedHashMap;
import java.util.Map;

public class TakeQuizResultOutputData {
    private final String matchedLibraryName;
    private final Map<String, Integer> scoresByLibraryName;

    public TakeQuizResultOutputData(String matchedLibraryName,
                                    Map<String, Integer> scoresByLibraryName) {
        this.matchedLibraryName = matchedLibraryName;
        this.scoresByLibraryName = new LinkedHashMap<>(scoresByLibraryName);
    }

    public String getMatchedLibraryName() {
        return matchedLibraryName;
    }

    public Map<String, Integer> getScoresByLibraryName() {
        return scoresByLibraryName;
    }
}
