package use_case.take_quiz;

import entity.Library;
import entity.Question;
import entity.Quiz;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TakeQuizInteractor {
    private final TakeQuizDataAccessInterface quizDataAccess;
    private final TakeQuizOutputBoundary presenter;

    public TakeQuizInteractor(TakeQuizDataAccessInterface quizDataAccess,
                              TakeQuizOutputBoundary presenter) {
        this.quizDataAccess = quizDataAccess;
        this.presenter = presenter;
    }
}
