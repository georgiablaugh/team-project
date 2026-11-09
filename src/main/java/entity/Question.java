package entity;

import java.util.List;

/**
 * A quiz question and its answer options.
 */
public class Question {
    private final String text;
    private final List<AnswerOption> options;

    /**
     * @throws IllegalArgumentException if there are fewer than 2 options
     */
    public Question(String text, List<AnswerOption> options) {
        if (options.size() < 2) {
            throw new IllegalArgumentException("A question needs at least 2 options.");
        }
        this.text = text;
        this.options = List.copyOf(options);
    }

    public String getText() {
        return text;
    }

    public List<AnswerOption> getOptions() {
        return options;
    }

    public AnswerOption getOption(int index) {
        return options.get(index);
    }
}
