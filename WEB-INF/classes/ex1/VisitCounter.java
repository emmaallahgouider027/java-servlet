package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/VisitCounter")
public class VisitCounter extends HttpServlet {

    private static final int ONE_YEAR = 60 * 60 * 24 * 365;

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int count = 0;
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("visitCount".equals(cookie.getName())) {
                    try {
                        count = Integer.parseInt(cookie.getValue());
                    } catch (NumberFormatException e) {
                        count = 0;
                    }
                }
            }
        }

        count++;

        Cookie visitCookie = new Cookie("visitCount", String.valueOf(count));
        visitCookie.setPath(request.getContextPath().isEmpty()
                ? "/" : request.getContextPath());
        visitCookie.setMaxAge(ONE_YEAR); // sans cela, le compteur repart à 0 à la fermeture du navigateur
        response.addCookie(visitCookie);

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Visit Counter</title></head><body>");
        out.println("<h1>Visit Counter</h1>");
        out.println("<p>Number of visits: " + count + "</p>");
        out.println("</body></html>");
    }
}