package AppServlet.CourseServlet;

import Repository.CourseRepository;
import Service.CourseService;
import Service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(urlPatterns = {"/deleteCourse"})
public class DeleteCourseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("courseId"));
        int year = Integer.parseInt(req.getParameter("year"));


        try {
            CourseService.getInstance().deleteCourse(id,year);
            req.setAttribute("success",true);
        } catch (SQLException e) {
            req.setAttribute("success",false);
            req.setAttribute("msg",e.toString());
            throw new RuntimeException(e);
        }
        super.doPost(req,resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("courseId"));
        int year = Integer.parseInt(req.getParameter("year"));

        try {
            CourseService.getInstance().deleteCourse(id,year);
            req.setAttribute("success",true);
        } catch (SQLException e) {
            req.setAttribute("success",false);
            req.setAttribute("msg",e.toString());
            throw new RuntimeException(e);
        }
        super.doPut(req,resp);
    }
}
