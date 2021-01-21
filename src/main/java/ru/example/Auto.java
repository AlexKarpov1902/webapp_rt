package ru.example;

import java.util.Objects;

public class Auto {
    String name;
    String color;

    public Auto(String name, String color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return Objects.equals(name, auto.name) && Objects.equals(color, auto.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, color);
    }
}
