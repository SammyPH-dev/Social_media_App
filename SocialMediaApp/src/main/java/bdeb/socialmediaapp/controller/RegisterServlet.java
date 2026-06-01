package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.User_DAO_MSSQL;
import bdeb.socialmediaapp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "RegisterServlet", value = "/RegisterServlet")
public class RegisterServlet extends HttpServlet {

    //Lorseque le user clique sur register/enregistrer, le client est rediriger au login.jsp avec soit un message de succes ou d'erreur (rouge ou vert)
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException
    {
        //Prise d'info du form
        UserService userService = new UserService(new User_DAO_MSSQL());
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        try {
            //Creation du user via userService. Rediriger avec un message de succes
            userService.createUser(username, password);
            request.setAttribute("registration", "Registration successful");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }catch(Exception e){
            //Echoue de la creation de compte, retour avec un message d'echoue
            request.setAttribute("error", "Probleme");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }
}
