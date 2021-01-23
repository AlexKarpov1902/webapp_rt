package ru.example;
import org.junit.Test;
import ru.example.model.Person;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.List;
import java.util.Properties;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class SqlTrackerTest {
    public Connection init() {
        try (InputStream in = SqlTracker.class.getClassLoader().getResourceAsStream("app.properties")) {
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
        try (SqlTracker tracker = new SqlTracker(this.init())) {
            List<Person> list = tracker.findAllPersons();
            list.forEach(System.out::println);
            assertThat(list.size(), is(7));   //
        }
    }

    @Test
    public void findByFirstNameTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(this.init())) {
            List<Person> list = tracker.findByFirstName("я4");
            list.forEach(System.out::println);
            assertThat(list.size(), is(1));   //
        }
    }

    @Test
    public void findByLastNameTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(this.init())) {
            List<Person> list = tracker.findByLastName("лия4");
            list.forEach(System.out::println);
            assertThat(list.size(), is(1));
        }
    }


    @Test
    public void findByAutoTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(this.init())) {
            List<Person> list = tracker.findByAuto("ope");
            list.forEach(System.out::println);
            System.out.println(list.size());
            assertThat(list.size(), is(1));   //
        }
    }

    @Test
    public void findByCityTest() throws Exception {
        try (SqlTracker tracker = new SqlTracker(this.init())) {
            List<Person> list = tracker.findByCity("иров");
            list.forEach(System.out::println);
            System.out.println(list.size());
            assertThat(list.size(), is(1));   //
        }
    }


    @Test
    public void findEmpty() throws Exception {
        try (SqlTracker tracker = new SqlTracker(this.init())) {
            List<Person> list = tracker.findByAuto("xcvb");
            list.forEach(System.out::println);
            System.out.println(list.size());
            assertThat(list.size(), is(0));   //
        }
    }
}