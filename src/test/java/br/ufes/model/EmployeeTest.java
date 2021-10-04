package br.ufes.model;

import br.ufes.exceptions.AppExceptions;
import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/**
 *
 * @author clayton
 */
class EmployeeTest {

    @Test
    void CT001() throws AppExceptions {
        // Arrange
        Employee employee = new Employee("Fulano", 2500.00, "Gerente");
        double expectSalary = 2500.00;

        // Assert
        assertEquals(expectSalary, employee.getBaseSalary(), 0.001);
    }

    @ParameterizedTest
    @ValueSource(doubles = {998.0, 998.01})
    void CT002(double salario) throws AppExceptions {
        // Arrange
        Employee employee = new Employee("Fulano", salario, "Gerente");

        // Assert
        assertEquals(salario, employee.getSalary(), 0.001);
    }

    @ParameterizedTest
    @MethodSource
    void CT003(String name, String expectMessage) throws AppExceptions {
        // Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new Employee(name, 2500.00, "Gerente");
        });

        // Assert
        assertEquals(expectMessage, exception.getMessage());
    }

    private static Stream<Arguments> CT003() {
        String expectMessage = "\n#1 Informe um nome válido";
        return Stream.of(Arguments.of("", expectMessage), Arguments.of(null, expectMessage),
                Arguments.of("    ", expectMessage));
    }

    @ParameterizedTest
    @MethodSource
    void CT004(String occupation, String expectMessage) throws AppExceptions {
        // Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new Employee("Fulano", 2500.00, occupation);
        });

        // Assert
        assertEquals(expectMessage, exception.getMessage());
    }

    private static Stream<Arguments> CT004() {
        String expectMessage = "\n#2 Informe um cargo válido";
        return Stream.of(Arguments.of("", expectMessage), Arguments.of(null, expectMessage),
                Arguments.of("    ", expectMessage));
    }

    @Test
    void CT005() throws AppExceptions {
        // Arrange
        Employee employee = new Employee("Fulano", 2500.00, "Programador");
        String expectMessage = "Distância não pode ser menor que zero!";

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            employee.setDistance(-1);
        });

        // Assert
        assertEquals(expectMessage, exception.getMessage());
    }

    @Test
    void CT006() throws AppExceptions {
        // Arrange
        Employee employee = new Employee("Fulano", 2500.00, "Programador");
        String expectMessage = "Faltas não pode ser menor que zero!";

        // Act
        Exception exception = assertThrows(Exception.class, () -> {
            employee.setAttendances(-1);
        });

        // Assert
        assertEquals(expectMessage, exception.getMessage());
    }

    @Test
    void CT007() throws AppExceptions {
        // Arrange
        Exception exception = assertThrows(Exception.class, () -> {
            new Employee("Fulano", 997.99, "Gerente");
        });
        String expectMessage = "\n#3 O salário base deve ser >= R$ 998,00";

        // Assert
        assertEquals(expectMessage, exception.getMessage());
    }

    @Test
    void CT008() throws AppExceptions {
        //Arrange
        Employee employee = new Employee();
        String id = "aaa1";
        String nome = "Arnaldo";
        int distancia = 122;
        String ocupacao = "Programador";
        double salarioBase = 1000;
        //Act

        employee.setId(id);
        employee.setName(nome);
        employee.setDistance(distancia);
        employee.setOccupation(ocupacao);
        employee.setBaseSalary(salarioBase);

        //Assert
        assertEquals(id, employee.getId());
        assertEquals(nome, employee.getName());
        assertEquals(distancia, employee.getDistance());
        assertEquals(ocupacao, employee.getOccupation());
        assertEquals(salarioBase, employee.getBaseSalary());
    }

    @Test
    void CT009() throws AppExceptions {
        // Arrange
        Exception exception = assertThrows(Exception.class, () -> {
           Employee emp = new Employee("Fulano", 1000, "Gerente");
            emp.setBaseSalary(997.99);
        });
        
        
        String expectMessage = "\n#3 O salário base deve ser >= R$ 998,00";

        // Assert
        assertEquals(expectMessage, exception.getMessage());
    }

}
