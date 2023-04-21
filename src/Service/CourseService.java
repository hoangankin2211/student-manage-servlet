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

    public boolean addCourse(String name,String lecture, int year,String notes)  {
        try {
            MySqlUtils.getInstance().query("insert into course  (name,lecture,year,notes) values (\""+ name + "\",\"" + lecture+"\","+year+",\""+notes+ "\");");
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
