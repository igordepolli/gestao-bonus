package br.ufes.presenter;

import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.state.KeepEmployeePresenterIncludeState;
import br.ufes.view.MainScreenView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class MainScreenPresenter {

    private static MainScreenPresenter instance = null;
    private final MainScreenView view;
    private final EmployeeCollection employeeCollection;
    private KeepEmployeePresenter keepEmployeePresenter;
    private SearchEmployeePresenter searchEmployeePresenter;
    private CalculateSalaryPresenter calculateSalaryPresenter;

    private MainScreenPresenter() throws Exception {
        view = new MainScreenView();
        view.setExtendedState(JFrame.MAXIMIZED_BOTH);
        view.setVisible(true);
        employeeCollection = EmployeeCollection.getInstance();

        initPresenters();
        initListeners();
        setNumberOfEmployees();
    }

    public static MainScreenPresenter getInstance() throws Exception {
        if (instance == null) {
            instance = new MainScreenPresenter();
        }
        return instance;
    }

    private void initPresenters() throws Exception {
        keepEmployeePresenter = KeepEmployeePresenter.getInstance(employeeCollection);
        searchEmployeePresenter = SearchEmployeePresenter.getInstance(employeeCollection);
        calculateSalaryPresenter = CalculateSalaryPresenter.getInstance(employeeCollection);

        view.add(keepEmployeePresenter.getView());
        view.add(searchEmployeePresenter.getView());
        view.add(calculateSalaryPresenter.getView());

    }

    private void initListeners() throws Exception {
        try {
            view.getMniKeepEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    keepEmployeePresenter.setState(new KeepEmployeePresenterIncludeState(keepEmployeePresenter, employeeCollection));
                }
            });

            view.getMniSearchEmployee().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    searchEmployeePresenter.getView().setVisible(true);
                }
            });

            view.getMniCalculateWages().addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent arg0) {
                    calculateSalaryPresenter.getView().setVisible(true);
                }
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
