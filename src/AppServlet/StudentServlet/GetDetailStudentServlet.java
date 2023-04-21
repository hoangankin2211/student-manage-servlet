package AppServlet.StudentServlet;

import Models.CourseStudentGrade;
import Repository.StudentRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.util.List;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(urlPatterns = {"/getStudentDetails"})
public class GetDetailStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int year = Integer.parseInt(req.getParameter("year"));
        int studentId = Integer.parseInt(req.getParameter("studentId"));

        try {
            List<CourseStudentGrade> data = StudentRepository.getInstance().getAllStudentGradeByYear(studentId,year);
            req.setAttribute("student",data);
            req.setAttribute("success",true);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/StudentDetail.jsp");
            dispatcher.forward(req, resp);
        } catch (SQLException | ParseException e) {
            req.setAttribute("success",false);
            super.doGet(req,resp);
        }
    }
}
