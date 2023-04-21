package Repository;

import AppDatabase.MySqlUtils;
import Models.Course;
import Models.Student;
import jdk.jshell.spi.ExecutionControl;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.time.Instant;
import java.time.Year;
import java.time.temporal.ChronoField;
import java.time.temporal.TemporalField;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRepository {

    private static final CourseRepository instance = new CourseRepository();

    public static CourseRepository getInstance(){
        return instance;
    }


    public List<Course> getAllCourse() throws SQLException {
        final ResultSet result = MySqlUtils.getInstance().query("select* from course;");
        List<Course> courses = new ArrayList<>();
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while (result.next()) {
            Map<String,Object> mapData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                mapData.put(resultSetMetaData.getColumnLabel(i), result.getObject(resultSetMetaData.getColumnLabel(i)));
            }
                courses.add(new Course(mapData));
        }
        return courses;
    }

    public List<Course> getAllCourseByYear(int year) throws SQLException {
        final ResultSet result = MySqlUtils.getInstance().query("select * from course where year = " + year + ";");
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        List<Course> courses = new ArrayList<>();

        while (result.next()) {
            Map<String,Object> mapData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                mapData.put(resultSetMetaData.getColumnLabel(i), result.getObject(resultSetMetaData.getColumnLabel(i)));
            }
                courses.add(new Course(mapData));
        }
        return courses;
    }

    public List<Course> getAllCourseByStudentId(int id,int year) throws SQLException {
        final ResultSet result = MySqlUtils.getInstance().query("select * from student_enrollment where studentId = " + id + " and year = " + year +";");

        List<Course> courses = new ArrayList<>();

        while (result.next()) {
            int courseId = result.getInt("courseId");
            final Course course = CourseRepository.getInstance().getCourseById(courseId);
            courses.add(course);
        }
        return courses;
    }

    public List<Course> getAllCourseByStudentId(int id) throws SQLException {
        final ResultSet result = MySqlUtils.getInstance().query("select * from student_enrollment where studentId = " + id + ";");
        ResultSetMetaData resultSetMetaData = result.getMetaData();

        List<Course> courses = new ArrayList<>();

        while (result.next()) {
            int courseId = result.getInt("courseId");
            final Course course = CourseRepository.getInstance().getCourseById(courseId);
            courses.add(course);
        }
        return courses;
    }

    public List<Student> getAllStudentEnrollment(int courseId, int year) throws SQLException, ParseException {
        final ResultSet result = MySqlUtils.getInstance().query("select * from student_enrollment where courseId= " + courseId + " and year = "+ year + ";");

        List<Student> students = new ArrayList<>();

        while (result.next()) {
            final Student student = StudentRepository.getInstance().getStudentById(result.getInt("studentId"));
            students.add(student);
        }

        return students;
    }

    public Course getCourseById(int id) throws SQLException {
        final ResultSet result = MySqlUtils.getInstance().query("select * from course where idCourse = " + id + ";");
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        if (result.next()) {
            Map<String, Object> mapData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                mapData.put(resultSetMetaData.getColumnLabel(i), result.getObject(i));
            }

            return new Course(mapData);
        }
        return null;
    }
}
