package Service;

import AppDatabase.MySqlUtils;
import Models.Course;
import Models.Student;

import java.sql.SQLException;
import java.time.LocalDate;

public class StudentService {
    private static final StudentService instance = new StudentService();

    public static StudentService getInstance(){
        return instance;
    }
    private StudentService(){}

    public void addStudent(String name, double grade, LocalDate birthday,String address, String notes) throws SQLException {
            MySqlUtils.getInstance().query("insert into course  (name,grade,birthday,address,notes) values (\""+ name + "\"," + grade+",\""+birthday+ "\");");
    }

    public void deleteStudent(int id) throws SQLException {
            MySqlUtils.getInstance().query("DELETE from student where id = "+ id + ";");
    }

    public void editStudent(int id, String name, double grade, LocalDate birthday, String address, String notes) throws SQLException {

            MySqlUtils.getInstance().query("UPDATE student\n" +
                    "            SET name = \"" + name +"\", grade = \"" + grade + "\", birthday = "+ birthday + "\", address = "+address + " , notes = \""+notes+ "\"\n" +
                    "            WHERE id = " + id + ";");
    }
}
