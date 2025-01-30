package servlet.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import servlet.model.Joueur;
import servlet.model.JoueurDao;
import servlet.model.Partie;
import servlet.model.PartieDAO;
import utils.DS;

@WebServlet("/")
public class Control extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String action = req.getParameter("act");
        if (action == null)
            action = "null";

        String vuePath = null;

        DS manager = new DS();
        PartieDAO partieDao = new PartieDAO(manager);
        JoueurDao joueurDao = new JoueurDao(manager);

        switch (action) {
            case "voir":
                try {
                    ;
                    int partieId = Integer.parseInt(req.getParameter("partie"));
                    Partie partie = partieDao.findById(partieId);
                    req.setAttribute("partie", partie);

                    Joueur j1 = joueurDao.findById(partie.jno1());
                    req.setAttribute("j1", j1);
                    Joueur j2 = joueurDao.findById(partie.jno2());
                    req.setAttribute("j2", j2);
                    Joueur gagnant = joueurDao.findById(partie.gagnant());
                    req.setAttribute("gagnant", gagnant);

                    vuePath = "WEB-INF/vue/view.jsp";
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }

                break;

            case "modifier":
                vuePath = "WEB-INF/vue/list.jsp";
                break;

            default:
                res.sendError(404, "Unknown action \'" + action + "\'");
                break;

        }
        if (vuePath != null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher(vuePath);
            dispatcher.forward(req, res);
        }
    }
}
