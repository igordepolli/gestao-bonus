package br.ufes.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import br.ufes.exceptions.AppExceptions;
public class Employee {

  private String id;
  private String name;
  private double baseSalary;
  private int distance;
  private int attendances;
  private String occupation;
  private List<Bonus> receivedBonus;

  public Employee() {
    this.receivedBonus = new ArrayList<>();
  }

  public Employee(String name, double baseSalary, String occupation) throws AppExceptions {
    String exceptions = "";

    if (name == null || name.trim().equals("")) {
      exceptions = exceptions.concat("\n#1 Informe um nome válido");
    }

    if (occupation == null || occupation.trim().equals("")) {
      exceptions = exceptions.concat("\n#2 Informe um cargo válido");
    }
    if (baseSalary < 998.0) {
      exceptions = exceptions.concat("\n#3 O salário base deve ser >= R$ 998,00");
    }

    if (exceptions.length() > 0) {
      throw new AppExceptions(exceptions);
    }
    this.name = name;
    this.occupation = occupation;
    this.baseSalary = baseSalary;
    this.receivedBonus = new ArrayList<>();
  }

  public String getId() {
    return id;
  }

  public Employee setId(String id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public double getBaseSalary() {
    return this.baseSalary;
  }

  public void setBaseSalary(double baseSalary) throws AppExceptions {
    if (baseSalary < 998.0)
      throw new AppExceptions("\n#3 O salário base deve ser >= R$ 998,00");
    this.baseSalary = baseSalary;
  }

  public int getAttendances() {
    return attendances;
  }

  public void setAttendances(int attendances) throws AppExceptions {
    if (attendances < 0) {
      throw new AppExceptions("Faltas não pode ser menor que zero!");
    }

    this.attendances = attendances;
  }

  public int getDistance() {
    return distance;
  }

  public void setDistance(int distance) throws AppExceptions {
    if (distance < 0) {
      throw new AppExceptions("Distância não pode ser menor que zero!");
    }

    this.distance = distance;
  }

  public String getOccupation() {
    return occupation;
  }

  public void setOccupation(String occupation) {
    this.occupation = occupation;
  }

  public double getSalary() {
    return this.calculateSalary();
  }

  public List<Bonus> getReceivedBonus() {
    return Collections.unmodifiableList(receivedBonus);
  }

  private double calculateSalary() {
    return this.baseSalary + this.calculateTotalBonus();
  }

  public void addBonus(Bonus bonus) {
    this.receivedBonus.add(bonus);
  }

  public void resetListBonus() {
    this.receivedBonus = new ArrayList<>();
  }

  public double calculateTotalBonus() {
    double totalBonus = 0;
    for (Bonus bonus : receivedBonus) {
      totalBonus += bonus.getValue();
    }

    return totalBonus;
  }
}
