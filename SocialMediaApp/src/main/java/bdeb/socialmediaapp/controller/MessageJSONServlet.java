package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.Message_DAO_MSSQL;
import bdeb.socialmediaapp.model.Message;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "MessageJSONServlet", value = "/MessageJSONServlet")
public class MessageJSONServlet extends HttpServlet {
    private MessageService messageService;

    public void init() throws ServletException {
        this.messageService = new MessageService(new Message_DAO_MSSQL());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        User user = (User) request.getSession().getAttribute("user");
        int userId = user.getUserID();
        int friendID = Integer.parseInt(request.getParameter("friendID"));
        List<Message> conversation = messageService.getConversationByFriendId(userId,friendID);
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(response.getWriter(), conversation);
    }
}
