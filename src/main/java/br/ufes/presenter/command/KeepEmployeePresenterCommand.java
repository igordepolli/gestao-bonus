package br.ufes.presenter.command;

import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;

public abstract class KeepEmployeePresenterCommand {

    protected Employee employee;
    protected EmployeeCollection employeeCollection;

    public KeepEmployeePresenterCommand(Employee employee, EmployeeCollection employeeCollection) {
        this.employee = employee;
        this.employeeCollection = employeeCollection;
    }

    public abstract void execute();

}
