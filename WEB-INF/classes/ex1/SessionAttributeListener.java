
package ex1;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class SessionAttributeListener
        implements HttpSessionAttributeListener {

    @Override
    public void attributeAdded(HttpSessionBindingEvent event) {

        System.out.println("Session attribute ADDED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(HttpSessionBindingEvent event) {

        System.out.println("Session attribute REPLACED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Old value: " + event.getValue());
        System.out.println("New value: "
                + event.getSession().getAttribute(event.getName()));
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {

        System.out.println("Session attribute REMOVED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Value: " + event.getValue());
    }
}
