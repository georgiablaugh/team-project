package interface_adapter.view_history;

import use_case.view_history.ViewHistoryInputBoundary;
import use_case.view_history.ViewHistoryInputData;


public class ViewHistoryController {
    private final ViewHistoryInputBoundary interactor;

    public ViewHistoryController(ViewHistoryInputBoundary interactor) {
        this.interactor = interactor;
    }

    public void execute(String username) {
        interactor.execute(new ViewHistoryInputData(username));
    }
}
