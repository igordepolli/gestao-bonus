/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.stategy;

import br.ufes.exceptions.AppExceptions;
import br.ufes.model.Employee;
import br.ufes.model.EmployeeCollection;
import br.ufes.strategy.EmployeeStategyTXT;
import br.ufes.strategy.ManagerStrategy;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author W-E-R
 */
public class StategyTest {

    EmployeeCollection collection;

    @BeforeEach
    void prepareTest() {
        collection = EmployeeCollection.getInstance();
        collection.clearList();
    }

    @Test
    void ExportTest() throws AppExceptions {
        // Arrange
        collection.addEmployee(new Employee("Werikis", 11000, "Gerente"));
        collection.addEmployee(new Employee("Wer", 11000, "Gerente"));

        ManagerStrategy manager = new ManagerStrategy(new EmployeeStategyTXT());
        manager.setLog(new EmployeeStategyTXT());
        manager.getLog().whiteEmployees(collection.getEmployees());

    }
}
