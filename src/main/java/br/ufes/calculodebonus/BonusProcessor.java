package br.ufes.calculodebonus;

import br.ufes.model.Employee;
import java.util.ArrayList;

public class BonusProcessor {

    private final ArrayList<CalculateBonus> calculateBonusMethods;

    public BonusProcessor() {
        calculateBonusMethods = new ArrayList<>();

        this.calculateBonusMethods.add(new CalculateAttendanceBonus());
        this.calculateBonusMethods.add(new CalculateDistanceBonus());
        this.calculateBonusMethods.add(new CalculateOccupationBonus());
    }

    public void process(Employee employee) {

        for (CalculateBonus calculateBonusMethod : calculateBonusMethods) {
            calculateBonusMethod.calculate(employee);
        }

    }
}
