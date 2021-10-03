package br.ufes.presenter.command;

import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;

public class KeepEmployeePresenterEditCommand extends KeepEmployeePresenterCommand {

    public KeepEmployeePresenterEditCommand(Employee employee, EmployeeCollection employeeCollection) {
        super(employee, employeeCollection);
    }

    @Override
    public void execute() {
        employeeCollection.updateEmployee(employee);
    }

}
