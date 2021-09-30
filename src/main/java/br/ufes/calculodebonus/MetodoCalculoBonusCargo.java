package br.ufes.calculodebonus;

import br.ufes.model.Bonus;
import br.ufes.model.Funcionario;

public class MetodoCalculoBonusCargo implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        String cargo = funcionario.getCargo();

        if (cargo.toUpperCase().equals("GERENTE")) {
            funcionario.addBonus(new Bonus("Cargo:" + cargo, 100));
        } else if (cargo.toUpperCase().compareTo("SUPERVISOR") == 0) {
            funcionario.addBonus(new Bonus("Cargo:" + cargo, 80));
        } else if (cargo.toUpperCase().compareTo("PROGRAMADOR") == 0) {
            funcionario.addBonus(new Bonus("Cargo:" + cargo, 50));
        }
    }

}
