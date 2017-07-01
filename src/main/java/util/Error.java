package util;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lixuanyu
 * on 2017/6/27.
 */
public class Error {
    public static void showError(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("message","Error");
        try {
            request.getRequestDispatcher("register.jsp").forward(request,response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
}
