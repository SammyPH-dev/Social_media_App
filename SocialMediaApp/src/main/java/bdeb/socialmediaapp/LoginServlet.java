package bdeb.socialmediaapp;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {

    public void init() {}

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
    }

    public void destroy() {
    }
}