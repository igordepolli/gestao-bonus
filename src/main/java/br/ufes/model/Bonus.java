package br.ufes.model;

public class Bonus {

    private final String type;
    private final double value;

    public Bonus(String type, double value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public double getValue() {
        return value;
    }
}
