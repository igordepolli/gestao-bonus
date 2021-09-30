package br.ufes.calculodebonus;

import br.ufes.model.Bonus;
import br.ufes.model.Funcionario;

class MetodoCalculaBonusFalta implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        int faltas = funcionario.getFaltas();
        double salario = funcionario.getSalarioBase();

        if (faltas == 0) {
            funcionario.addBonus(new Bonus("Falta", salario * 0.05));
        } else if (faltas < 5) {
            funcionario.addBonus(new Bonus("Falta", salario * 0.02));
        } else if (faltas < 10) {
            funcionario.addBonus(new Bonus("Falta", salario * 0.01));
        }
    }

}
