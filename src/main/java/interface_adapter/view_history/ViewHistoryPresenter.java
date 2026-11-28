package interface_adapter.view_history;

import use_case.view_history.ViewHistoryOutputBoundary;
import use_case.view_history.ViewHistoryOutputData;

import java.util.List;

public class ViewHistoryPresenter implements ViewHistoryOutputBoundary {
    private final ViewHistoryViewModel viewModel;

    public ViewHistoryPresenter(ViewHistoryViewModel viewModel) {
        this.viewModel = viewModel;
    }

    // Show the past results
    @Override
    public void prepareSuccessView(ViewHistoryOutputData outputData) {
        viewModel.setError(null);
        viewModel.setRows(outputData.getRows());
        viewModel.firePropertyChanged();
    }

    // Blank name or no results
    @Override
    public void prepareFailView(String errorMessage) {
        viewModel.setError(errorMessage);
        viewModel.setRows(List.of());
        viewModel.firePropertyChanged();
    }
}
