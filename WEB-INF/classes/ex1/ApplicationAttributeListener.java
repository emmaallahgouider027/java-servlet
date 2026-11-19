
package ex1;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class ApplicationAttributeListener
        implements ServletContextAttributeListener {

    @Override
    public void attributeAdded(ServletContextAttributeEvent event) {

        System.out.println("Application attribute ADDED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletContextAttributeEvent event) {

        System.out.println("Application attribute REPLACED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Old value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletContextAttributeEvent event) {

        System.out.println("Application attribute REMOVED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Value: " + event.getValue());
    }
}
