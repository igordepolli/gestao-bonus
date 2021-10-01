package br.ufes.calculodebonus;

import br.ufes.model.Bonus;
import br.ufes.model.Employee;

public class CalculateDistanceBonus implements CalculateBonus {

    @Override
    public void calculate(Employee employee) {
        int distance = employee.getDistance();

        String tipoBonus = "Distancia: " + distance + " Km";

        if (distance > 150) {
            employee.addBonus(new Bonus(tipoBonus, 500));
        } else if (distance > 100) {
            employee.addBonus(new Bonus(tipoBonus, 300));
        } else if (distance > 50) {
            employee.addBonus(new Bonus(tipoBonus, 150));
        }
    }
    
}
