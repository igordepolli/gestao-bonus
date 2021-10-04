package br.ufes.presenter;

import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.state.KeepEmployeePresenterIncludeState;
import br.ufes.presenter.state.KeepEmployeePresenterViewState;
import br.ufes.utils.EmployeeTableConstructor;
import br.ufes.view.SearchEmployeeView;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.logging.Logger;

public class SearchEmployeePresenter {

    private static SearchEmployeePresenter instance = null;
    private final SearchEmployeeView view;
    private EmployeeCollection employeeCollection;
    private final DefaultTableModel tableEmployees;    
    private final EmployeeTableConstructor empTable;
    Logger logger = Logger.getLogger(SearchEmployeePresenter.class.getName());

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
        view.getBtnClose().addActionListener((ActionEvent arg0) ->
            view.dispose()
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
            try {
                //Path onde o arquivo será salvo
                File file = new File("C:\\TextExportjTable.txt");

                //Caso o arquivo não exista então cria-se um novo arquivo
                if (file.createNewFile()) {
                    logger.log(Level.INFO, "Arquivo criado");
                }else{
                    logger.log(Level.INFO, "Arquivo já existente. Foi sobrescrito");
                }

                try ( FileWriter fw = new FileWriter(file.getAbsoluteFile());  BufferedWriter bw = new BufferedWriter(fw)) {
                    //Laço que percorre as colunas da jTable recuperando o nome das mesmas
                    for (int i = 0; i < tableEmployees.getColumnCount(); i++) {
                        bw.write(tableEmployees.getColumnName(i) + " ");
                    }

                    //Quebra de linha no arquivo .txt
                    //Windows: \r\n | Linux: \n
                    bw.write("\r\n");

                    //Laço que percorre as linhas da jTable
                    for (int i = 0; i < tableEmployees.getRowCount(); i++) {

                        //Laço que percorre as colunas da jTable recuperando os valores
                        for (int j = 0; j < tableEmployees.getColumnCount(); j++) {
                            bw.write(tableEmployees.getValueAt(i, j) + " ");
                        }

                        //Quebra de linha no arquivo .txt
                        //Windows: \r\n | Linux: \n
                        bw.write("\r\n");
                    }
                }

                JOptionPane.showMessageDialog(view, "Dados exportados com sucesso!");
            } catch (HeadlessException | IOException ex) {
                JOptionPane.showMessageDialog(view, "Erro ao exportar os dados.");
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
        employees.forEach(employee ->
            tableEmployees.addRow(new Object[]{
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
