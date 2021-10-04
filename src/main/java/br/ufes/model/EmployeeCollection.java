package br.ufes.model;

import java.util.ArrayList;
import java.util.List;

public class EmployeeCollection {

    private static EmployeeCollection instance = null;
    private final List<Employee> employees;

    private EmployeeCollection() {
        employees = new ArrayList<>();
    }

    public static EmployeeCollection getInstance() {
        if (instance == null) {
            instance = new EmployeeCollection();
        }

        return instance;
    }

    public void addEmployee(Employee employee) {
        employees.add(employee);
    }

    public Employee searchEmployeeById(String id){
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        return null;
    }

    public List<Employee> searchEmployeeByName(String name){
        List<Employee> employeesFiltered = new ArrayList<>();

        employees.stream()
            .filter(employee -> (
                employee.getName().toLowerCase()
                    .contains(name.toLowerCase()))).forEachOrdered(employeesFiltered::add);

        return employeesFiltered;
    }

    public void updateEmployee(Employee employee) {
        Employee employeeToDelete = null;
        for (Employee emp : employees) {
            if (emp.getId().equals(employee.getId())) {
                employeeToDelete = emp;
                break;
            }
        }
        if (employeeToDelete != null) {
          removeEmployee(employeeToDelete);
          addEmployee(employee);
        }
    }

    public void removeEmployee(Employee employee) {
        employees.remove(employee);
    }

    public boolean isEmpty() {
        return employees.isEmpty();
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void clearList (){
        employees.clear();
    }
}
