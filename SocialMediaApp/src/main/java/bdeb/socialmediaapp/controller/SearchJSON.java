package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.User_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name="SearchJSON", value = "/SearchJSON")
public class SearchJSON extends HttpServlet {
    private UserService userService;

    public void init()  throws ServletException {
        this.userService = new UserService(new User_DAO_MSSQL());
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        String username = request.getParameter("username");
        List<User> users = userService.findUserByUsername(username);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), users);
    }
}
