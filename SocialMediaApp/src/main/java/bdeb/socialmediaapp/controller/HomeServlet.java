package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.Post_DAO_MSSQL;
import bdeb.socialmediaapp.model.Post;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "HomeServlet", value = "/HomeServlet")
public class HomeServlet extends HttpServlet {


    //Redirige l'utilisateur a la page de connection si il n'etait pas connecter
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //La page doit etre accessible seulement si un user est connecter. Pour eviter l'acces par adresse URL
        if(session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LanguageServlet");
        } else  {
            loadPosts(request, response);
            request.getRequestDispatcher("/WEB-INF/home.jsp").forward(request, response);
        }
    }

    //S'occupe de charger les posts et de les attribuer a la reponse
    private void loadPosts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PostService postService = new PostService(new Post_DAO_MSSQL());
        User user = (User) request.getSession().getAttribute("user");
        List<Post> posts = postService.getPosts(user.getUserID());
        request.setAttribute("posts", posts);
    }
}
