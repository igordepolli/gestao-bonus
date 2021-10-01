package br.ufes.presenter.state;

import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.KeepEmployeePresenter;
import java.awt.Component;
import java.awt.event.ActionListener;
import javax.swing.JButton;

public abstract class KeepEmployeePresenterState {

    protected KeepEmployeePresenter presenter;
    protected EmployeeCollection employeeCollection;

    public KeepEmployeePresenterState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        this.presenter = presenter;
        this.employeeCollection = employeeCollection;
        cleanListeners();
    }

    public void save() {
    }

    public void update() {
    }

    public void delete() {
    }

    private void cleanListeners() {
        for (Component component : presenter.getView().getPanelButtons().getComponents()) {
            if (component instanceof JButton) {
                for (ActionListener actionListener : ((JButton) component).getActionListeners()) {
                    ((JButton) component).removeActionListener(actionListener);
                }
            }
        }
    }
}
