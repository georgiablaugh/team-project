package use_case.take_quiz;

import java.util.List;

/**
 * The answers the quiz taker has given so far, in question order.
 */

public class TakeQuizInputData {
    private final List<Integer> chosenIndices;

    public TakeQuizInputData(List<Integer> chosenIndices) {
        this.chosenIndices = List.copyOf(chosenIndices);
    }

    public List<Integer> getChosenIndices() {
        return chosenIndices;
    }
}

