package AppServlet.CourseServlet;

import Service.CourseService;
import Service.StudentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet({"/createCourse"})
public class CreateCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/InsertCourse.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String lecture = String.valueOf(req.getParameter("lecture"));
        int year = Integer.parseInt(req.getParameter("year"));
        String notes = req.getParameter("notes");

        try {
            CourseService.getInstance().addCourse(name,lecture,year,notes);
            req.setAttribute("success",true);
            System.out.println("Successful");
        } catch (SQLException e) {
            req.setAttribute("success",false);
            req.setAttribute("msg",e.toString());
        }

        resp.sendRedirect(req.getContextPath() + "/courseManage");
    }
}
