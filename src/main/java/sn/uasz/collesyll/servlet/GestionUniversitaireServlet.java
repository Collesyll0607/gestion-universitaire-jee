package sn.uasz.collesyll.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import sn.uasz.collesyll.model.NoteEvaluation;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Couche Présentation / Contrôleur Jakarta EE :
 * Gestion des étudiants, cours et notes avec suivi par session HTTP.
 */
@WebServlet(name = "GestionUniversitaireServlet", urlPatterns = {"/notes", "/api/notes"})
public class GestionUniversitaireServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(true);
        @SuppressWarnings("unchecked")
        List<NoteEvaluation> evaluations = (List<NoteEvaluation>) session.getAttribute("evaluations");

        if (evaluations == null) {
            evaluations = new ArrayList<>();
            evaluations.add(new NoteEvaluation("2023-UASZ-001", "INF361", 16.5, 17.0, 6));
            evaluations.add(new NoteEvaluation("2023-UASZ-001", "INF362", 15.0, 16.0, 6));
            evaluations.add(new NoteEvaluation("2023-UASZ-001", "INF363", 17.0, 18.0, 6));
            session.setAttribute("evaluations", evaluations);
        }

        resp.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = resp.getWriter()) {
            StringBuilder json = new StringBuilder("[");
            for (int i = 0; i < evaluations.size(); i++) {
                NoteEvaluation ev = evaluations.get(i);
                json.append(String.format(
                        "{\"matricule\":\"%s\",\"codeUe\":\"%s\",\"moyenne\":%.2f,\"validee\":%b,\"mention\":\"%s\"}",
                        ev.getMatriculeEtudiant(),
                        ev.getCodeUe(),
                        ev.calculerMoyenneFinale(),
                        ev.estValidee(),
                        ev.determinerMention()
                ));
                if (i < evaluations.size() - 1) {
                    json.append(",");
                }
            }
            json.append("]");
            out.print(json);
        }
    }
}
