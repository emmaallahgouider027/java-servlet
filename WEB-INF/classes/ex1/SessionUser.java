package ex1;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

public class SessionUser implements HttpSessionBindingListener {

    @Override
    public void valueBound(HttpSessionBindingEvent event) {

        System.out.println("SessionUser added to session!");
        System.out.println("Name: " + event.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {

        System.out.println("SessionUser removed from session!");
        System.out.println("Name: " + event.getName());
    }
}