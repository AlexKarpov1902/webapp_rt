package ru.example.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.dao.SqlTracker;
import ru.example.dao.Store;
import ru.example.model.Person;
import ru.example.servlets.servlet.FindServlet;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

public class Service {

    private static final Logger LOG = LoggerFactory.getLogger(FindServlet.class.getName());

    public static List<Person> getSqlRequest(HttpServletRequest request) {
       // final List<Person> persons;
        final String firstname = request.getParameter("firstname");
        final String lastname = request.getParameter("lastname");
        final String city = request.getParameter("city");
        final String model = request.getParameter("model");
        LOG.info("Incoming in FindServlet POST with fam {}, name {}, auto {}, city {}",
                lastname, firstname, model, city);
        final Store s = new SqlTracker();
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
