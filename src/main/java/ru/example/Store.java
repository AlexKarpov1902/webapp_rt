package ru.example;

import java.sql.SQLException;
import java.util.List;

public interface Store extends AutoCloseable {

    void init();

 //   Item add(Item item) throws SQLException;

//    boolean replace(String id, Item item);

//    boolean delete(String id);

   List<Person> findAllPersons();

   List<Person> findByFirstName(String firstName);

 //   Item findById(String id);



}