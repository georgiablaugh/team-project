package use_case.take_quiz;

public interface TakeQuizOutputBoundary {
    void prepareQuestionView(TakeQuizQuestionOutputData outputData);

    void prepareResultView(TakeQuizResultOutputData outputData);

    void prepareFailView(String errorMessage);
}
