package use_case.view_history;

import entity.QuizResult;

import java.util.List;

public interface ViewHistoryDataAccessInterface {
    public List<QuizResult> findByUserName(String userName);
}