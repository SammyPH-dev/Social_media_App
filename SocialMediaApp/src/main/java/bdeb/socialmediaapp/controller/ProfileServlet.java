package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.IPostDAO;
import bdeb.socialmediaapp.dao.Post_DAO_MSSQL;
import bdeb.socialmediaapp.model.Post;
import bdeb.socialmediaapp.model.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name="ProfileServlet", value = "/ProfileServlet")
public class ProfileServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //La page doit etre accessible seulement si un user est connecter. Pour eviter l'acces par adresse URL
        if(session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LanguageServlet");
        } else  {
            loadProfilePosts(request);
            request.getRequestDispatcher("/WEB-INF/profile.jsp").forward(request, response);
        }
    }

    //Charge toute les posts de ce user
    public void loadProfilePosts(HttpServletRequest request) throws ServletException, IOException {
        //Recherche toute les posts fait par le user
        User  user = (User) request.getSession().getAttribute("user");
        IPostDAO postDAO = new Post_DAO_MSSQL();
        List<Post> posts = postDAO.fetchPostById(user.getUserID());
        //Colle la liste a request
        request.setAttribute("posts", posts);
    }
}
