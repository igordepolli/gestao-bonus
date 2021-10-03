package br.ufes.calculodebonus;

import br.ufes.model.Employee;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

public class BonusProcessorTest {
  
    @ParameterizedTest
  @MethodSource
  public void CT001(int faltas, double expectSalary) throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", 2500.00, "Zelador");
    employee.setAttendances(faltas);
    BonusProcessor pb = new BonusProcessor();

    // Act
    pb.process(employee);

    // Assert
    assertEquals(expectSalary, employee.getSalary(), 0.001);
  }
  
    private static Stream<Arguments> CT001() {
    return Stream.of(Arguments.of(0, 2625), Arguments.of(1, 2550), Arguments.of(4, 2550), Arguments.of(5, 2525),
        Arguments.of(6, 2525), Arguments.of(9, 2525), Arguments.of(10, 2500), Arguments.of(11, 2500));
  }
  
  
  @ParameterizedTest
  @MethodSource
  public void CT002(int distancia, double expectSalary) throws Exception {
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

  private static Stream<Arguments> CT002() {
    return Stream.of(Arguments.of(49, 2500), Arguments.of(50, 2500), Arguments.of(51, 2650), Arguments.of(99, 2650),
        Arguments.of(100, 2650), Arguments.of(101, 2800), Arguments.of(149, 2800), Arguments.of(150, 2800),
        Arguments.of(151, 3000));
  }
  
  
   @ParameterizedTest
  @MethodSource
  public void CT003(String occupation, double expectSalary) throws Exception {
    // Arrange
    Employee employee = new Employee("Fulano", 2500.00, occupation);
    employee.setAttendances(10);
    BonusProcessor pb = new BonusProcessor();

    // Act
    pb.process(employee);

    // Assert
    assertEquals(expectSalary, employee.getSalary(), 0.001);
  }

  private static Stream<Arguments> CT003() {
    return Stream.of(Arguments.of("Zelador", 2500), Arguments.of("Gerente", 2600), Arguments.of("Supervisor", 2580),
        Arguments.of("Programador", 2550));
  }
}
