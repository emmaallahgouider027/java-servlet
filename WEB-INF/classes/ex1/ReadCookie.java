package ex1;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/ReadCookie")
public class ReadCookie extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        String firstName = "";
        String lastName = "";
        String birthDate = "";
        String sex = "";

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                String value = decode(cookie.getValue());
                switch (cookie.getName()) {
                    case "firstName":
                        firstName = value;
                        break;
                    case "lastName":
                        lastName = value;
                        break;
                    case "birthDate":
                    case "birthDay":
                        birthDate = value;
                        break;
                    case "sex":
                        sex = value;
                        break;
                    default:
                        break;
                }
            }
        }

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        out.println("<!DOCTYPE html>");
        out.println("<html><head><meta charset='UTF-8'><title>Read Cookies</title></head><body>");
        out.println("<h1>Cookies Information</h1>");
        out.println("<table border='1'>");
        out.println("<tr><th>First Name</th><th>Last Name</th>"
                + "<th>Birth Date</th><th>Sex</th></tr>");
        out.println("<tr><td>" + escape(firstName) + "</td><td>" + escape(lastName)
                + "</td><td>" + escape(birthDate) + "</td><td>" + escape(sex)
                + "</td></tr>");
        out.println("</table>");
        out.println("<p><a href='index.html'>Back to the form</a></p>");
        out.println("</body></html>");
    }

    private static String decode(String s) {
        try {
            return URLDecoder.decode(s, StandardCharsets.UTF_8);
        } catch (IllegalArgumentException e) {
            return s; // ancien cookie non encodé
        }
    }

    private static String escape(String s) {
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;")
                .replace("\"", "&quot;").replace("'", "&#39;");
    }
}