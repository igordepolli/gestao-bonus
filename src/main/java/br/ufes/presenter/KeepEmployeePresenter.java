package br.ufes.presenter;

import br.ufes.model.Bonus;
import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;
import br.ufes.presenter.command.KeepEmployeePresenterCommand;
import br.ufes.presenter.state.KeepEmployeePresenterState;
import br.ufes.view.KeepEmployeeView;
import java.math.BigDecimal;
import java.util.UUID;

public class KeepEmployeePresenter {

    private static KeepEmployeePresenter instance = null;
    private final KeepEmployeeView view;
    private KeepEmployeePresenterCommand command;
    private KeepEmployeePresenterState state;
    private Employee employee;
    private final EmployeeCollection employeeCollection;

    private KeepEmployeePresenter(EmployeeCollection employeeCollection) throws Exception {
        this.employee = null;
        this.employeeCollection = employeeCollection;

        view = new KeepEmployeeView();
        view.setLocation(20, 20);

    }

    public static KeepEmployeePresenter getInstance(EmployeeCollection employeeCollection) throws Exception {
        if (instance == null) {
            instance = new KeepEmployeePresenter(employeeCollection);
        }

        return instance;
    }

    public void cleanFields() {
        getView().getCbxOccupation().setSelectedIndex(-1);
        getView().getTfdName().setText("");
        getView().getFfdDistance().setText("");
        getView().getTfdSalary().setText("");
        getView().getTfdAbsence().setText("");
    }

    private void checkFieldsIsEmpty() throws Exception {
        if (fieldsIsEmpty()) {
            throw new Exception("TODOS os campos devem ser preenchidos!");
        }
    }

    private int getDistanceOfTextField() throws Exception {
        int distance = Integer.parseInt(view.getFfdDistance().getText());

        if (distance < 0) {
            throw new Exception("Não é possível cadastrar uma distância menor que 0!");
        }

        return distance;
    }

    private int getAttendanceOfTextField() throws Exception {
        int numberOfAbsence = Integer.parseInt(view.getTfdAbsence().getText());

        if (numberOfAbsence < 0) {
            throw new Exception("Número de faltas deve ser maior ou igual a zero!");
        }

        return numberOfAbsence;
    }

    public void createNewEmployee() throws Exception {
        /*if (employee != null) {
            throw new Exception("Não é possível criar um novo usuário!");
        }*/

        checkFieldsIsEmpty();

        employee = new Employee();

        employee.setId(generateRandomId());
        setEmplyee();
    }

    public void getTextInFieldsAndSetEmployee() throws Exception {
        checkFieldsIsEmpty();
        setEmplyee();
    }

    private void setEmplyee() throws Exception {
        employee.setOccupation(String.valueOf(view.getCbxOccupation().getSelectedItem()));
        employee.setName(view.getTfdName().getText());
        employee.setDistance(getDistanceOfTextField());
        employee.setAttendances(getAttendanceOfTextField());
        employee.setBaseSalary(getAndConvertSalaryField());
    }
    
    private boolean fieldsIsEmpty() {
        return view.getTfdName().getText().equals("")
                || view.getTfdSalary().getText().equals("")
                || view.getTfdAbsence().getText().equals("")
                || view.getFfdDistance().getText().equals("")
                || view.getCbxOccupation().getSelectedIndex() == -1;
    }

    private String generateRandomId() {
        return UUID.randomUUID().toString();
    }

    private double getAndConvertSalaryField() throws Exception {
        BigDecimal salaryValueBigDecimal = view.getTfdSalary().getValue();
        double salary = salaryValueBigDecimal.doubleValue();

        if (salary < 0) {
            throw new Exception("Não é possível inserir um salário negativo!");
        }

        return salary;
    }

    private Bonus getInstanceOfBonus() {
        return null;
    }

    public void loadFields() throws Exception {

        view.getCbxOccupation().setSelectedItem(employee.getOccupation());
        view.getTfdName().setText(employee.getName());
        view.getFfdDistance().setText(String.valueOf(employee.getDistance()));
        view.getTfdSalary().setValue(new BigDecimal(employee.getSalary()));
        view.getTfdAbsence().setText(String.valueOf(employee.getAttendances()));

    }

    public KeepEmployeeView getView() {
        return view;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public KeepEmployeePresenterCommand getCommand() {
        return command;
    }

    public void setCommand(KeepEmployeePresenterCommand command) {
        this.command = command;
    }

    public KeepEmployeePresenterState getState() {
        return state;
    }

    public void setState(KeepEmployeePresenterState state) {
        this.state = state;
    }

}
