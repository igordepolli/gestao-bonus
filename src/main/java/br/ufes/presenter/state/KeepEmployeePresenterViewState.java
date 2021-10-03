package br.ufes.presenter.state;

import br.ufes.exceptions.AppExceptions;
import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.KeepEmployeePresenter;
import br.ufes.presenter.command.KeepEmployeePresenterDeleteCommand;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class KeepEmployeePresenterViewState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterViewState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) throws AppExceptions {
        super(presenter, employeeCollection);

        setView();
        presenter.loadFields();
        initListeners();

        presenter.getView().setVisible(true);
    }

    private void initListeners() {
        presenter.getView().getBtnClose().addActionListener((ActionEvent arg0) -> {
            presenter.getView().dispose();
        });

        presenter.getView().getBtnEdit().addActionListener((ActionEvent arg0) -> {
            update();
        });

        presenter.getView().getBtnDelete().addActionListener((ActionEvent arg0) -> {
            delete();
        });
    }

    @Override
    public void update() {
        presenter.setState(new KeepEmployeePresenterEditState(presenter, employeeCollection));
    }

    @Override
    public void delete() {
        try {
            if (confirmDeleteEmployee()) {
                presenter.setCommand(new KeepEmployeePresenterDeleteCommand(presenter.getEmployee(), employeeCollection));
                presenter.getCommand().execute();
                JOptionPane.showMessageDialog(presenter.getView(), "Funcionário deletado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
                presenter.setState(new KeepEmployeePresenterIncludeState(presenter, employeeCollection));
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean confirmDeleteEmployee() {
        int result = JOptionPane.showConfirmDialog(presenter.getView(), "Deseja mesmo remover o funcionário " + presenter.getEmployee().getName() + "?");

        return result == JOptionPane.YES_OPTION;
    }

    private void setView() {
        presenter.cleanFields();
        setFields();
        setButtons();
    }

    private void setFields() {
        presenter.getView().getCbxOccupation().setEnabled(false);
        presenter.getView().getTfdName().setEditable(false);
        presenter.getView().getTfdOutro().setEditable(false);
        presenter.getView().getFfdAge().setEditable(false);
        presenter.getView().getTfdSalary().setEditable(false);
        presenter.getView().getTfdAbsence().setEditable(false);
    }

    private void setButtons() {
        presenter.getView().getBtnSave().setEnabled(false);
        presenter.getView().getBtnEdit().setEnabled(true);
        presenter.getView().getBtnDelete().setEnabled(true);
    }
}
