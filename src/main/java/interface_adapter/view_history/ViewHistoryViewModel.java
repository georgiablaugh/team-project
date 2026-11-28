package interface_adapter.view_history;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.List;

/**
 * Holds what the past results list should show. The view listens to it and redraws on change.
 */
public class ViewHistoryViewModel {
    private final PropertyChangeSupport support = new PropertyChangeSupport(this);

    private List<String> rows = List.of();
    private String error;

    public List<String> getRows() {
        return rows;
    }

    public void setRows(List<String> rows) {
        this.rows = rows;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    /** Tells every listening view that something changed. */
    public void firePropertyChanged() {
        support.firePropertyChange("state", null, this);
    }

    /** The view calls this once so it gets notified. */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }
}
