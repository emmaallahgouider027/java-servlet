
package ex1;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request,
                         HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Retrieve username
        String username = request.getParameter("username");

        // 2. Create or retrieve session
        HttpSession session = request.getSession();

        // 3. Store username in session
        session.setAttribute("user", username);

        // 4. Store SessionUser object to trigger HttpSessionBindingListener
        session.setAttribute("sessionUser", new SessionUser());

        // 5. Display confirmation
        response.setContentType("text/html;charset=UTF-8");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<body>");

        out.println("<h2>Welcome " + username + "!</h2>");

        out.println("<p>You are successfully logged in.</p>");

        out.println("<a href='ListItems'>Go to Items</a>");

        out.println("</body>");
        out.println("</html>");
    }
}
