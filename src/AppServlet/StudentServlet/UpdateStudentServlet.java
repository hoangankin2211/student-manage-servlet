package AppServlet.StudentServlet;

import Models.Student;
import Repository.StudentRepository;
import Service.StudentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.LocalDate;

@WebServlet(urlPatterns = {"/updateStudent"})
public class UpdateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            int id = Integer.parseInt(req.getParameter("studentId"));
            final Student student = StudentRepository.getInstance().getStudentById(id);
            req.setAttribute("student",student);
            req.setAttribute("success",true);

        } catch (SQLException | ParseException e) {
            req.setAttribute("success",false);

            System.out.println(e);
        }
        RequestDispatcher dispatcher = req.getServletContext()
                .getRequestDispatcher("/WEB-INF/views/UpdateStudent.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("studentId"));
        String name = req.getParameter("name");
        double grade = Double.parseDouble(req.getParameter("grade"));
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        String notes = req.getParameter("notes");
        String address = req.getParameter("address");

        try {
            StudentService.getInstance().editStudent(id,name,grade,birthday,address,notes);
            req.setAttribute("success",true);
        } catch (SQLException e) {
            String errorString = e.toString();
            req.setAttribute("success",false);
            req.setAttribute("msg", errorString);
        }

        resp.sendRedirect(req.getContextPath() + "/studentManage");
    }
}
