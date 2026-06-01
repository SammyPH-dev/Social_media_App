package bdeb.socialmediaapp.controller;

import java.io.*;

import bdeb.socialmediaapp.dao.User_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.UserService;
import bdeb.socialmediaapp.util.UserNotFound;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void init() {}

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        //Prend les infos du form
        String error = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService(new User_DAO_MSSQL());
        try{
            //Va valider voir si le username ET password match avec une entree dans la bd. Rediriger au home.jsp via homeservlet
            User user = userService.validateUser(username, password);
            request.getSession().setAttribute("user", user);
            response.sendRedirect(request.getContextPath() + "/HomeServlet");
        }catch(UserNotFound e){
            //Si rien n'est retrouver, Il va rafraichir la page avec un message d'erreur en rouge
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("/login.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}