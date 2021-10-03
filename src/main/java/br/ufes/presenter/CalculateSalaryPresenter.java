package br.ufes.presenter;

import br.ufes.calculodebonus.BonusProcessor;
import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;
import br.ufes.view.CalculateSalaryView;
import br.ufes.exceptions.AppExceptions;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

public class CalculateSalaryPresenter {

    private static CalculateSalaryPresenter instance = null;
    private final CalculateSalaryView view;
    private DefaultTableModel tableEmployees;
    private EmployeeCollection employeeCollection;

    private CalculateSalaryPresenter(EmployeeCollection employeeCollection) {
        this.employeeCollection = employeeCollection;

        view = new CalculateSalaryView();
        view.setLocation(800, 20);

        constructTableModel();
        loadEmployees(employeeCollection.getEmployees());
        initListeners();
    }

    public static CalculateSalaryPresenter getInstance(EmployeeCollection employeeCollection) {
        if (instance == null) {
            instance = new CalculateSalaryPresenter(employeeCollection);
        }
        instance.employeeCollection = employeeCollection;
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
                try {
                    defineTableBehavior(view.getTfdSearch().getText());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        view.getBtnListAll().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                try {
                    loadEmployees(employeeCollection.getEmployees());
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
                    loadEmployees(employeeCollection.getEmployees());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void defineTableBehavior(String textInNameTextField) throws AppExceptions {
        clearTable();
        List<Employee> searchEmployee = searchEmployee(textInNameTextField);
        loadEmployees(searchEmployee);
    }

    private List<Employee> searchEmployee(String name) {
        return employeeCollection.searchEmployeeByName(name);
    }

    private void loadEmployees(List<Employee> employees) {
        clearTable();

        employees.forEach(employee ->
            tableEmployees.addRow(new Object[]{
                employee.getName(),
                employee.getBaseSalary(),
                employee.calculateTotalBonus(),
                employee.getSalary()
            })
        );
    }

    private void constructTableModel() {
        tableEmployees = new DefaultTableModel(
                new Object[][][]{},
                new String[]{"Funcionário", "Salário Base (R$)", "Bônus Recebido (R$)", "Salário (R$)"}
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

    private void calculateAllBonus() {
        employeeCollection.getEmployees().stream().map(emp -> {
            emp.resetListBonus();
            return emp;
        }).forEachOrdered(emp -> {
            BonusProcessor calculateAllBonusByEmployee = new BonusProcessor();
            calculateAllBonusByEmployee.process(emp);
        });
    }

    private void checkIfHasEmployeesInCollection() throws AppExceptions {
        if (employeeCollection.isEmpty()) {
            throw new AppExceptions("Não há funcionários para calcular o salário!");
        }
    }

    public CalculateSalaryView getView() {
        return view;
    }

}
