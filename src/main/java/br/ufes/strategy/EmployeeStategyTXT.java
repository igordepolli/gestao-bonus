/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.strategy;

import br.ufes.model.Employee;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author W-E-R
 */
public class EmployeeStategyTXT implements EmployeeStategy {

    @Override
    public void whiteEmployees(List<Employee> employees) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("ExportTXT.txt", true))) {
            bw.write("Funcionário(s): [");
            bw.newLine();

            for (Employee emp : employees) {
                bw.newLine();
                bw.write("ID: " + emp.getId());
                bw.newLine();
                bw.write("Nome: " + emp.getName());
                bw.newLine();
                bw.write("Cargo: " + emp.getOccupation());
                bw.newLine();
                bw.newLine();
            }
            bw.write("]");
            bw.newLine();
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
}
