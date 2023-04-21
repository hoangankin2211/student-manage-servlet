package Service;

import AppDatabase.MySqlUtils;
import Models.Course;

import java.sql.SQLException;
import java.time.LocalDate;

public class StudentService {
    private static final StudentService instance = new StudentService();

    public static StudentService getInstance(){
        return instance;
    }
    private StudentService(){}

    public boolean addCourse(String name, double grade, LocalDate birthday,String address, String notes)  {
        try {
            MySqlUtils.getInstance().query("insert into course  (name,grade,birthday,address,notes) values (\""+ name + "\"," + grade+",\""+birthday+ "\");");
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean deleteCourse(int id,int year){
        try {
            MySqlUtils.getInstance().query("DELETE from course where idCourse = "+ id + " and year = "+ year + ";");
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }

    public boolean editCourse(Course course){
        try {

            MySqlUtils.getInstance().query("UPDATE course\n" +
                    "            SET name = \"" + course.getName() +"\", lecture = \"" + course.getLecture() + "\", year = "+ course.getYear() + " , notes = \""+course.getNotes()+ "\"\n" +
                    "            WHERE idCourse = " + course.getIdCourse() + ";");
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
}
