package Service;

import AppDatabase.MySqlUtils;
import Models.Course;

import java.sql.SQLException;

public class CourseService {
    private static final CourseService instance = new CourseService();

    public static CourseService getInstance(){
        return instance;
    }
    private CourseService(){}

    public void addCourse(String name,String lecture, int year,String notes) throws SQLException {
        MySqlUtils.getInstance().query("insert into course (name,lecture,year,notes) " +
                "values (\""+ name + "\",\"" + lecture+"\","+year+",\""+notes+ "\");");
    }

    public void deleteCourse(int id,int year) throws SQLException {
        MySqlUtils.getInstance().query("DELETE from course where idCourse = "
                + id + " and year = "+ year + ";");
    }

    public void editCourse(int id,String name,String lecture,int year,String notes) throws SQLException {
        MySqlUtils.getInstance().query("UPDATE course\n" +
        "            SET name = \"" + name+"\", lecture = \"" +lecture + "\", year = "+year + " , notes = \""+notes+ "\"\n" +
        "            WHERE idCourse = " + id + ";");
    }
}
