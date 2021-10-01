package br.ufes.presenter;

import br.ufes.calculodebonus.ProcessBonus;
import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;
import br.ufes.view.CalculateSalaryView;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CalculateSalaryPresenter {

    private static CalculateSalaryPresenter instance = null;
    private final CalculateSalaryView view;
    private DefaultTableModel tableEmployees;
    private final EmployeeCollection employeeCollection;

    private CalculateSalaryPresenter(EmployeeCollection employeeCollection) {
        this.employeeCollection = employeeCollection;

        view = new CalculateSalaryView();
        view.setLocation(800, 20);

        constructTableModel();
        initListeners();
    }

    public static CalculateSalaryPresenter getInstance(EmployeeCollection employeeCollection) {
        if (instance == null) {
            instance = new CalculateSalaryPresenter(employeeCollection);
        }
        return instance;
    }

    private void initListeners() {
        view.getBtnClose().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                view.dispose();
            }
        });

        view.getBtnSearch().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                
            }
        });

        view.getBtnListAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    loadBonus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getBtnCalculate().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    checkIfHasEmployeesInCollection();
                    calculateAllBonus();
                    loadBonus();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void constructTableModel() {
        tableEmployees = new DefaultTableModel(
                new Object[][][][]{},
                new String[]{"Funcionário", "Data", "Salário Base (R$)", "Descrição do Bônus", "Bônus (R$)", "Salário (R$)"}
        );

        view.getTblEmployees().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableEmployees.setNumRows(0);
        view.getTblEmployees().setModel(tableEmployees);
    }

    private void clearTable() {
        if (tableEmployees.getRowCount() > 0) {
            for (int i = tableEmployees.getRowCount() - 1; i > -1; i--) {
                tableEmployees.removeRow(i);
            }
        }
    }

    private void calculateAllBonus() throws Exception {
        for (Employee emp : employeeCollection.getEmployees()) {
            ProcessBonus calculateAllBonusByEmployee = new ProcessBonus();
            calculateAllBonusByEmployee.process(emp);
            emp.getSalary();
        }
    }

    private void checkIfHasEmployeesInCollection() throws Exception {
        if (employeeCollection.isEmpty()) {
            throw new Exception("Não há funcionários para calcular o salário!");
        }
    }

    private void loadBonus() throws Exception {
        clearTable();
    }

    public CalculateSalaryView getView() {
        return view;
    }

}
