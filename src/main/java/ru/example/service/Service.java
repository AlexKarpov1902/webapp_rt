package ru.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.dao.SqlStore;
import ru.example.dao.Store;
import ru.example.model.Person;
import ru.example.servlets.servlet.FindServlet;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private static final Logger LOG = LoggerFactory.getLogger(FindServlet.class.getName());

    public static List<Person> getSqlRequest(final String firstname,
                                             final String lastname,
                                             final String city,
                                             final String model) {
        LOG.info("Incoming in Service with fam {}, name {}, auto {}, city {}",
                lastname, firstname, model, city);
        final Store s = new SqlStore();
        s.init();
        if (!firstname.isEmpty()) {
            return s.findByFirstName(firstname);
        }
        if (!lastname.isEmpty()) {
            return s.findByLastName(lastname);
        }
        if (!city.isEmpty()) {
            return s.findByCity(city);
        }
        if (!model.isEmpty()) {
            return s.findByAuto(model);
        }
        return new ArrayList<>();
    }
}
