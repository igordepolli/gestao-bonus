package br.ufes.calculodebonus;

import br.ufes.model.Funcionario;
import java.util.ArrayList;

public class ProcessadoraBonus {

    private final ArrayList<IMetodoCalculaBonus> metodosDeCalculoBonus;

    public ProcessadoraBonus() {
        metodosDeCalculoBonus = new ArrayList<>();

        this.metodosDeCalculoBonus.add(new MetodoCalculaBonusFalta());
        this.metodosDeCalculoBonus.add(new MetodoCalculaBonusDistancia());
        this.metodosDeCalculoBonus.add(new MetodoCalculoBonusCargo());
    }

    public void processar(Funcionario funcionario) {

        for (IMetodoCalculaBonus metodoDeCalculoBonus : metodosDeCalculoBonus) {
            metodoDeCalculoBonus.calcular(funcionario);
        }

    }
}
