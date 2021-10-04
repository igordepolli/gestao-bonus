/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import br.ufes.exceptions.AppExceptions;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 *
 * @author W-E-R
 */
public class EmployeeCollectionTest {

    EmployeeCollection collection;

    @BeforeEach
    void prepareTest() {
        collection = EmployeeCollection.getInstance();
        collection.clearList();
    }

    @Test
    void CT001() throws AppExceptions {
        // Arrange
        collection.addEmployee(new Employee().setId("01"));
        Employee emp = collection.searchEmployeeById("01");

        // Assert
        assertEquals("01", emp.getId());
    }

    @Test
    void CT002() throws AppExceptions {
        // Arrange
        collection.addEmployee(new Employee().setId("01"));
        Employee emp = collection.searchEmployeeById("02");

        // Assert
        assertNull(emp);
    }

    @Test
    void CT003() throws AppExceptions {
        // Arrange
        collection.addEmployee(new Employee("Vlad", 11000, "Gerente"));
        collection.addEmployee(new Employee("Vladzao", 11000, "Gerente"));
        collection.addEmployee(new Employee("Maycao", 11000, "Gerente"));
        List<Employee> list = collection.searchEmployeeByName("Vlad");

        // Assert
        assertEquals(2, list.size());
    }

    @Test
    void CT004() throws AppExceptions {
        // Arrange
        Employee emp1 = new Employee("Vlad", 11000, "Gerente");
        Employee emp2 = new Employee("Vlad", 10000, "Gerente");
        emp1.setId("01");
        emp2.setId("01");
        
        collection.addEmployee(emp1);
        collection.updateEmployee(emp2);
        
        double expectSalary = 10000;
        
        // Assert
        assertEquals(expectSalary, collection.searchEmployeeById("01").getBaseSalary());
    }
    
 @Test
    void CT005() throws AppExceptions {
        // Arrange
        collection.addEmployee(new Employee("cascall", 11000, "Gerente"));
        collection.clearList();
        
        // Assert
        assertEquals(true, collection.isEmpty());
    }
    
    
    @Test
    void CT006() throws AppExceptions {
        // Arrange
        collection.addEmployee(new Employee("cascall", 11000, "Gerente"));
        collection.addEmployee(new Employee("cascall", 11000, "Gerente"));      
        
        // Assert
        assertEquals(2, collection.getEmployees().size());
    }
    
}
