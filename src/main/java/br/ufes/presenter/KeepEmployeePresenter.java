package br.ufes.presenter;

import br.ufes.model.Employee;
import br.ufes.presenter.command.KeepEmployeePresenterCommand;
import br.ufes.presenter.state.KeepEmployeePresenterState;
import br.ufes.view.KeepEmployeeView;
import br.ufes.exceptions.AppExceptions;
import java.math.BigDecimal;
import java.util.UUID;

public class KeepEmployeePresenter {

    private static KeepEmployeePresenter instance = null;
    private final KeepEmployeeView view;
    private KeepEmployeePresenterCommand command;
    private KeepEmployeePresenterState state;
    private Employee employee;

    private KeepEmployeePresenter() {
        this.employee = null;

        view = new KeepEmployeeView();
        view.setLocation(20, 20);

    }

    public static KeepEmployeePresenter getInstance() {
        if (instance == null) {
            instance = new KeepEmployeePresenter();
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

    private void checkFieldsIsEmpty() throws AppExceptions {
        if (fieldsIsEmpty()) {
            throw new AppExceptions("TODOS os campos devem ser preenchidos!");
        }
    }

    private int getDistanceOfTextField() throws AppExceptions {
        int distance = Integer.parseInt(view.getFfdDistance().getText().replace(",", ""));

        if (distance < 0) {
            throw new AppExceptions("Não é possível cadastrar uma distância menor que 0!");
        }

        return distance;
    }

    private int getAttendanceOfTextField() throws AppExceptions {
        int numberOfAbsence = Integer.parseInt(view.getTfdAbsence().getText());

        if (numberOfAbsence < 0) {
            throw new AppExceptions("Número de faltas deve ser maior ou igual a zero!");
        }

        return numberOfAbsence;
    }

    public void createNewEmployee() throws AppExceptions {
        checkFieldsIsEmpty();

        employee = new Employee().setId(generateRandomId());
        setEmployee();
    }

    public void getTextInFieldsAndSetEmployee() throws AppExceptions {
        checkFieldsIsEmpty();
        setEmployee();
    }

    private void setEmployee() throws AppExceptions {

        String occupation = String.valueOf(view.getCbxOccupation().getSelectedItem());

        if(occupation.equalsIgnoreCase("outro"))
            occupation = view.getTfdOutro().getText();

        employee.setOccupation(occupation);
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

    private double getAndConvertSalaryField() throws AppExceptions {
        BigDecimal salaryValueBigDecimal = view.getTfdSalary().getValue();
        double salary = salaryValueBigDecimal.doubleValue();

        if (salary < 0) {
            throw new AppExceptions("Não é possível inserir um salário negativo!");
        }

        return salary;
    }

    public void loadFields() {
        String occupation = employee.getOccupation();

        if(!occupation.equals("Gerente") && !occupation.equals("Supervisor") && !occupation.equals("Programador")) {
            occupation = "Outro";
        }


        view.getCbxOccupation().setSelectedItem(occupation);
        view.getTfdName().setText(employee.getName());
        view.getFfdDistance().setText(String.valueOf(employee.getDistance()));
        view.getTfdSalary().setValue(new BigDecimal(String.valueOf(employee.getSalary())));
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
