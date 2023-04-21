import AppDatabase.MySqlUtils;
import Models.Course;
import Models.Student;
import Repository.CourseRepository;
import Service.CourseService;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import java.text.ParseException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException, ParseException {
//        for (Course course : CourseRepository.getInstance().getAllCourseByStudentId(1)) {
//            System.out.println(course.getIdCourse());
//            System.out.println(course.getName());
//            System.out.println(course.getYear());
//        }

        CourseService.getInstance().editCourse(new Course(4,"BA4+","test2",2023,"testNotes"));

    }
}
