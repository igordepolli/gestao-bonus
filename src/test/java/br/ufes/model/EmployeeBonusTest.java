package br.ufes.model;

import br.ufes.calculodebonus.BonusProcessor;

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
public class EmployeeBonusTest {

  @Test
  public void CT001() throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", 2500.00, "Gerente");
    double expectSalary = 2500.00;

    // Assert
    assertEquals(expectSalary, employee.getBaseSalary(), 0.001);
  }

  @ParameterizedTest
  @ValueSource(doubles = { 998.0, 998.01 })
  public void CT002(double salario) throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", salario, "Gerente");

    // Assert
    assertEquals(salario, employee.getSalary(), 0.001);
  }

  @ParameterizedTest
  @MethodSource
  public void CT003(int faltas, double expectSalary) throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", 2500.00, "Zelador");
    employee.setAttendances(faltas);
    BonusProcessor pb = new BonusProcessor();

    // Act
    pb.process(employee);

    // Assert
    assertEquals(expectSalary, employee.getSalary(), 0.001);
  }

  private static Stream<Arguments> CT003() {
    return Stream.of(Arguments.of(0, 2625), Arguments.of(1, 2550), Arguments.of(4, 2550), Arguments.of(5, 2525),
        Arguments.of(6, 2525), Arguments.of(9, 2525), Arguments.of(10, 2500), Arguments.of(11, 2500));
  }

  @ParameterizedTest
  @MethodSource
  public void CT004(int distancia, double expectSalary) throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", 2500.00, "Zelador");
    employee.setAttendances(10);
    employee.setDistance(distancia);
    BonusProcessor pb = new BonusProcessor();

    // Act
    pb.process(employee);

    // Assert
    assertEquals(expectSalary, employee.getSalary(), 0.001);
  }

  private static Stream<Arguments> CT004() {
    return Stream.of(Arguments.of(49, 2500), Arguments.of(50, 2500), Arguments.of(51, 2650), Arguments.of(99, 2650),
        Arguments.of(100, 2650), Arguments.of(101, 2800), Arguments.of(149, 2800), Arguments.of(150, 2800),
        Arguments.of(151, 3000));
  }

  @ParameterizedTest
  @MethodSource
  public void CT005(String occupation, double expectSalary) throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", 2500.00, occupation);
    employee.setAttendances(10);
    BonusProcessor pb = new BonusProcessor();

    // Act
    pb.process(employee);

    // Assert
    assertEquals(expectSalary, employee.getSalary(), 0.001);
  }

  private static Stream<Arguments> CT005() {
    return Stream.of(Arguments.of("Zelador", 2500), Arguments.of("Gerente", 2600), Arguments.of("Supervisor", 2580),
        Arguments.of("Programador", 2550));
  }

  @ParameterizedTest
  @MethodSource
  public void CT006(String name, String expectMessage) throws Exception {
    // Arrange
    Exception exception = assertThrows(Exception.class, () -> {
      new Employee(name, 2500.00, "Gerente");
    });

    // Assert
    assertEquals(expectMessage, exception.getMessage());
  }

  private static Stream<Arguments> CT006() {
    String expectMessage = "\n#1 Informe um nome válido";
    return Stream.of(Arguments.of("", expectMessage), Arguments.of(null, expectMessage),
        Arguments.of("    ", expectMessage));
  }

  @ParameterizedTest
  @MethodSource
  public void CT007(String occupation, String expectMessage) throws Exception {
    // Arrange
    Exception exception = assertThrows(Exception.class, () -> {
      new Employee("Fulano", 2500.00, occupation);
    });

    // Assert
    assertEquals(expectMessage, exception.getMessage());
  }

  private static Stream<Arguments> CT007() {
    String expectMessage = "\n#2 Informe um cargo válido";
    return Stream.of(Arguments.of("", expectMessage), Arguments.of(null, expectMessage),
        Arguments.of("    ", expectMessage));
  }

  @Test
  public void CT008() throws Exception {
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
  public void CT009() throws Exception {
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
  public void CT010() throws Exception {
    // Arrange
    Exception exception = assertThrows(Exception.class, () -> {
      new Employee("Fulano", 997.99, "Gerente");
    });
    String expectMessage = "\n#3 O salário base deve ser >= R$ 998,00";

    // Assert
    assertEquals(expectMessage, exception.getMessage());
  }
}
