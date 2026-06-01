package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.IUserDAO;
import bdeb.socialmediaapp.dao.User_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.SearchService;
import bdeb.socialmediaapp.util.UserNotFound;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "SearchServlet", value = "/SearchServlet")
public class SearchServlet extends HttpServlet {
    private SearchService searchService;

    public void init() throws ServletException {
        this.searchService =  new SearchService(new User_DAO_MSSQL());
    }

    //Redirection a la page login via LanguageServlet si le user n'est pas connecter
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //Pour proteger la page d'une entrer par URL
        if(session==null || session.getAttribute("user")==null){
            response.sendRedirect(request.getContextPath()+"/LanguangeServlet");
        }else{
            request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Recherche sans ajax
        String username =  request.getParameter("username");
        try{
            List<User> users = searchService.searchUser(username);
            request.setAttribute("users", users);
            request.getRequestDispatcher("/WEB-INF/search.jsp").forward(request, response);
        }catch (UserNotFound userNotFound){
            System.out.println(userNotFound.getMessage());
        }
    }
}
