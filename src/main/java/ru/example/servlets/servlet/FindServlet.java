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

        LOG.info("Run FindServlet");
        request.setCharacterEncoding("UTF8");
        request.setAttribute("persons", Service.getSqlRequest(
                request.getParameter("firstname"),
                request.getParameter("lastname"),
                request.getParameter("city"),
                request.getParameter("model"))
        );
        request.getRequestDispatcher("/WEB-INF/view/list-person.jsp").forward(request, response);
    }
}
