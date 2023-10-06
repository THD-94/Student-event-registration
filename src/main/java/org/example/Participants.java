package org.example;

public class Participants extends StudentPerson {
    private int id;
    private String staffName;
    private String guestName;
    private Student student;


    public Participants(String guestName, String staffName, String studentName) {
        super(studentName);
        this.staffName = staffName;
        this.guestName = guestName;
    }

    public Participants(String studentName) {
        super(studentName);
    }

    public Participants(int id, String guestName, String pStudentName, String sProgram) {
        super(pStudentName);
        student = new Student(pStudentName);
        this.id = id;
        this.guestName = guestName;
        student.setProgram(sProgram);
    }
    public String getProgram(){
        String program = student.getProgram();
        return program;


    }

    public String getStaffName() {
        return staffName;
    }

    public void setStaffName(String staffName) {
        this.staffName = staffName;
    }

    public String getGuestName() {
        return guestName;
    }

    public void setGuestName(String guestName) {
        this.guestName = guestName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "org.example.Participants{" +
                ", staffName='" + staffName + '\'' +
                ", StudentName='" + super.getStudentName() + '\'' +
                ", guestName=" + guestName +
                '}';
    }
}
