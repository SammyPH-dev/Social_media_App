package bdeb.socialmediaapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.Locale;

@WebServlet(name = "LanguageServlet" , value = "/LanguageServlet")
public class LanguageServlet extends HttpServlet {

    //La servlet sert a mettre le Language du site au language du navigateur
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        Locale locale = req.getLocale();
        HttpSession session = req.getSession();
        session.setAttribute("language", locale);
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }

    //Pour changer de francais a anglais, vise versa
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String language = request.getParameter("language");
        request.getSession().setAttribute("language", language);
        String referer = request.getHeader("Referer");
        if (referer != null) {
            response.sendRedirect(referer);
        } else {
            response.sendRedirect(
                    request.getContextPath() + "/HomeServlet");
        }
    }
}
