package br.ufes.calculodebonus;

import br.ufes.model.Bonus;
import br.ufes.model.Employee;

public class CalculateOccupationBonus implements CalculateBonus {

    @Override
    public void calculate(Employee employee) {
        String occupation = employee.getOccupation();
        String occupationString = "Cargo: " + occupation;

        if (occupation.equalsIgnoreCase("GERENTE")) {
            employee.addBonus(new Bonus(occupationString, 100));
        } else if (occupation.toUpperCase().compareTo("SUPERVISOR") == 0) {
            employee.addBonus(new Bonus(occupationString, 80));
        } else if (occupation.toUpperCase().compareTo("PROGRAMADOR") == 0) {
            employee.addBonus(new Bonus(occupationString, 50));
        }
    }

}
