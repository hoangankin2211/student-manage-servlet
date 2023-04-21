package AppServlet.StudentServlet;

import Service.StudentService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;

@WebServlet({"/addStudent"})
public class CreateStudentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher dispatcher
                = this.getServletContext().getRequestDispatcher("/WEB-INF/views/InsertStudent.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double grade = Double.parseDouble(req.getParameter("grade"));
        LocalDate birthday = LocalDate.parse(req.getParameter("birthday"));
        String address = req.getParameter("address");
        String notes = req.getParameter("notes");

        try {
            StudentService.getInstance().addStudent(name,grade,birthday,address,notes);
            req.setAttribute("success",true);
            System.out.println("Successful");

        } catch (SQLException e) {
            req.setAttribute("success",false);
            req.setAttribute("msg",e.toString());
        }

        resp.sendRedirect(req.getContextPath() + "/studentManage");
    }
}
