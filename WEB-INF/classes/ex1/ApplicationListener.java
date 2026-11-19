package ex1;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {

        System.out.println("Application is starting...");

        // Get the application context
        ServletContext context = sce.getServletContext();

        // Store an application attribute
        context.setAttribute("appName", "ING1 Web Application");

        System.out.println("Application name stored successfully!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

        System.out.println("Application is stopping...");
    }
}
