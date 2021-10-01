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

    public Employee searchEmployeeById(String id) throws Exception {
        for (Employee employee : employees) {
            if (employee.getId().equals(id)) {
                return employee;
            }
        }
        throw new Exception("Funcionário não encontrado!");
    }

    public Employee searchEmployeeByName(String name) throws Exception {
        for (Employee employee : employees) {
            if (employee.getName().toLowerCase().equals(name.toLowerCase())) {
                return employee;
            }
        }
        throw new Exception("Funcionário não encontrado!");
    }

    public void updateEmployee(Employee employee) {
        for (Employee emp : employees) {
            if (emp.getId().equals(employee.getId())) {
                emp = employee;
            }
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

}
