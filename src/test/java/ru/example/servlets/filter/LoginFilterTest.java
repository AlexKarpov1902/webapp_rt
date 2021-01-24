package ru.example.servlets.filter;

import org.junit.Ignore;
import org.junit.Test;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class LoginFilterTest {
    private final static String path = "/WEB-INF/view/index.jsp";

    @Ignore
    @Test
    public void doFilter() throws ServletException, IOException {

            final LoginFilter servlet = new LoginFilter();

            final HttpServletRequest request = mock(HttpServletRequest.class);
            final HttpServletResponse response = mock(HttpServletResponse.class);
            final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

            when(request.getRequestDispatcher(path)).thenReturn(dispatcher); //var... args => OngoingStubbing<T> thenReturn(T value, T... values);

    //        servlet.doGet(request, response);

            verify(request, times(1)).getRequestDispatcher(path);
            verify(request, never()).getSession();
            verify(dispatcher).forward(request, response);
    }


    @Ignore
    @Test
    public void moveToMenuTest() throws ServletException, IOException {

        final LoginFilter servlet = new LoginFilter();

        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);

        when(request.getRequestDispatcher(path)).thenReturn(dispatcher); //var... args => OngoingStubbing<T> thenReturn(T value, T... values);

   //     servlet.doGet(request, response);

        verify(request, times(1)).getRequestDispatcher(path);
        verify(request, never()).getSession();
        verify(dispatcher).forward(request, response);
    }



}
