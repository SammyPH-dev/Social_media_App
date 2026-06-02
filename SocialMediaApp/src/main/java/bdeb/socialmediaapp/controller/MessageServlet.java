package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.Friend_DAO_MSSQL;
import bdeb.socialmediaapp.dao.Message_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.FriendService;
import bdeb.socialmediaapp.service.MessageService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name="MessageServlet", value = "/MessageServlet")
public class MessageServlet extends HttpServlet {
    private FriendService friendService;
    private MessageService messageService;

    public void init() throws ServletException {
        this.friendService = new FriendService(new Friend_DAO_MSSQL());
        this.messageService = new MessageService(new Message_DAO_MSSQL());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        String content = request.getParameter("content");
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserID();
        messageService.sendMessage(userId, receiverId, content);
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        //La page doit etre accessible seulement si un user est connecter. Pour eviter l'acces par adresse URL
        if(session == null || session.getAttribute("user") == null) {
            response.sendRedirect(request.getContextPath() + "/LanguageServlet");
        } else  {
            getFriends(request, response);
            request.getRequestDispatcher("/WEB-INF/messages.jsp").forward(request, response);
        }
    }

    public void getFriends(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<User> friends = friendService.getFriends(user.getUserID());
        request.setAttribute("friends", friends);
    }
}
