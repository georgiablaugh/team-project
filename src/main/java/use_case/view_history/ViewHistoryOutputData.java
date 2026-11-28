package use_case.view_history;

import java.util.List;

public class ViewHistoryOutputData {
    private final List<String> rows;

    public ViewHistoryOutputData(List<String> rows) {
        this.rows = List.copyOf(rows);      // frozen copy of rows
    }

    public List<String> getRows() {
        return this.rows;
    }
}
