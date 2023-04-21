package Repository;

import AppDatabase.MySqlUtils;
import Models.Course;
import Models.CourseStudentGrade;
import Models.Student;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {

    private static final StudentRepository instance = new StudentRepository();

    public static StudentRepository getInstance(){
        return instance;
    }

    private StudentRepository() {}

    public List<Student> getAllStudent() throws SQLException, ParseException {
        final ResultSet result = MySqlUtils.getInstance().query("select* from student;");
        List<Student> students = new ArrayList<>();
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        while (result.next()) {
            Map<String,Object> mapData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                if (i==4){
                    mapData.put(resultSetMetaData.getColumnLabel(i), result.getDate(i));
                }
                else {
                    mapData.put(resultSetMetaData.getColumnLabel(i), result.getObject(resultSetMetaData.getColumnLabel(i)));
                }
            }
            students.add(new Student(mapData));
        }
        return students;
    }

    public Student getStudentById(int studentId) throws SQLException, ParseException {
        final ResultSet result = MySqlUtils.getInstance().query("select * from student where id = " + studentId + ";");
        ResultSetMetaData resultSetMetaData = result.getMetaData();
        int columnCount = resultSetMetaData.getColumnCount();

        Student student = null;

        while (result.next()) {
            Map<String,Object> mapData = new HashMap<>();
            for (int i = 1; i <= columnCount; i++) {
                if (i==4){
                    mapData.put(resultSetMetaData.getColumnLabel(i), result.getDate(i));
                }
                else {
                    mapData.put(resultSetMetaData.getColumnLabel(i), result.getObject(resultSetMetaData.getColumnLabel(i)));
                }
            }
            student = new Student(mapData);
        }
        return student;
    }

    public List<CourseStudentGrade> getAllStudentGradeByYear(int id,int year) throws SQLException, ParseException {
        final ResultSet result = MySqlUtils.getInstance().query(
                "select courseId,grade from course_grade where studentId = "
                + id + " and year = " + year + ";");

        final List<CourseStudentGrade> listGrade = new ArrayList<>();

        while (result.next()) {
            int courseId = result.getInt("courseId");
            double grade = result.getDouble("grade");
            final Course course = CourseRepository.getInstance().getCourseById(courseId);
            final Student student = getStudentById(id);
            listGrade.add(new CourseStudentGrade(student,course,grade));
        }
        return listGrade;
    }


}
