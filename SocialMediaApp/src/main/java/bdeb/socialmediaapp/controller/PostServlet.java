package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.Post_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.PostService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "PostServlet", value = "/PostServlet")
public class PostServlet extends HttpServlet {

    //Lorsque le user clique sur poste, la methode va creer un poste a partir de PosteService et rafraichit la page
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtient les infos du form et de la session
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("user"); //Obtient le user a partir de la session
        int userID = user.getUserID();
        //Je passe les info au service pour qu'il creer le post
        PostService postService = new PostService(new Post_DAO_MSSQL());
        postService.createPost(userID, content);
        //rafraichit la page
        response.sendRedirect(request.getContextPath() + "/HomeServlet");
    }

}
