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
        //test
        System.out.println("Browser locale: " + req.getLocale());
        System.out.println("Language: " + req.getLocale().getLanguage());

        Locale locale = req.getLocale();
        HttpSession session = req.getSession();
        session.setAttribute("language", locale);
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }
}
