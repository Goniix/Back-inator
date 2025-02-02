package servlet.controller;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utils.DS;

@WebServlet("/")
public class Control extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null)
            action = "null";

        switch (action) {
        case "voir":
            DS dbManager = DS.getInstance();
            break;

        case "modifier":
            break;

        default:
            res.sendError(404, "Unknown action \'" + action + "\'");
            break;

        }

    }
}
