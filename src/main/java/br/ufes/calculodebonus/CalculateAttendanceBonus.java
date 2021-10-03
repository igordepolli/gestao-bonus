package br.ufes.calculodebonus;

import br.ufes.model.Bonus;
import br.ufes.model.Employee;

public class CalculateAttendanceBonus implements CalculateBonus {

    @Override
    public void calculate(Employee employee) {
        int attendances = employee.getAttendances();
        double salary = employee.getBaseSalary();
        String abstentionTitle = "Falta";

        if (attendances == 0) {
            employee.addBonus(new Bonus(abstentionTitle, salary * 0.05));
        } else if (attendances < 5) {
            employee.addBonus(new Bonus(abstentionTitle, salary * 0.02));
        } else if (attendances < 10) {
            employee.addBonus(new Bonus(abstentionTitle, salary * 0.01));
        }
    }

}
