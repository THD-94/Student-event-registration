package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBCommunicator {
    public static Connection getConnectionUDB() {
        String url = "jdbc:mysql://localhost:3306/UniversityDB";
        String username = "root";
        String password = "Kristiania2022";

        try {
            System.out.println("Database connectiom is made" + url);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnectionEDB() {
        String url = "jdbc:mysql://localhost:3306/EventDB";
        String username = "root";
        String password = "Kristiania2022";

        try {
            System.out.println("Database connectiom is made" + url);
            return DriverManager.getConnection(url, username, password);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Map<String, String> getLogInStudent() {
        try (Connection connection = getConnectionUDB()) {
            String query = "Select * from Student";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            HashMap<String, String> students = new HashMap<>();
            while (resultSet.next()) {
                String name = resultSet.getString("StudentName");
                String password = resultSet.getString("Password");
                students.put(name, password);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Student> getStudent() {
        try (Connection connection = getConnectionUDB()) {
            String query = "Select * from Student";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Student> students = new ArrayList<>();
            while (resultSet.next()) {
                String name = resultSet.getString("StudentName");
                String password = resultSet.getString("Password");
                String program = resultSet.getString("Program");
                Student student = new Student(name, password, program);
                students.add(student);
            }
            return students;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Participants> getParticipants() {
        try (Connection connection = getConnectionEDB()) {
            String query = "select * from Participants";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Participants> participants = new ArrayList<>();
            while (resultSet.next()) {
                String guestName = resultSet.getString("GuestName");
                String staffName = resultSet.getString("StaffName");
                String studentName = resultSet.getString("StudentName");
                Participants participant = new Participants(guestName, staffName, studentName);
                participants.add(participant);
            }
            return participants;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void getParticipantsFromInput(String student, String input) {
        try (Connection connection = getConnectionEDB()) {
            String query = "select * from Participants where GuestName =? or StaffName = ? or StudentName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, input);
            statement.setString(2, input);
            statement.setString(3,input);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                String guestName = resultSet.getString("GuestName");
                String staffName = resultSet.getString("StaffName");
                String studentName = resultSet.getString("StudentName");
                Participants participant = new Participants(guestName, staffName, studentName);
                System.out.println(participant);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public void registerStudent(String student) {
        try (Connection connection = getConnectionEDB()) {
            String query = "INSERT INTO Participants (GuestName,StaffName, StudentName) VALUES (null,null,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void unRegisterStudent(String student) {
        try (Connection connection = getConnectionEDB()) {
            String query = "DELETE FROM Participants where StudentName = ? and GuestName = null";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, student);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void registerGuest(String student, String guest) {
        try (Connection connection = getConnectionEDB()) {
            String query = "INSERT INTO Participants (GuestName, StaffName, StudentName) VALUES (?,null,?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, guest);
            statement.setString(2, student);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void unRegisterGuest(String student, String guest) {
        try (Connection connection = getConnectionEDB()) {
            String query = "DELETE FROM Participants where GuestName = ? and StudentName = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, guest);
            statement.setString(2, student);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

        public List<Participants> getParticipantsWithJoin(String student) {
        try (Connection connection = getConnectionEDB()) {
            String query = "select Participants.*, Student.*\n" +
                    "from EventDB.Participants join UniversityDB.Student\n" +
                    "    on Participants.StudentName = Student.StudentName\n";
            /*Dataofish(12.feb.2022) Join Tables from Different Databases in SQL Server. Hentet 15.jun.
            https://datatofish.com/join-tables-different-databases-sql-server/
             */
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<Participants> participants = new ArrayList<>();
            while (resultSet.next()) {
                int pID = resultSet.getInt("ParticipantsID");
                String guestName = resultSet.getString("GuestName");
                String pStudentName = resultSet.getString("StudentName");
                String sProgram = resultSet.getString("Program");
                Participants participant = new Participants(pID, guestName, pStudentName, sProgram);
                participants.add(participant);
                //getParticipantsInYourProgram(student);

            }
            return participants;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /*public List<Participants> getParticipantsInYourProgram(String student) {
        Student logdInStudent = new Student(student);
        try (Connection connection = getConnectionEDB()) {
            String query = "select Participants.*, Student.*\n" +
                    "from EventDB.Participants join UniversityDB.Student\n" +
                    "    on Participants.StudentName = Student.StudentName\n" +
                    "where Program = ?";*/
           /* PreparedStatement statement1 = connection.prepareStatement(query);
            statement1.setString(1, logdInStudent.getProgram());
            Statement statement2 = connection.createStatement();
            ResultSet resultSet = statement2.executeQuery(query);
            List<Participants> participants = new ArrayList<>();
            while (resultSet.next()) {
                int pID = resultSet.getInt("ParticipantsID");
                String guestName = resultSet.getString("GuestName");
                String pStudentName = resultSet.getString("Participants.StudentName");
                String sProgram = resultSet.getString("Program");
                Participants participant = new Participants(pID, guestName, pStudentName, sProgram);
                participants.add(participant);
                statement1.executeUpdate();

            }
            return null;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }*/
    public List<StudyProgram> getStudyProgram(){
        try (Connection connection = getConnectionUDB()) {
            String query = "Select * from StudyProgram";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);
            List<StudyProgram> studyPrograms = new ArrayList<>();
            while (resultSet.next()) {
                String programName = resultSet.getString("ProgramName");
                String responsibility = resultSet.getString("Responsibility");
                StudyProgram studyProgram = new StudyProgram(programName, responsibility);
                studyPrograms.add(studyProgram);
            }
            return studyPrograms;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    }

