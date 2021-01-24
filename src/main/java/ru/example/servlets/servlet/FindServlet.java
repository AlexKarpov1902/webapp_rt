package ru.example.servlets.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.service.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/findServlet")
public class FindServlet extends HttpServlet {

    private static final Logger LOG = LoggerFactory.getLogger(FindServlet.class.getName());

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF8");
        request.setAttribute("persons", Service.getSqlRequest(request));
        request.getRequestDispatcher("/WEB-INF/view/list-person.jsp").forward(request, response);
    }

    /**
     * Возвращает информацию из базы данных по запросу
     * @param request запрос с параметрами
     * @return список
     */
   /* private List<Person> getSqlRequest(HttpServletRequest request) {
        final List<Person> persons;
        final String firstname = request.getParameter("firstname");
        final String lastname = request.getParameter("lastname");
        final String city = request.getParameter("city");
        final String model = request.getParameter("model");
        LOG.info("Incoming in FindServlet POST with fam {}, name {}, auto {}, city {}",
                lastname, firstname, model, city);
        final SqlTracker s = new SqlTracker();
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
    }*/
}
