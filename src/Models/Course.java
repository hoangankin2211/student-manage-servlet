package Models;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Map;

public class Course {
    final int idCourse;
     String name;
    String lecture;
    int year;
    String notes;

    public Course(int idCourse, String name, String lecture, int year, String notes) {
        this.idCourse = idCourse;
        this.name = name;
        this.lecture = lecture;
        this.year = year;
        this.notes = notes;
    }

    public Course(Map<String, Object> mapData) {
        this.idCourse = Integer.parseInt(mapData.get("idCourse").toString());
        this.name = String.valueOf(mapData.get("name"));
        this.lecture = mapData.get("lecture").toString();
        this.year = Integer.parseInt(mapData.get("year").toString());
        this.notes = String.valueOf(mapData.get("notes"));
    }


    public int getIdCourse() {
        return idCourse;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecture() {
        return lecture;
    }

    public void setLecture(String lecture) {
        this.lecture = lecture;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
