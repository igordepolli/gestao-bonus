package br.ufes.presenter;

import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.state.KeepEmployeePresenterIncludeState;
import br.ufes.view.MainScreenView;
import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class MainScreenPresenter {

    private static MainScreenPresenter instance = null;
    private final MainScreenView view;
    private final EmployeeCollection employeeCollection;
    private KeepEmployeePresenter keepEmployeePresenter;
    private SearchEmployeePresenter searchEmployeePresenter;
    private CalculateSalaryPresenter calculateSalaryPresenter;

    private MainScreenPresenter() {
        view = new MainScreenView();
        view.setVisible(true);
        employeeCollection = EmployeeCollection.getInstance();

        initListeners();
        setNumberOfEmployees();
    }

    public static MainScreenPresenter getInstance() {
        if (instance == null) {
            instance = new MainScreenPresenter();
        }
        return instance;
    }

    private void initListeners() {
        try {
            view.getMniKeepEmployee().addActionListener((ActionEvent e) -> {
                try {
                    keepEmployeePresenter = KeepEmployeePresenter.getInstance();
                    keepEmployeePresenter.setState(new KeepEmployeePresenterIncludeState(keepEmployeePresenter, employeeCollection));
                    view.add(keepEmployeePresenter.getView());
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            view.getMniSearchEmployee().addActionListener((ActionEvent arg0) -> {
                try {
                    searchEmployeePresenter = SearchEmployeePresenter.getInstance(employeeCollection);
                    view.add(searchEmployeePresenter.getView());
                    searchEmployeePresenter.getView().setVisible(true);
                } catch (Exception ex) {
                    Logger.getLogger(MainScreenPresenter.class.getName()).log(Level.SEVERE, null, ex);
                }
            });

            view.getMniCalculateWages().addActionListener((ActionEvent arg0) -> {
                calculateSalaryPresenter = CalculateSalaryPresenter.getInstance(employeeCollection);
                calculateSalaryPresenter.getView().setVisible(true);
                view.add(calculateSalaryPresenter.getView());
            });

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void setNumberOfEmployees() {
        view.getLblAmountEmployees().setText(String.valueOf(employeeCollection.getEmployees().size()));
    }

    public MainScreenView getView() {
        return view;
    }

}
