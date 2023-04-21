package Models;


import java.text.ParseException;
import java.time.*;
import java.util.Map;

public class Student {
     final String id;
     String name;
     double grade;
     LocalDate birthday;
     String address;
     String notes;

    public Student(String id, String name, double grade, LocalDate birthday, String address, String notes) {
        this.id = id;
        this.name = name;
        this.grade = grade;
        this.birthday = birthday;
        this.address = address;
        this.notes = notes;
    }

    public Student(Map<String, Object> mapData) throws ParseException {
        this.id = String.valueOf(mapData.get("id"));
        this.name = String.valueOf(mapData.get("name"));
        this.grade = Double.parseDouble(mapData.get("grade").toString());
        this.birthday = LocalDate.parse(mapData.get("birthday").toString());
        this.birthday = LocalDate.parse(mapData.get("birthday").toString());
        this.address = String.valueOf(mapData.get("address"));
        this.notes = String.valueOf(mapData.get("notes"));
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

}
