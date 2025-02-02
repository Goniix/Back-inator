package fr.goniix.apinator.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Produces;

import java.io.IOException;


public class ConnectionApi extends HttpServlet {
    @POST
    @Produces("text/plain")
    public String hello() {
        return "Hello, World!";
    }
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
    }
}
