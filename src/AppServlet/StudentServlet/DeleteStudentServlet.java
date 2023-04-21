package AppServlet.StudentServlet;

import Models.Student;
import Service.StudentService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

public class DeleteStudentServlet extends HttpServlet {
        @Override
        protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                int id = Integer.parseInt(req.getParameter("studentId"));

                try {
                        StudentService.getInstance().deleteStudent(id);
                        req.setAttribute("success",true);

                } catch (SQLException e) {
                        req.setAttribute("success",false);
                        req.setAttribute("msg",e.toString());

                        final PrintWriter out = resp.getWriter();
                        out.println("<script>alert('Sorry! Invalid ID')");
                        out.println("location='CourseManage.jsp';");
                        out.println("</script>");

                        throw new RuntimeException(e);
                }
        }

        @Override
        protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
                int id = Integer.parseInt(req.getParameter("studentId"));

                try {
                        StudentService.getInstance().deleteStudent(id);
                        req.setAttribute("success",true);
                } catch (SQLException e) {
                        req.setAttribute("success",false);
                        req.setAttribute("msg",e.toString());

                        final PrintWriter out = resp.getWriter();
                        out.println("<script>alert('Sorry! Invalid ID')");
                        out.println("location='CourseManage.jsp';");
                        out.println("</script>");

                        throw new RuntimeException(e);
                }
                super.doPut(req,resp);
        }


}
