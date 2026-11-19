
package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ShowSession extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // Récupérer ou créer une session
        HttpSession session = request.getSession();

        // Réinitialiser la session
        if ("1".equals(request.getParameter("reset"))) {
            session.invalidate();
            response.sendRedirect("ShowSession");
            return;
        }

        // Compter les visites
        Integer visits = (Integer) session.getAttribute("visits");

        if (visits == null) {
            visits = 1;
        } else {
            visits++;
        }

        session.setAttribute("visits", visits);

        // Récupérer les informations de l'utilisateur
        String fullName = (String) session.getAttribute("fullName");
        LocalDate birthDate =
            (LocalDate) session.getAttribute("birthDate");

        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");

        // Message de bienvenue
        if (visits == 1) {
            out.println("<h1>Welcome on my site</h1>");
        } else {
            out.println("<h1>Welcome back</h1>");
        }

        out.println("<p>Number of visits: " + visits + "</p>");
        Integer activeSessions = (Integer)
        getServletContext().getAttribute("activeSessions");

if (activeSessions == null) {
    activeSessions = 0;
}

out.println("<p>Active sessions: " + activeSessions + "</p>");

        // Vérifier si les informations existent
        if (fullName != null && birthDate != null) {

            // Calculer les jours avant le prochain anniversaire
            LocalDate today = LocalDate.now();

            LocalDate birthday = birthDate.withYear(today.getYear());

            if (birthday.isBefore(today)) {
                birthday = birthday.plusYears(1);
            }

            long days = ChronoUnit.DAYS.between(today, birthday);

            out.println("<p>Hello " + fullName + "</p>");
            out.println("<p>Days until your birthday: " + days + "</p>");

        } else {

            // Afficher le formulaire
            out.println("<h2>Please tell us about you</h2>");

            out.println("<form method='post' action='ShowSession'>");

            out.println("Full name: ");
            out.println("<input type='text' name='fullName' required><br>");

            out.println("Birth date: ");
            out.println("<input type='date' name='birthDate' required><br>");

            out.println("<input type='submit' value='Save'>");

            out.println("</form>");
        }

        out.println("<a href='ShowSession'>Reload</a><br>");
        out.println("<a href='ShowSession?reset=1'>Reset session</a>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");

        HttpSession session = request.getSession();

        // Récupérer les données du formulaire
        String fullName = request.getParameter("fullName");
        String birth = request.getParameter("birthDate");

        // Transformer la date String en LocalDate
        LocalDate birthDate = LocalDate.parse(birth);

        // Enregistrer les données dans la session
        session.setAttribute("fullName", fullName);
        session.setAttribute("birthDate", birthDate);

        // Retourner vers doGet()
        response.sendRedirect("ShowSession");
    }
}
