/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.strategy;

import br.ufes.model.Employee;
import java.util.List;

/**
 *
 * @author W-E-R
 */
public interface EmployeeStategy {
    
    public void whiteEmployees(List<Employee> funcionarios);
    
}
