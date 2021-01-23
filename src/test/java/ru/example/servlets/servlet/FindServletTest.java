package ru.example.servlets.servlet;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static org.mockito.Mockito.*;
import static org.junit.Assert.*;

public class FindServletTest {

    private final static String path = "/WEB-INF/view/find-person.jsp";
    private final static String path1 = "/WEB-INF/view/list-person.jsp";
    private static final Logger LOG = LoggerFactory.getLogger(FindServlet.class.getName());

    @Test
    public void whenCallDoGetThenServletReturnIndexPage() throws ServletException, IOException {

        final FindServlet servlet = new FindServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher); //var... args => OngoingStubbing<T> thenReturn(T value, T... values);
        servlet.doGet(request, response);
        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

 //   @Ignore
    @Test
    public void whenCallDoPostThenServletReturnIndexPage() throws ServletException, IOException {

        final FindServlet servlet = new FindServlet();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path1)).thenReturn(dispatcher);
        servlet.doPost(request, response);
        verify(request, times(1)).getRequestDispatcher(path1);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }

}