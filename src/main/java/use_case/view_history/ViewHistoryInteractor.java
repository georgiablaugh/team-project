package use_case.view_history;

import entity.QuizResult;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;

public class ViewHistoryInteractor implements ViewHistoryInputBoundary {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("MMM d, yyyy", Locale.ENGLISH);

    private final ViewHistoryOutputBoundary presenter;
    private final ViewHistoryDataAccessInterface dao;

    public ViewHistoryInteractor(ViewHistoryOutputBoundary presenter, ViewHistoryDataAccessInterface dao) {
        this.presenter = presenter;
        this.dao = dao;
    }

    @Override
    public void execute(ViewHistoryInputData input) {
        // 1. username entered -> View history button -> controller -> interactor
        String username = input.getUsername();
        if (username == null || username.isBlank()) {
            presenter.prepareFailView("Please enter a name.");
            return;
        }

        // 2. dao.findByUserName(username) -> List<QuizResult>
        List<QuizResult> results = new ArrayList<>(dao.findByUserName(username.trim()));    // ArrayList for sort()

        // 2.1 presenter -> No past results found
        if (results.isEmpty()) {
            presenter.prepareFailView("No past results for " + username.trim() + ".");

        // 2.2 newest first -> plain text rows -> presenter
        } else {
            // sort and reformat results -> rows
            results.sort(Comparator.comparing(QuizResult::getDateTaken).reversed());
            List<String> rows = new ArrayList<>();
            for (QuizResult result : results) {
                rows.add(result.getDateTaken().format(DATE_FORMAT) + " \u00B7 "
                        + result.getMatchedLibrary().getDisplayName());
            }
            presenter.prepareSuccessView(new ViewHistoryOutputData(rows));
        }
    }
}
