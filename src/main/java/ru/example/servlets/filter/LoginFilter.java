package ru.example.servlets.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.example.dao.UserDAO;
import ru.proekt.model.User;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicReference;

import static java.util.Objects.nonNull;

/**
 * Фильтр запросов
 */
public class LoginFilter implements Filter {

    private static final Logger LOG = LoggerFactory.getLogger(LoginFilter.class.getName());

    @Override
    public void init(FilterConfig filterConfig)  {
    }

    @Override
    public void doFilter(final ServletRequest request,
                         final ServletResponse response,
                         final FilterChain filterChain)
            throws IOException, ServletException {

        final HttpServletRequest req = (HttpServletRequest) request;
        final HttpServletResponse res = (HttpServletResponse) response;
        final String login = req.getParameter("login");
        final String password = req.getParameter("password");
        final String memo = req.getParameter("memo");
        @SuppressWarnings("unchecked")
        final AtomicReference<UserDAO> dao = (AtomicReference<UserDAO>)
                req.getServletContext().getAttribute("dao");
        final HttpSession session = req.getSession();
        LOG.info("Servlet LoginFilter with login {}", login);
        //Если сессия продолжается
        if ((nonNull(session) && nonNull(memo))
                && nonNull(session.getAttribute("login"))
                && nonNull(session.getAttribute("password"))) {
            final User.ROLE role = (User.ROLE) session.getAttribute("role");
            moveToMenu(req, res, role);
        } else if (dao.get().userIsExist(login, password)) {
            final User.ROLE role = dao.get().getRoleByLoginPassword(login, password);
            req.getSession().setAttribute("password", password);
            req.getSession().setAttribute("login", login);
            req.getSession().setAttribute("role", role);
            moveToMenu(req, res, role);
        } else {
            moveToMenu(req, res, User.ROLE.UNKNOWN);
        }
    }

    /**
     * Move user to menu.
     */
    private void moveToMenu(final HttpServletRequest req,
                            final HttpServletResponse res,
                            final User.ROLE role)
            throws ServletException, IOException {

        LOG.info("Run moveToMenu role - {} method - {}", role, req.getMethod());
        if (role.equals(User.ROLE.ADMIN) || (role.equals(User.ROLE.USER))) {
            req.getRequestDispatcher("/WEB-INF/view/list-person.jsp").forward(req, res);
        } else {
            req.getRequestDispatcher("/WEB-INF/view/login.jsp").forward(req, res);
        }
    }

    @Override
    public void destroy() {
    }

}
