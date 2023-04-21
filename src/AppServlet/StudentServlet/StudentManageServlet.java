package AppServlet.StudentServlet;

import Models.Student;
import Repository.StudentRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@WebServlet(urlPatterns = {"/","/studentManage"})
public class StudentManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students;

        try {
            students = new ArrayList<>(StudentRepository.getInstance().getAllStudent());

            req.setAttribute("students",students);
            req.setAttribute("success",true);

        } catch (SQLException e) {
            req.setAttribute("success",false);
            req.setAttribute("students",new ArrayList<Student>());
            throw new RuntimeException(e);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/WEB-INF/views/StudentManage.jsp");
        dispatcher.forward(req, resp);
    }
}
