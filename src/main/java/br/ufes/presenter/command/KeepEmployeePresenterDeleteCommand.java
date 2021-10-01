package br.ufes.presenter.command;

import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;

public class KeepEmployeePresenterDeleteCommand extends KeepEmployeePresenterCommand {

    public KeepEmployeePresenterDeleteCommand(Employee employee, EmployeeCollection employeeCollection) {
        super(employee, employeeCollection);
    }

    @Override
    public void execute() throws Exception {
        employeeCollection.removeEmployee(employee);
    }

}
