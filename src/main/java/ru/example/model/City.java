package ru.example.model;

import java.util.Objects;

public class City {
    private String name;
    private Integer population;

    public City() {
    }

    public City(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return Objects.equals(name, city.name) && Objects.equals(population, city.population);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, population);
    }

    @Override
    public String toString() {
        return "City{"
                + "name='" + name + '\''
                + ", population=" + population
                + '}';
    }
}
