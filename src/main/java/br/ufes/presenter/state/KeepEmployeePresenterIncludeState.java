package br.ufes.presenter.state;

import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.KeepEmployeePresenter;
import br.ufes.presenter.command.KeepEmployeePresenterIncludeCommand;
import java.awt.event.ActionEvent;
import javax.swing.JOptionPane;

public class KeepEmployeePresenterIncludeState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterIncludeState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        super(presenter, employeeCollection);

        setView();
        initListeners();

        presenter.setEmployee(null);
        presenter.getView().setVisible(true);
    }

    private void initListeners() {
        presenter.getView().getBtnSave().addActionListener((ActionEvent arg0) -> {
            save();
        });

        presenter.getView().getBtnClose().addActionListener((ActionEvent arg0) -> {
            presenter.getView().dispose();
        });
    }

    @Override
    public void save() {
        try {
            presenter.createNewEmployee();
            presenter.setCommand(new KeepEmployeePresenterIncludeCommand(presenter.getEmployee(), employeeCollection));
            presenter.getCommand().execute();
            JOptionPane.showMessageDialog(presenter.getView(), "Funcionário inserido com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            presenter.setEmployee(null);
            presenter.cleanFields();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setView() {
        presenter.cleanFields();
        setFields();
        setButtons();
    }

    private void setFields() {
        presenter.getView().getCbxOccupation().setEnabled(true);
        presenter.getView().getTfdName().setEditable(true);
        presenter.getView().getFfdAge().setEditable(true);
        presenter.getView().getTfdSalary().setEditable(true);
        presenter.getView().getTfdAbsence().setEditable(true);
    }

    private void setButtons() {
        presenter.getView().getBtnSave().setEnabled(true);
        presenter.getView().getBtnEdit().setEnabled(false);
        presenter.getView().getBtnDelete().setEnabled(false);
    }
}
