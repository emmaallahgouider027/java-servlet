
package ex1;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class RequestAttributeListener
        implements ServletRequestAttributeListener {

    @Override
    public void attributeAdded(ServletRequestAttributeEvent event) {

        System.out.println("Request attribute ADDED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Value: " + event.getValue());
    }

    @Override
    public void attributeReplaced(ServletRequestAttributeEvent event) {

        System.out.println("Request attribute REPLACED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Old value: " + event.getValue());
    }

    @Override
    public void attributeRemoved(ServletRequestAttributeEvent event) {

        System.out.println("Request attribute REMOVED:");
        System.out.println("Name: " + event.getName());
        System.out.println("Value: " + event.getValue());
    }
}
