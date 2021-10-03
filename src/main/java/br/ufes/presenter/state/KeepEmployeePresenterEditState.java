package br.ufes.presenter.state;

import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.KeepEmployeePresenter;
import br.ufes.presenter.command.KeepEmployeePresenterEditCommand;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

public class KeepEmployeePresenterEditState extends KeepEmployeePresenterState {

    public KeepEmployeePresenterEditState(KeepEmployeePresenter presenter, EmployeeCollection employeeCollection) {
        super(presenter, employeeCollection);

        setView();
        initListeners();

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
            presenter.getTextInFieldsAndSetEmployee();
            presenter.setCommand(new KeepEmployeePresenterEditCommand(presenter.getEmployee(), employeeCollection));
            presenter.getCommand().execute();
            JOptionPane.showMessageDialog(presenter.getView(), "Funcionário atualizado com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);
            presenter.setState(new KeepEmployeePresenterViewState(presenter, employeeCollection));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(presenter.getView(), ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setView() {
        setFields();
        setButtons();
    }

    private void setFields() {
        presenter.getView().getCbxOccupation().setEnabled(true);
        presenter.getView().getTfdName().setEditable(true);
        presenter.getView().getTfdOutro().setEditable(true);
        presenter.getView().getFfdDistance().setEditable(true);
        presenter.getView().getTfdSalary().setEditable(true);
        presenter.getView().getTfdAbsence().setEditable(true);
    }

    private void setButtons() {
        presenter.getView().getBtnSave().setEnabled(true);
        presenter.getView().getBtnEdit().setEnabled(false);
        presenter.getView().getBtnDelete().setEnabled(false);
    }
}
