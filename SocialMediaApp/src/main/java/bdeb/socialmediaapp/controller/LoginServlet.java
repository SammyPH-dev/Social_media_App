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
        String error = "";
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        UserService userService = new UserService(new User_DAO_MSSQL());
        try{
            User user = userService.validateUser(username, password);
            request.getSession().setAttribute("user", user);
            request.getRequestDispatcher("home.jsp").forward(request, response);
        }catch(UserNotFound e){
            request.setAttribute("error", "User not found");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    public void destroy() {
    }
}