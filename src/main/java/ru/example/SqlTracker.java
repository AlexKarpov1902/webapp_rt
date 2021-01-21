package ru.example;

//import java.io.FileInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.sql.*;
import java.util.*;

public class SqlTracker implements Store {
    private Connection cn;
    private final String tableName = "item";
    private static final Logger LOG = LoggerFactory.getLogger(SqlTracker.class.getName());

    public SqlTracker(Connection connection) {
        this.cn = connection;
    }

    public SqlTracker() {
    }

    public void init() {
        try (InputStream in = SqlTracker.class.getClassLoader().
                getResourceAsStream("app.properties")) {
            Properties config = new Properties();
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
        }
    }

    //    @Override
//    public Item add(Item item)  {
//        try (PreparedStatement statement =
//                     cn.prepareStatement("insert into item (name) values (?)",
//                             Statement.RETURN_GENERATED_KEYS)) {
//       //     statement.setString(1, tableName);
//            statement.setString(1, item.getName());
//            statement.execute();
//            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
//                if (generatedKeys.next()) {
//                    item.setId(generatedKeys.getInt(1));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return item;
//    }
//
//    @Override
//    public boolean replace(String id, Item item) {
//        boolean result = false;
//        try (PreparedStatement statement =
//                     cn.prepareStatement("update item set name = ? where id = ?")) {
//            statement.setString(1, item.getName());
//            statement.setInt(2, Integer.parseInt(id));
//            result = statement.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
//    @Override
//    public boolean delete(String id) {
//        boolean result = false;
//        try (PreparedStatement statement =
//                     cn.prepareStatement("delete from item where id = ?")) {
//         //   statement.setString(1, tableName);
//            statement.setInt(1, Integer.parseInt(id));
//            result = statement.executeUpdate() > 0;
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return result;
//    }
//
    @Override
    public List<Person> findAllPersons() {
        List<Person> persons = null;
        try (PreparedStatement statement = cn.prepareStatement(
                "select p.id, p.firstname, p.lastname, p.middlename, a.name, a.color" +
                        " from person p LEFT JOIN auto a on p.id = a.person_id")) {
            try (ResultSet rs = statement.executeQuery()) {
                Map<Long, Person> personsById = new HashMap<>();
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String fn = rs.getString("firstname");
                    String ln = rs.getString("lastname");
                    String mn = rs.getString("middlename");
                    Person person = personsById.get(id);
                    if (person == null) {
                        person = new Person(fn, ln, mn);
                        personsById.put(id, person);
                    }
                    person.addAuto(new Auto(
                            rs.getString("name"),
                            rs.getString("color")
                    ));
                }
                persons = new ArrayList<>(personsById.values());
            }
        } catch (Exception e) {
              e.printStackTrace();
        }
        return persons;
    }

    @Override
    public List<Person> findByFirstName(String firstName) {
        List<Person> persons = null;
        try (PreparedStatement statement = cn.prepareStatement(
                "select p.id, p.firstname, p.lastname, p.middlename, a.name, a.color " +
                        "FROM person p " +
                        "LEFT JOIN auto a on p.id = a.person_id WHERE p.firstname = ? ")
         )
          {  statement.setString(1, firstName);
            try (ResultSet rs = statement.executeQuery()) {
                Map<Long, Person> personsById = new HashMap<>();
                while (rs.next()) {
                    Long id = rs.getLong("id");
                    String fn = rs.getString("firstname");
                    String ln = rs.getString("lastname");
                    String mn = rs.getString("middlename");
                    Person person = personsById.get(id);
                    if (person == null) {
                        person = new Person(fn, ln, mn);
                        personsById.put(id, person);
                    }
                    person.addAuto(new Auto(
                            rs.getString("name"),
                            rs.getString("color")
                    ));
                }
                persons = new ArrayList<>(personsById.values());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return persons;
    }



//    @Override
//    public List<Item> findByName(String key) {
//        List<Item> items = new ArrayList<>();
//        try (PreparedStatement statement = cn.prepareStatement(
//                "select * from item where name LIKE ?")) {
// //           statement.setString(1, tableName);
//            statement.setString(1, "%" + key + "%");
//            try (ResultSet resultSet = statement.executeQuery()) {
//                while (resultSet.next()) {
//                    items.add(new Item(
//                            resultSet.getInt("id"),
//                            resultSet.getString("name")
//                    ));
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return items;
//    }
//
//    @Override
//    public Item findById(String id) {
//        Item item = null;
//        try (PreparedStatement statement = cn.prepareStatement("select * from item where id = ?")) {
//            statement.setInt(1, Integer.parseInt(id));
//            try (ResultSet resultSet = statement.executeQuery()) {
//                if (resultSet.next()) {
//                    item = new Item(
//                            resultSet.getInt("id"),
//                            resultSet.getString("name")
//                    );
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return item;
//    }

    public static void main(String[] args) throws Exception {
        SqlTracker sqlTracker = new SqlTracker();
        sqlTracker.init();
//        sqlTracker.add(new Item(5, "first"));
//        sqlTracker.add(new Item(5, "second"));
//        sqlTracker.add(new Item(5, "thread"));
//        System.out.println("Вывод всей базы");
//        sqlTracker.findAll().forEach(System.out::println);
//        sqlTracker.delete("3");
//        System.out.println("Вывод после удаления записи 3");
//        sqlTracker.findAll().forEach(System.out::println);
//        System.out.println("вывод id=2 " + sqlTracker.findById("2"));
//        System.out.println("Вывод результатов поиска по ключу");
//        sqlTracker.findByName("irs").forEach(System.out::println);
//        sqlTracker.replace("8", new Item(1, "Ten"));

        sqlTracker.close();
    }
}
