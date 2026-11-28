package view;

import entity.Library;
import interface_adapter.save_result.SaveResultController;
import interface_adapter.save_result.SaveResultViewModel;
import interface_adapter.view_history.ViewHistoryController;
import interface_adapter.view_history.ViewHistoryViewModel;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.Component;
import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;

/**
 * Shown after the quiz: name box, Save and View history buttons, a message, and past results.
 * One panel shared by two use cases (save result and view history).
 */
public class SaveResultView extends JPanel implements PropertyChangeListener {
    private final SaveResultViewModel saveViewModel;
    private final ViewHistoryViewModel historyViewModel;

    private final JTextField nameField = new JTextField(15);
    private final JButton saveButton = new JButton("Save");
    private final JButton historyButton = new JButton("View history");
    private final JLabel messageLabel = new JLabel(" ");
    private final DefaultListModel<String> historyRows = new DefaultListModel<>();
    private final JList<String> historyList = new JList<>(historyRows);

    // Set by take quiz before the user presses Save
    private List<Integer> chosenIndices = List.of();
    private Library matchedLibrary;

    public SaveResultView(SaveResultViewModel saveViewModel, SaveResultController saveController,
                          ViewHistoryViewModel historyViewModel, ViewHistoryController historyController) {
        this.saveViewModel = saveViewModel;
        this.historyViewModel = historyViewModel;
        saveViewModel.addPropertyChangeListener(this);
        historyViewModel.addPropertyChangeListener(this);

        JPanel namePanel = new JPanel();
        namePanel.add(new JLabel("Name:"));
        namePanel.add(nameField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(historyButton);

        // Save button -> save result controller
        saveButton.addActionListener(e ->
                saveController.execute(nameField.getText(), chosenIndices, matchedLibrary));

        // View history button -> view history controller (reuses the typed name)
        historyButton.addActionListener(e -> historyController.execute(nameField.getText()));

        JScrollPane historyScroll = new JScrollPane(historyList);
        historyScroll.setBorder(BorderFactory.createTitledBorder("Past results"));
        historyScroll.setPreferredSize(new Dimension(320, 150));

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        for (Component c : new Component[] {namePanel, buttonPanel, messageLabel, historyScroll}) {
            ((javax.swing.JComponent) c).setAlignmentX(Component.CENTER_ALIGNMENT);
            add(c);
        }
    }

    public void setResult(List<Integer> chosenIndices, Library matchedLibrary) {
        this.chosenIndices = chosenIndices;
        this.matchedLibrary = matchedLibrary;
    }

    // presenter -> firePropertyChanged() -> callback -> this
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (evt.getSource() == saveViewModel) {
            if (saveViewModel.getError() != null) {
                messageLabel.setText(saveViewModel.getError());   // Throw warning
            } else {
                messageLabel.setText(saveViewModel.getMessage()); // Thank you message
            }
        } else if (evt.getSource() == historyViewModel) {
            historyRows.clear();
            if (historyViewModel.getError() != null) {
                messageLabel.setText(historyViewModel.getError()); // Blank name / no results
            } else {
                messageLabel.setText(" ");
                for (String row : historyViewModel.getRows()) {
                    historyRows.addElement(row);
                }
            }
        }
    }
}
