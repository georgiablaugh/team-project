package entity;

import java.util.EnumMap;
import java.util.List;
import java.util.Map;

/**
 * A quiz: an ordered list of questions, plus the rule for scoring a set of answers.
 */
public class Quiz {
    private final String title;
    private final List<Question> questions;

    public Quiz(String title, List<Question> questions) {
        this.title = title;
        this.questions = List.copyOf(questions);
    }

    public String getTitle() {
        return title;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    /** Used for the progress bar. */
    public int getQuestionCount() {
        return questions.size();
    }

    /**
     * Adds up each library's points from the chosen answers.
     *
     * @param chosenIndices the index of the chosen option for each question, in order
     * @throws IllegalArgumentException if not every question was answered
     */
    public Map<Library, Integer> score(List<Integer> chosenIndices) {
        if (chosenIndices.size() != questions.size()) {
            throw new IllegalArgumentException("Every question must be answered.");
        }
        Map<Library, Integer> scores = new EnumMap<>(Library.class);
        for (Library library : Library.values()) {
            scores.put(library, 0);
        }
        for (int i = 0; i < questions.size(); i++) {
            AnswerOption chosen = questions.get(i).getOption(chosenIndices.get(i));
            for (Library library : Library.values()) {
                scores.put(library, scores.get(library) + chosen.getWeight(library));
            }
        }
        return scores;
    }

    /** Returns the library with the highest score (ties go to the one listed first in Library). */
    public static Library bestLibrary(Map<Library, Integer> scores) {
        Library best = Library.values()[0];
        for (Library library : Library.values()) {
            if (scores.getOrDefault(library, 0) > scores.getOrDefault(best, 0)) {
                best = library;
            }
        }
        return best;
    }
}
