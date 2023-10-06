package org.example;

public class StudyProgram {
    private String programName;
    private String Responsibility;

    public StudyProgram(String programName, String responsibility) {
        this.programName = programName;
        Responsibility = responsibility;
    }

    public String getProgramName() {
        return programName;
    }

    public String getResponsibility() {
        return Responsibility;
    }


    @Override
    public String toString() {
        return "org.example.StudyProgram{" +
                "programName='" + programName + '\'' +
                ", Responsibility='" + Responsibility + '\'' +
                '}';
    }
}
