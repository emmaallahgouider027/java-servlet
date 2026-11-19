package ex1;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
@WebFilter("/ListItems")
public class SecurityFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("SecurityFilter initialized!");
    }

    @Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
                         throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;

        // 1. Check if a session already exists
        HttpSession session = req.getSession(false);

        // 2. Check if the user is connected
        if (session != null && session.getAttribute("user") != null) {

            System.out.println("User is authenticated!");

            // 3. Allow access to the servlet
            chain.doFilter(request, response);

        } else {

            System.out.println("Access denied!");

            // 4. Redirect to login page
            res.sendRedirect(req.getContextPath() + "/login.html");
        }
    }

    @Override
    public void destroy() {
        System.out.println("SecurityFilter destroyed!");
    }
}
