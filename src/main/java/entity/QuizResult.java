package entity;

import java.time.LocalDate;
import java.util.List;

/**
 * One completed quiz: who took it, when, what they answered, and which library they matched.
 * This is what gets saved, shown in history, and compared when finding matches.
 */
public class QuizResult {
    private final String userName;
    private final LocalDate dateTaken;
    private final List<Integer> chosenIndices;
    private final Library matchedLibrary;

    public QuizResult(String userName, LocalDate dateTaken, List<Integer> chosenIndices,
                      Library matchedLibrary) {
        this.userName = userName;
        this.dateTaken = dateTaken;
        this.chosenIndices = List.copyOf(chosenIndices);
        this.matchedLibrary = matchedLibrary;
    }

    public String getUserName() {
        return userName;
    }

    public LocalDate getDateTaken() {
        return dateTaken;
    }

    public List<Integer> getChosenIndices() {
        return chosenIndices;
    }

    public Library getMatchedLibrary() {
        return matchedLibrary;
    }

    /** Returns how many questions both people answered the same way (higher = more similar). */
    public int similarityTo(QuizResult other) {
        int same = 0;
        int n = Math.min(chosenIndices.size(), other.chosenIndices.size());
        for (int i = 0; i < n; i++) {
            if (chosenIndices.get(i).equals(other.chosenIndices.get(i))) {
                same++;
            }
        }
        return same;
    }
}
