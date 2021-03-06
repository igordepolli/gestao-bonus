package br.ufes.presenter;

import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.state.KeepEmployeePresenterIncludeState;
import br.ufes.presenter.state.KeepEmployeePresenterViewState;
import br.ufes.strategy.EmployeeStategyTXT;
import br.ufes.strategy.ManagerStrategy;
import br.ufes.utils.EmployeeTableConstructor;
import br.ufes.view.SearchEmployeeView;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class SearchEmployeePresenter {

    private static SearchEmployeePresenter instance = null;
    private final SearchEmployeeView view;
    private EmployeeCollection employeeCollection;
    private final DefaultTableModel tableEmployees;
    private final EmployeeTableConstructor empTable;

    private SearchEmployeePresenter(EmployeeCollection employeeCollection) {
        this.employeeCollection = employeeCollection;

        view = new SearchEmployeeView();
        view.setLocation(20, 350);

        empTable = new EmployeeTableConstructor(new String[]{"ID", "Nome", "Função", "Salário base (R$)"});
        tableEmployees = empTable.getTable();
        empTable.constructTable(view);

        loadEmployees(employeeCollection.getEmployees());
        initListeners();
    }

    public static SearchEmployeePresenter getInstance(EmployeeCollection employeeCollection) {
        if (instance == null) {
            instance = new SearchEmployeePresenter(employeeCollection);
        }

        instance.employeeCollection = employeeCollection;
        return instance;
    }

    private void initListeners() {
        view.getBtnClose().addActionListener((ActionEvent arg0)
                -> view.dispose()
        );

        view.getBtnSearch().addActionListener((ActionEvent arg0) -> {
            try {
                defineTableBehavior(view.getTfdName().getText());
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getBtnViewEmployee().addActionListener((ActionEvent arg0) -> {
            try {
                Employee emp = getEmployeeSelected();
                KeepEmployeePresenter keepEmployeePresenter = KeepEmployeePresenter.getInstance();
                keepEmployeePresenter.setEmployee(emp);
                keepEmployeePresenter.setState(new KeepEmployeePresenterViewState(keepEmployeePresenter, employeeCollection));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getBtnNewEmployee().addActionListener((ActionEvent arg0) -> {
            try {
                KeepEmployeePresenter keepEmployeePresenter = KeepEmployeePresenter.getInstance();
                keepEmployeePresenter.setState(new KeepEmployeePresenterIncludeState(keepEmployeePresenter, employeeCollection));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(view, ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            }
        });

        view.getTblEmployees().addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (view.getTblEmployees().getSelectedRow() > -1) {
                    changeView();
                }
            }
        });

        view.getBtnExportar().addActionListener((ActionEvent arg0) -> {

            List<Employee> employees;
            employees = employeeCollection.getEmployees();
            if (!employees.isEmpty()) {
                ManagerStrategy manager = new ManagerStrategy(new EmployeeStategyTXT());
                manager.getLog().whiteEmployees(employees);
                JOptionPane.showMessageDialog(view, "Dados exportados com sucesso!");
            } else {
                JOptionPane.showMessageDialog(view, "Não há funcionarios para Exportação!");
            }

            

        });
    }

    private Employee getEmployeeSelected() {
        return employeeCollection.searchEmployeeById(getIdOfEmployeeSelected());
    }

    private String getIdOfEmployeeSelected() {
        int rowIndex = view.getTblEmployees().getSelectedRow();

        return (String) view.getTblEmployees().getValueAt(rowIndex, 0);
    }

    private boolean checkIfElementWasSelected() {
        return view.getTblEmployees().getSelectedRow() >= 0;
    }

    private void changeView() {
        view.getBtnViewEmployee().setEnabled(checkIfElementWasSelected());
    }

    private void defineTableBehavior(String textInNameTextField) {
        List<Employee> employees;

        if (textInNameTextField.isEmpty() || textInNameTextField.isBlank()) {
            employees = employeeCollection.getEmployees();
        } else {
            employees = searchEmployee(textInNameTextField);
        }

        loadEmployees(employees);

    }

    private List<Employee> searchEmployee(String name) {
        return employeeCollection.searchEmployeeByName(name);
    }

    private void loadEmployees(List<Employee> employees) {
        empTable.clearTable(this.tableEmployees);
        employees.forEach(employee
                -> tableEmployees.addRow(new Object[]{
            employee.getId(),
            employee.getName(),
            employee.getOccupation(),
            employee.getBaseSalary()
        })
        );
    }

    public SearchEmployeeView getView() {
        return view;
    }
}
