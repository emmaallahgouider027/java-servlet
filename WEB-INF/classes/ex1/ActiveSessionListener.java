package ex1;

import java.util.concurrent.atomic.AtomicInteger;

import javax.servlet.ServletContext;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class ActiveSessionListener implements HttpSessionListener {

    private final AtomicInteger counter = new AtomicInteger(0);

    @Override
    public void sessionCreated(HttpSessionEvent se) {

        int active = counter.incrementAndGet();

        ServletContext context =
                se.getSession().getServletContext();

        context.setAttribute("activeSessions", active);

        System.out.println("New session created!");
        System.out.println("Active sessions: " + active);
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {

        int active = counter.decrementAndGet();

        ServletContext context =
                se.getSession().getServletContext();

        context.setAttribute("activeSessions", active);

        System.out.println("Session destroyed!");
        System.out.println("Active sessions: " + active);
    }
}
