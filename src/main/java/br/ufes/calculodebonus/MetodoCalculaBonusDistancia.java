package br.ufes.calculodebonus;

import br.ufes.model.Bonus;
import br.ufes.model.Funcionario;

class MetodoCalculaBonusDistancia implements IMetodoCalculaBonus {

    @Override
    public void calcular(Funcionario funcionario) {
        int distanciaMoradia = funcionario.getDistanciaMoradia();

        String tipoBonus = "Distancia: " + distanciaMoradia + " Km";

        if (distanciaMoradia > 150) {
            funcionario.addBonus(new Bonus(tipoBonus, 500));
        } else if (distanciaMoradia > 100) {
            funcionario.addBonus(new Bonus(tipoBonus, 300));
        } else if (distanciaMoradia > 50) {
            funcionario.addBonus(new Bonus(tipoBonus, 150));
        }
    }

}
