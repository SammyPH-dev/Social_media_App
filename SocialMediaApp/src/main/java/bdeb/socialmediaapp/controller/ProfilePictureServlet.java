package bdeb.socialmediaapp.controller;

import bdeb.socialmediaapp.dao.User_DAO_MSSQL;
import bdeb.socialmediaapp.model.User;
import bdeb.socialmediaapp.service.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.File;
import java.io.IOException;

@WebServlet(name="ProfilePictureServlet", value = "/ProfilePictureServlet")
@MultipartConfig
public class ProfilePictureServlet extends HttpServlet {
    private UserService userService;

    public void init() throws ServletException {
        this.userService = new UserService(new  User_DAO_MSSQL());
    }

    //La methode va changer la photo de profile de l'utilisateur
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //Prise de l'information user et du fichier a partir de la session
        HttpSession session = request.getSession(false);
        User user = (User) session.getAttribute("user");
        Part imagePart = request.getPart("profilePicture");

        //Creation du nom du fichier et de le mettre dans le fichier uploads
        String fileName = user.getUserID() + "_profile.jpg";
        String uploadPath = request.getServletContext().getRealPath("/")
                +".."+ File.separator + ".."
                + File.separator + ".."
                + File.separator + "SocialMediaUploads";
        imagePart.write(uploadPath + File.separator + fileName);

        //Sauvegarder dans base de donner et de update le user dans la session
        this.userService.setProfileImage(user.getUserID(), fileName);
        user.setProfilePicture(fileName);
        session.setAttribute("user", user);
        session.setAttribute("picturePath", uploadPath);
        response.sendRedirect(request.getContextPath() + "/ProfileServlet");
    }
}
