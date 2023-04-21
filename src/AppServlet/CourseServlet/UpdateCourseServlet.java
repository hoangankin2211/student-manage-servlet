package AppServlet.CourseServlet;

import Models.Course;
import Repository.CourseRepository;
import Service.CourseService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
@WebServlet(urlPatterns = {"/updateCourse"})
public class UpdateCourseServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("courseId"));
            final Course course = CourseRepository.getInstance().getCourseById(id);
            req.setAttribute("course",course);
            req.setAttribute("success",true);
            RequestDispatcher dispatcher = req.getServletContext()
                    .getRequestDispatcher("/WEB-INF/views/UpdateCourse.jsp");
            dispatcher.forward(req, resp);

        } catch (SQLException e) {
            System.out.println(e);

            req.setAttribute("success",false);
            req.setAttribute("msg",e.toString());

            final PrintWriter out = resp.getWriter();
            out.println("<script>alert('Sorry! Invalid ID')");
            out.println("location='CourseManage.jsp';");
            out.println("</script>");
        }

    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("courseId"));
        String name = req.getParameter("name");
        String lecture = String.valueOf(req.getParameter("lecture"));
        int year = Integer.parseInt(req.getParameter("year"));
        String notes = req.getParameter("notes");

        try {
            CourseService.getInstance().editCourse(id,name,lecture,year,notes);
            req.setAttribute("success",true);
        } catch (SQLException e) {
            String errorString = e.toString();
            req.setAttribute("success",false);
            req.setAttribute("msg", errorString);

            final PrintWriter out = resp.getWriter();
            out.println("<script>alert('Sorry! Invalid ID')");
            out.println("location='CourseManage.jsp';");
            out.println("</script>");
        }

        resp.sendRedirect(req.getContextPath() + "/courseManage");
    }
}
