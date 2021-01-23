package ru.example.model;

import java.util.Objects;

public class Auto {
    private String model;
    private String color;

    public Auto(String model, String color) {
        this.model = model;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String name) {
        this.model = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return "Auto{"
                + "model='" + model + '\''
                + ", color='" + color + '\''
                + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Auto auto = (Auto) o;
        return Objects.equals(model, auto.model) && Objects.equals(color, auto.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(model, color);
    }
}
