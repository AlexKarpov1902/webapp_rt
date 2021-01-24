package ru.example.service;
import org.junit.Test;
import ru.example.dao.SqlStore;
import ru.example.model.Person;


import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlStoreTest {
    public Connection init() {
        try (InputStream in = SqlStore.class.getClassLoader().getResourceAsStream("app.properties")) {
            Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            return DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );
        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Test
    public void findAllPersonsTest() throws Exception {
        try (SqlStore tracker = new SqlStore(this.init())) {
            List<Person> list = tracker.findAllPersons();
            list.forEach(System.out::println);
            assertThat(list.size(), is(7));   //
        }
    }

    @Test
    public void findByFirstNameTest() throws Exception {
        try (SqlStore tracker = new SqlStore(this.init())) {
            List<Person> list = tracker.findByFirstName("паВел");
            list.forEach(System.out::println);
            assertThat(list.size(), is(1));   //
        }
    }

    @Test
    public void findByLastNameTest() throws Exception {
        try (SqlStore tracker = new SqlStore(this.init())) {
            List<Person> list = tracker.findByLastName("еЛов");
            list.forEach(System.out::println);
            assertThat(list.size(), is(1));
        }
    }


    @Test
    public void findByAutoTest() throws Exception {
        try (SqlStore tracker = new SqlStore(this.init())) {
            List<Person> list = tracker.findByAuto("aDa");
            list.forEach(System.out::println);
            System.out.println(list.size());
            assertThat(list.size(), is(1));   //
        }
    }

    @Test
    public void findByCityTest() throws Exception {
        try (SqlStore tracker = new SqlStore(this.init())) {
            List<Person> list = tracker.findByCity("иРов");
            list.forEach(System.out::println);
            System.out.println(list.size());
            assertThat(list.size(), is(1));   //
        }
    }


    @Test
    public void findEmpty() throws Exception {
        try (SqlStore tracker = new SqlStore(this.init())) {
            List<Person> list = tracker.findByAuto("xcvb");
            list.forEach(System.out::println);
            System.out.println(list.size());
            assertThat(list.size(), is(0));   //
        }
    }
}