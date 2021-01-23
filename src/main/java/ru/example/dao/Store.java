package ru.example.dao;

import ru.example.model.Person;

import java.util.List;

public interface Store extends AutoCloseable {

    void init();

    List<Person> findAllPersons();

    List<Person> findByFirstName(String key);

    List<Person> findByLastName(String key);

    List<Person> findByAuto(String key);

    List<Person> findByCity(String key);
}