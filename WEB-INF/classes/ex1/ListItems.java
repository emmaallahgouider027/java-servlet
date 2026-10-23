package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// http://localhost:8080/LAB1/ListItems
//   POST (formulaire items.html) : ajoute l'item dans la session, puis redirige vers l'affichage
//   GET                          : affiche la liste et le nombre de saisies de chaque item
//   GET ?clear=1                 : vide la liste
@WebServlet("/ListItems")
public class ListItems extends HttpServlet {

    private static final String ATTR = "items";

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        String newItem = request.getParameter("newItem");
        newItem = (newItem == null) ? "" : newItem.trim();

        HttpSession session = request.getSession(true);

        if (!newItem.isEmpty()) {
            // Plusieurs requêtes de la même session peuvent arriver en même temps
            synchronized (session) {
                ArrayList<String> items = getItems(session);
                items.add(newItem);
                session.setAttribute(ATTR, items);
            }
        }

        // Redirection : recharger la page n'envoie pas l'item une deuxième fois
        response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if ("1".equals(request.getParameter("clear"))) {
            session.removeAttribute(ATTR);
            response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
            return;
        }

        List<String> snapshot;
        synchronized (session) {
            snapshot = new ArrayList<>(getItems(session));
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>List Items</title></head><body>");
        out.println("<h1>Your items</h1>");

        if (snapshot.isEmpty()) {
            out.println("<p>The list is empty.</p>");
        } else {
            // Contenu de l'ArrayList, dans l'ordre de saisie
            out.println("<h2>List (" + snapshot.size() + ")</h2>");
            out.println("<ol>");
            for (String item : snapshot) {
                out.println("<li>" + escape(item) + "</li>");
            }
            out.println("</ol>");

            // Extra : nombre de fois que chaque item a été saisi
            out.println("<h2>Number of times each item was entered</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Item</th><th>Times</th></tr>");
            for (Map.Entry<String, Integer> e : countItems(snapshot).entrySet()) {
                out.println("<tr><td>" + escape(e.getKey()) + "</td><td>" + e.getValue() + "</td></tr>");
            }
            out.println("</table>");
        }

        out.println("<p><a href='items.html'>Add another item</a>"
                + " | <a href='ListItems?clear=1'>Clear the list</a></p>");
        out.println("</body></html>");
    }

    // Récupère la liste de la session (la crée si elle n'existe pas encore)
    @SuppressWarnings("unchecked")
    private static ArrayList<String> getItems(HttpSession session) {
        Object o = session.getAttribute(ATTR);
        if (o instanceof ArrayList) {
            return (ArrayList<String>) o;
        }
        ArrayList<String> items = new ArrayList<>();
        session.setAttribute(ATTR, items);
        return items;
    }

    // Compte les occurrences exactes (« Pizza » et « pizza » sont deux items différents),
    // dans l'ordre de première saisie
    static Map<String, Integer> countItems(List<String> items) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        for (String item : items) {
            counts.merge(item, 1, Integer::sum);
        }
        return counts;
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}