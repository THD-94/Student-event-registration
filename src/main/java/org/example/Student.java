package org.example;

public class Student extends StudentPerson {
    private String program;
    private String password;

    public Student(String name) {
        super(name);

    }

    public Student(String name, String program){
        super(name);
        this.program = program;
    }


    public Student(String name,String password, String program) {
        super(name);
        this.password = password;
        this.program = program;


    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }

    public String getPassword() {
        return password;
    }


    @Override
    public String toString() {
        return "org.example.Student{" +
                "name='" + super.getStudentName() + '\'' +
                ", program='" + program + '\'' +
                '}';
    }
}
