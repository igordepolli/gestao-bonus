/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.utils;

import br.ufes.view.IEmployeeTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author vladimir
 */
public class EmployeeTableConstructor {

    private final DefaultTableModel table;
    
    public EmployeeTableConstructor(String[] colunas) {
                table = new DefaultTableModel(
                new Object[][][]{},
                colunas
                        
                        
        );
    }

    public DefaultTableModel getTable() {
        return table;
    }
    
    public void constructTable(IEmployeeTable view) {
        view.getTblEmployees().setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        table.setNumRows(0);
        view.getTblEmployees().setModel(table);
    }
    
    public void clearTable(DefaultTableModel table) {
        if(table == null) return;
        
        if (table.getRowCount() > 0) {
            for (int i = table.getRowCount() - 1; i > -1; i--) {
                table.removeRow(i);
            }
        }
    }    
}
