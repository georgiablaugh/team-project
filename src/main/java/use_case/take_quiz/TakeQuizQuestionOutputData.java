package use_case.take_quiz;

import java.util.List;

public class TakeQuizQuestionOutputData {
    private final int questionNumber;
    private final int totalQuestions;
    private final String questionText;
    private final List<String> optionTexts;

    public TakeQuizQuestionOutputData(int questionNumber, int totalQuestions, String questionText,
                                      List<String> optionTexts, List<Integer> chosenSoFar) {
        this.questionNumber = questionNumber;
        this.totalQuestions = totalQuestions;
        this.questionText = questionText;
        this.optionTexts = List.copyOf(optionTexts);
    }

    public int getQuestionNumber() {
        return questionNumber;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public String getQuestionText() {
        return questionText;
    }

    public List<String> getOptionTexts() {
        return optionTexts;
    }
}
