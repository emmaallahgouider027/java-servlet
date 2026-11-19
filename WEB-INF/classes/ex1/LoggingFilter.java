package ex1;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.annotation.WebFilter;
//Intercepter les URLs de l'application.
//@WebFilter("/*")
public class LoggingFilter implements Filter {
    private String logPrefix;

   @Override
public void init(FilterConfig filterConfig) throws ServletException {
    logPrefix = filterConfig.getInitParameter("logPrefix");
    System.out.println(logPrefix + " LoggingFilter initialized");
}

    /*@Override
    public void doFilter(ServletRequest request,
                         ServletResponse response,
                         FilterChain chain)
            throws IOException, ServletException {

        System.out.println(logPrefix + " Before servlet");

        chain.doFilter(request, response);

        System.out.println(logPrefix + " After servlet");
    }*/
   
@Override
public void doFilter(ServletRequest request,
                     ServletResponse response,
                     FilterChain chain)
                     throws IOException, ServletException {

    long start = System.nanoTime();

    System.out.println("Before Servlet execution");

    System.out.println("Requested URL: " +
        ((javax.servlet.http.HttpServletRequest) request).getRequestURL());

    try {
        chain.doFilter(request, response);
    }
    finally {
        long end = System.nanoTime();

        long duration = end - start;

        double milliseconds = duration / 1_000_000.0;

        System.out.println("After Servlet execution");

        System.out.println("Execution time: " +
            milliseconds + " ms");
    }
}


    @Override
    public void destroy() {
        System.out.println("LoggingFilter destroyed");
    }
}