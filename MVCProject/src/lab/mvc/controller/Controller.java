
package lab.mvc.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import lab.mvc.model.Person;

@WebServlet("/Controller")
public class Controller extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // 1. Recuperer les donnees du formulaire
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String birthDay = request.getParameter("birthDay");
        String sex = request.getParameter("sex");

        // 2. Creer l'objet Person (Model)
        Person person = new Person(firstName, lastName, birthDay, sex);

        // 3. Stocker l'objet dans la requete
        request.setAttribute("person", person);

        // 4. Recuperer l'annee de naissance
        int year = Integer.parseInt(birthDay.substring(0, 4));

        // 5. Choisir la JSP
        String page;

        if (year < 1990) {
            page = "/jsp/old.jsp";
        } else {
            page = "/jsp/young.jsp";
        }

        // 6. Envoyer la requete vers la JSP choisie
        RequestDispatcher dispatcher =
                getServletContext().getRequestDispatcher(page);

        dispatcher.include(request, response);
    }
}
