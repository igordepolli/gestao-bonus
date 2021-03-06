package br.ufes.presenter.command;

import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;

public class KeepEmployeePresenterIncludeCommand extends KeepEmployeePresenterCommand {

    public KeepEmployeePresenterIncludeCommand(Employee employee, EmployeeCollection employeeCollection) {
        super(employee, employeeCollection);
    }

    @Override
    public void execute() {
        employeeCollection.addEmployee(employee);
    }

}
