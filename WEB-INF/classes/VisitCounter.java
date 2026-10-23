import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/VisitCounter")
public class VisitCounter extends HttpServlet {

    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        int count = 0;

        Cookie[] cookies = request.getCookies();

        if (cookies != null) {

            for (Cookie cookie : cookies) {

                if (cookie.getName().equals("visitCount")) {

                    count = Integer.parseInt(cookie.getValue());
                }
            }
        }

        count++;

        Cookie visitCookie =
                new Cookie("visitCount", String.valueOf(count));

        response.addCookie(visitCookie);

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head>");
        out.println("<title>Visit Counter</title>");
        out.println("</head>");

        out.println("<body>");

        out.println("<h1>Visit Counter</h1>");

        out.println("<p>Number of visits: " + count + "</p>");

        out.println("</body>");
        out.println("</html>");
    }
}