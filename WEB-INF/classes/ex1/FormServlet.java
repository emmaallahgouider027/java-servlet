package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// Deux URL pour la même servlet :
//   http://localhost:8080/LAB1/FormServlet
//   http://localhost:8080/LAB1/Form_Servlet
@WebServlet({"/FormServlet", "/Form_Servlet"})
public class FormServlet extends HttpServlet {

    private static final int ONE_YEAR = 60 * 60 * 24 * 365;

    // Ouvrir l'URL directement dans le navigateur (GET) renvoie vers le formulaire
    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {
        response.sendRedirect(request.getContextPath() + "/index.html");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        // Accents lus correctement (à faire AVANT getParameter)
        request.setCharacterEncoding("UTF-8");

        String firstName = clean(request.getParameter("firstName"));
        String lastName = clean(request.getParameter("lastName"));
        String birthDate = clean(request.getParameter("birthDate"));
        String sex = clean(request.getParameter("sex")); // vide si aucun bouton radio choisi

        // Les cookies doivent être ajoutés AVANT d'écrire la page
        addCookie(request, response, "firstName", firstName);
        addCookie(request, response, "lastName", lastName);
        addCookie(request, response, "birthDay", birthDate);
        addCookie(request, response, "sex", sex);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head><meta charset='UTF-8'><title>Personal Information</title></head>");
        out.println("<body>");
        out.println("<h1>Personal Information</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>First Name</th><th>Last Name</th>"
                + "<th>Birth Date</th><th>Sex</th></tr>");
        out.println("<tr><td>" + escape(firstName) + "</td><td>" + escape(lastName)
                + "</td><td>" + escape(birthDate) + "</td><td>" + escape(sex)
                + "</td></tr>");
        out.println("</table>");
        out.println("<p><a href='ReadCookie'>Read cookies</a></p>");
        out.println("</body>");
        out.println("</html>");
    }

    // Un cookie ne peut pas contenir d'espace, de virgule ni de point-virgule :
    // on encode la valeur (ReadCookie la décode).
    private void addCookie(HttpServletRequest request, HttpServletResponse response,
                           String name, String value) {
        Cookie cookie = new Cookie(name, URLEncoder.encode(value, StandardCharsets.UTF_8));
        String path = request.getContextPath();
        cookie.setPath(path.isEmpty() ? "/" : path);
        cookie.setMaxAge(ONE_YEAR);
        response.addCookie(cookie);
    }

    private static String clean(String s) {
        return s == null ? "" : s.trim();
    }

    // Évite qu'un texte saisi du genre <script> soit exécuté dans la page
    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}