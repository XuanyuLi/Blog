package servlet;

import org.jasypt.util.password.StrongPasswordEncryptor;
import util.Db;
import util.Error;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by lixuanyu
 * on 2017/7/1.
 */
@WebServlet(urlPatterns = "/user")
public class UserAction extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if ("register".equals(action)) {
            register(req, resp);
            return;
        }
        if ("login".equals(action)) {
            login(req, resp);
            return;
        }
        Error.showError(req, resp);
    }

    private void register(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nick = req.getParameter("nick").trim();
        String email = req.getParameter("email").trim();
        String plainPassword = req.getParameter("password");
        if (nick.length() == 0) {
            req.setAttribute("message", "请输入昵称");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }

        if (email.length() == 0) {
            req.setAttribute("message", "请输入邮箱");
            req.getRequestDispatcher("register.jsp").forward(req, resp);
            return;
        }
        StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
        String password = encryptor.encryptPassword(plainPassword);
        Connection connection = Db.getConnection();
        PreparedStatement preparedStatement = null;

        String sql = "INSERT INTO db_blog.user(nick, email, password) VALUE( ?, ?, ?)";

        try {
            if (connection != null) {
                preparedStatement = connection.prepareStatement(sql);
            } else {
                Error.showError(req, resp);
                return;
            }
            preparedStatement.setString(1, nick);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, password);
            preparedStatement.executeUpdate();
            resp.sendRedirect("index.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(null, preparedStatement, connection);
        }
    }

    private void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nick = req.getParameter("nick");
        String email = req.getParameter("email").trim();
        String plainPassword = req.getParameter("password");
        Connection connection = Db.getConnection();
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String sql = "SELECT * FROM db_blog.user WHERE email = ?";
        try {
            if (connection != null) {
                statement = connection.prepareStatement(sql);
            } else {
                Error.showError(req, resp);
                return;
            }
            statement.setString(1, email);
            resultSet = statement.executeQuery();
            if (resultSet.next()) {
                String encryptedPassword = resultSet.getString("password");
                StrongPasswordEncryptor encryptor = new StrongPasswordEncryptor();
                if (encryptor.checkPassword(plainPassword, encryptedPassword)) {
                    req.setAttribute("nick", nick);
                    req.getRequestDispatcher("home.jsp").forward(req,resp);
                }
            } else {
                req.setAttribute("message", "邮箱或密码错误");
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            Db.close(resultSet, statement, connection);
        }

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }
}
