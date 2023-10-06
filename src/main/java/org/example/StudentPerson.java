package org.example;

public abstract class StudentPerson {
    private String StudentName;

    public StudentPerson(String studentName) {
        StudentName = studentName;
    }

    public String getStudentName() {
        return StudentName;
    }

    public void setStudentName(String studentName) {
        StudentName = studentName;
    }

    @Override
    public String toString() {
        return "org.example.StudentPerson{" +
                "StudentName='" + StudentName + '\'' +
                '}';
    }
}
