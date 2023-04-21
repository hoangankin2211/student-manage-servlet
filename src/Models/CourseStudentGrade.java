package Models;

public class CourseStudentGrade {
    final Student student;
    final Course course;
    final double grade;

    public CourseStudentGrade(Student student, Course course, double grade) {
        this.student = student;
        this.course = course;
        this.grade = grade;
    }
}
