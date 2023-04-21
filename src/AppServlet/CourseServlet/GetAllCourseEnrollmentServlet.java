package AppServlet.CourseServlet;

import Models.Student;
import Repository.CourseRepository;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;

@WebServlet(urlPatterns = {"/getStudentEnrollment"})
public class GetAllCourseEnrollmentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("courseId"));
        int year = Integer.parseInt(req.getParameter("year"));

        try {

            List<Student> students = CourseRepository.getInstance().getAllStudentEnrollment(id,year);
            req.setAttribute("students",students);
            req.setAttribute("success",true);
            RequestDispatcher dispatcher //
                    = this.getServletContext().getRequestDispatcher("/WEB-INF/views/CourseDetail.jsp");
            dispatcher.forward(req, resp);
        }
        catch (SQLException | ParseException e) {
            req.setAttribute("success",false);
            req.setAttribute("msg",e.toString());
            super.doGet(req,resp);
        }


    }
}
