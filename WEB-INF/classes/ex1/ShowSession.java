package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.MonthDay;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

// http://localhost:8080/LAB1/ShowSession
//   - 1re visite : "Welcome on my site" + formulaire (nom complet + date de naissance)
//   - visites suivantes : "Welcome back" + nombre de jours avant l'anniversaire
//   - ?reset=1 : détruit la session (utile pour retester)
@WebServlet("/ShowSession")
public class ShowSession extends HttpServlet {

    private static final DateTimeFormatter US_DATE = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(true);

        if ("1".equals(request.getParameter("reset"))) {
            session.invalidate();
            response.sendRedirect(request.getRequestURI());
            return;
        }

        // Compteur de visites stocké dans la session.
        // Après l'envoi du formulaire (redirection), on ne recompte pas.
        Integer visits = (Integer) session.getAttribute("visits");
        if (session.getAttribute("skipCount") != null) {
            session.removeAttribute("skipCount");
        } else {
            visits = (visits == null) ? 1 : visits + 1;
            session.setAttribute("visits", visits);
        }
        if (visits == null) { // sécurité
            visits = 1;
            session.setAttribute("visits", visits);
        }

        String fullName = (String) session.getAttribute("fullName");
        LocalDate birthDate = (LocalDate) session.getAttribute("birthDate");
        String error = (String) session.getAttribute("formError");
        session.removeAttribute("formError");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Show Session</title></head><body>");
        out.println("<h1>" + (visits == 1 ? "Welcome on my site" : "Welcome back") + "</h1>");
        out.println("<p>Number of visits: " + visits + "</p>");

        if (fullName != null && birthDate != null) {
            long days = daysToBirthday(birthDate, LocalDate.now());
            if (days == 0) {
                out.println("<p>Hi, " + escape(fullName) + ". Happy birthday!</p>");
            } else if (days == 1) {
                out.println("<p>Hi, " + escape(fullName) + ". There is 1 day to your birthday.</p>");
            } else {
                out.println("<p>Hi, " + escape(fullName) + ". There are " + days
                        + " days to your birthday.</p>");
            }
        } else {
            out.println("<p>Please tell us a little about you:</p>");
            if (error != null) {
                out.println("<p style='color:red'>" + escape(error) + "</p>");
            }
            out.println("<form method='post' action='" + response.encodeURL("ShowSession") + "'>");
            out.println("<p><label>Full name: <input type='text' name='fullName' required></label></p>");
            out.println("<p><label>Date of birth: <input type='date' name='birthDate' required></label></p>");
            out.println("<p><input type='submit' value='Save'></p>");
            out.println("</form>");
        }

        out.println("<p><small>Session ID: " + escape(session.getId()) + "</small></p>");
        out.println("<p><a href='ShowSession'>Reload</a> | <a href='ShowSession?reset=1'>Reset session</a>"
                + " | <a href='index.html'>Back to the form</a></p>");
        out.println("</body></html>");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws ServletException, IOException {

        request.setCharacterEncoding("UTF-8");
        HttpSession session = request.getSession(true);

        if (session.getAttribute("visits") == null) {
            session.setAttribute("visits", 1);
        }

        String fullName = request.getParameter("fullName");
        String birth = request.getParameter("birthDate");
        fullName = (fullName == null) ? "" : fullName.trim();

        LocalDate birthDate = parseDate(birth);
        LocalDate today = LocalDate.now();

        if (fullName.isEmpty()) {
            session.setAttribute("formError", "Please enter your full name.");
        } else if (birthDate == null) {
            session.setAttribute("formError", "Invalid date of birth.");
        } else if (birthDate.isAfter(today)) {
            session.setAttribute("formError", "The date of birth cannot be in the future.");
        } else {
            session.setAttribute("fullName", fullName);
            session.setAttribute("birthDate", birthDate);
        }

        // Redirection (évite le renvoi du formulaire au rechargement) sans recompter la visite
        session.setAttribute("skipCount", Boolean.TRUE);
        response.sendRedirect(response.encodeRedirectURL(request.getRequestURI()));
    }

    // Accepte aaaa-mm-jj (champ date HTML) et mm/jj/aaaa (format de l'énoncé)
    private static LocalDate parseDate(String s) {
        if (s == null) {
            return null;
        }
        s = s.trim();
        try {
            return LocalDate.parse(s);
        } catch (DateTimeParseException e) {
            try {
                return LocalDate.parse(s, US_DATE);
            } catch (DateTimeParseException e2) {
                return null;
            }
        }
    }

    // Nombre de jours jusqu'au prochain anniversaire (0 = aujourd'hui).
    // MonthDay gère le 29 février : en année non bissextile, on retombe sur le 28.
    static long daysToBirthday(LocalDate birth, LocalDate today) {
        MonthDay md = MonthDay.from(birth);
        LocalDate next = md.atYear(today.getYear());
        if (next.isBefore(today)) {
            next = md.atYear(today.getYear() + 1);
        }
        return ChronoUnit.DAYS.between(today, next);
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}