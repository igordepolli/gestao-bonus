/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufes.model;

import br.ufes.exceptions.AppExceptions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

/**
 *
 * @author mayco
 */
class BonusTest {

    @Test
    void testTipo() throws AppExceptions {
        // Arrange
        String tipo = "distancia";
        double valor = 200;
        Bonus bonus = new Bonus(tipo, valor);

        // Assert
        assertEquals(tipo, bonus.getType());
        assertEquals(valor, bonus.getValue());
    }

}
