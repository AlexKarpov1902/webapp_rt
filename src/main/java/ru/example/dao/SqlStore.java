package ru.example.dao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.model.Auto;
import ru.example.model.City;
import ru.example.model.Person;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class SqlStore implements Store {

    private static final Logger LOG = LoggerFactory.getLogger(SqlStore.class.getName());
    private Connection cn;

    public SqlStore(Connection connection) {
        this.cn = connection;
    }

    public SqlStore() {
    }

    public void init() {
        try (InputStream in = SqlStore.class.getClassLoader().
                getResourceAsStream("app.properties")) {
            final Properties config = new Properties();
            config.load(in);
            Class.forName(config.getProperty("driver-class-name"));
            cn = DriverManager.getConnection(
                    config.getProperty("url"),
                    config.getProperty("username"),
                    config.getProperty("password")
            );

        } catch (Exception e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void close() throws Exception {
        if (cn != null) {
            cn.close();
            LOG.info("Connection closed");
        }
    }

    @Override
    public List<Person> findAllPersons() {
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT p.id, p.firstname, p.lastname, p.middlename, "
                        + " a.model, a.color, c.name as city "
                        + "FROM person p "
                        + "LEFT JOIN auto a on p.id = a.person_id "
                        + "LEFT JOIN city c on p.city_id = c.id ")) {
            try (ResultSet rs = statement.executeQuery()) {
                persons = getResultFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("SELECT find {} records", persons.size());
        return persons;
    }

    @Override
    public List<Person> findByFirstName(String key) {
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT p.id, p.firstname, p.lastname, p.middlename, "
                        + " a.model, a.color, c.name as city "
                        + "FROM person p "
                        + "LEFT JOIN auto a on p.id = a.person_id "
                        + "LEFT JOIN city c on p.city_id = c.id "
                        + "WHERE UPPER(p.firstname) LIKE UPPER( ? ) ")
        ) {
            statement.setString(1, "%" + key + "%");
            try (ResultSet rs = statement.executeQuery()) {
                persons = getResultFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("SELECT find {} records", persons.size());
        return persons;
    }

    @Override
    public List<Person> findByLastName(String key) {
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "SELECT p.id, p.firstname, p.lastname, p.middlename, "
                        + " a.model, a.color , c.name as city "
                        + "FROM person p "
                        + "LEFT JOIN auto a on p.id = a.person_id "
                        + "LEFT JOIN city c on p.city_id = c.id "
                        + "WHERE UPPER(p.lastname) LIKE UPPER( ? )")
        ) {
            statement.setString(1, "%" + key + "%");
            try (ResultSet rs = statement.executeQuery()) {
                persons = getResultFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("SELECT find {} records", persons.size());
        return persons;
    }

    @Override
    public List<Person> findByAuto(String key) {
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "select p.id, p.firstname, p.lastname, p.middlename, "
                        + " a.model, a.color, c.name as city "
                        + "FROM person p "
                        + "LEFT JOIN auto a on p.id = a.person_id "
                        + "LEFT JOIN city c on p.city_id = c.id "
                        + "WHERE UPPER(a.model) LIKE UPPER( ? )")
        ) {
            statement.setString(1, "%" + key + "%");
            try (ResultSet rs = statement.executeQuery()) {
                persons = getResultFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("SELECT find {} records", persons.size());
        return persons;
    }

    @Override
    public List<Person> findByCity(String key) {
        List<Person> persons = new ArrayList<>();
        try (PreparedStatement statement = cn.prepareStatement(
                "select p.id, p.firstname, p.lastname, p.middlename, "
                        + " a.model, a.color, c.name as city "
                        + "FROM person p "
                        + "LEFT JOIN auto a on p.id = a.person_id "
                        + "LEFT JOIN city c on p.city_id = c.id "
                        + "WHERE UPPER(c.name) LIKE UPPER( ? )")
        ) {
            statement.setString(1, "%" + key + "%");
            try (ResultSet rs = statement.executeQuery()) {
                persons = getResultFromRS(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        LOG.info("SELECT find {} records", persons.size());
        return persons;
    }

    private List<Person> getResultFromRS(ResultSet rs) throws SQLException {
        Map<Long, Person> personsById = new HashMap<>();
        while (rs.next()) {
            Long id = rs.getLong("id");
            String fn = rs.getString("firstname");
            String ln = rs.getString("lastname");
            String mn = rs.getString("middlename");
            String city = rs.getString("city");
            Person person = personsById.get(id);
            if (person == null) {
                person = new Person(fn, ln, mn);
                personsById.put(id, person);
            }
            person.setCity(new City(city));
            person.addAuto(new Auto(
                    rs.getString("model"),
                    rs.getString("color")
            ));
        }
        return new ArrayList<>(personsById.values());
    }
}
