package bdeb.socialmediaapp.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@WebServlet(name="ImageServlet", value = "/ImageServlet")
public class ImageServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Retire l'information du jsp et je donne la valeur de ou le fichier va etre
        String fileName = request.getParameter("file");
        String uploadPath = request.getServletContext().getRealPath("/")
                        + ".." + File.separator + ".."
                        + File.separator + ".."
                        + File.separator + "SocialMediaUploads";
        //Je charge l'imagine et je m'assure de quelle type l'image est
        File imageFile = new File(uploadPath, fileName);
        String contentType = request.getServletContext().getMimeType(imageFile.getName());
        //je renvoi la reponse avec l'image attacher
        response.setContentType(contentType);
        response.setContentLengthLong(imageFile.length());
        Files.copy(imageFile.toPath(), response.getOutputStream());
    }
}
