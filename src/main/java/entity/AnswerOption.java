package entity;

import java.util.EnumMap;
import java.util.Map;

/**
 * One choice for a question, e.g. "Robarts Starbucks", and how many points it gives each library.
 */
public class AnswerOption {
    private final String text;
    private final Map<Library, Integer> weights;

    public AnswerOption(String text, Map<Library, Integer> weights) {
        this.text = text;
        this.weights = new EnumMap<>(Library.class);
        this.weights.putAll(weights);
    }

    public String getText() {
        return text;
    }

    /** Returns the points this option gives the library (0 if none). */
    public int getWeight(Library library) {
        return weights.getOrDefault(library, 0);
    }
}
