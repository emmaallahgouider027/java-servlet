package ex1;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

@WebListener
public class RequestListener implements ServletRequestListener {

    @Override
    public void requestInitialized(ServletRequestEvent sre) {

        HttpServletRequest request =
                (HttpServletRequest) sre.getServletRequest();

        System.out.println("Request initialized!");
        System.out.println("URL: " + request.getRequestURI());
    }

    @Override
    public void requestDestroyed(ServletRequestEvent sre) {

        HttpServletRequest request =
                (HttpServletRequest) sre.getServletRequest();

        System.out.println("Request destroyed!");
        System.out.println("URL: " + request.getRequestURI());
    }
}
