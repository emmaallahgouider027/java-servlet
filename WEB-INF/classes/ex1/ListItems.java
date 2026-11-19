
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.*;

public class ListItems extends HttpServlet {

    // Nom de l'attribut dans la session
    private static final String ATTR = "items";

    // Ajouter un item
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {

        request.setCharacterEncoding("UTF-8");

        String newItem = request.getParameter("newItem");

        HttpSession session = request.getSession();

        ArrayList<String> items =
            (ArrayList<String>) session.getAttribute(ATTR);

        if (items == null) {
            items = new ArrayList<String>();
        }

        if (newItem != null && !newItem.trim().isEmpty()) {
            items.add(newItem.trim());
        }

        session.setAttribute(ATTR, items);

        response.sendRedirect("ListItems");
    }

    // Afficher les items
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws IOException {

        HttpSession session = request.getSession();

        // Vider la liste
        if ("1".equals(request.getParameter("clear"))) {
            session.removeAttribute(ATTR);
            response.sendRedirect("ListItems");
            return;
        }

        ArrayList<String> items =
            (ArrayList<String>) session.getAttribute(ATTR);

        if (items == null) {
            items = new ArrayList<String>();
        }

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html><body>");

        out.println("<h1>Your items</h1>");

        if (items.isEmpty()) {
            out.println("<p>The list is empty.</p>");
        } else {

            // Afficher la liste
            out.println("<h2>List of items</h2>");
            out.println("<ol>");

            for (String item : items) {
                out.println("<li>" + item + "</li>");
            }

            out.println("</ol>");

            // Compter les occurrences
            Map<String, Integer> counts = new LinkedHashMap<>();

            for (String item : items) {

                if (counts.containsKey(item)) {
                    counts.put(item, counts.get(item) + 1);
                } else {
                    counts.put(item, 1);
                }
            }

            // Afficher les occurrences
            out.println("<h2>Number of times each item was entered</h2>");
            out.println("<table border='1'>");
            out.println("<tr><th>Item</th><th>Times</th></tr>");

            for (String item : counts.keySet()) {
                out.println("<tr>");
                out.println("<td>" + item + "</td>");
                out.println("<td>" + counts.get(item) + "</td>");
                out.println("</tr>");
            }

            out.println("</table>");
        }

        out.println("<p><a href='items.html'>Add another item</a></p>");
        out.println("<p><a href='ListItems?clear=1'>Clear the list</a></p>");

        out.println("</body></html>");
    }
}
