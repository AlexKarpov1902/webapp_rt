package ru.example.servlets.servlet;

import ru.example.Person;
import ru.example.SqlTracker;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/find/*")
public class FindServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SqlTracker s = new SqlTracker();
        s.init();
        List<Person> persons = s.findAllPersons();
        try {
            s.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        String path = "/WEB-INF/view/list-person.html";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        request.setAttribute("persons", persons);
       // response.  etAttribute("persons", persons);
        requestDispatcher.forward(request, response);
    }
}
