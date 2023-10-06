package org.example;

import java.util.*;

public class Program {
    private final DBCommunicator dbCommunicatorEDB = new DBCommunicator();
    private final DBCommunicator dbCommunicatorUDB = new DBCommunicator();

    public void run() {
        startMenu();
    }

    private void startMenu() {
        System.out.println("1. Log in");
        System.out.println("2. See overall program");
        System.out.println("3. Exit");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        switch (input) {
            case "1" -> {
                dbCommunicatorUDB.getStudent();
                logIn();
            }
            case "2" -> printProgram();

            case "3" -> {System.out.println("Exit now");
            System.exit(0);}
            default -> System.out.println("Wrong input");
        }
    }

    private String logIn() {
        Map<String, String> student = dbCommunicatorUDB.getLogInStudent();
        System.out.println("Write in your name");
        Scanner scanner = new Scanner(System.in);
        String inputName = scanner.nextLine();
        if (student.containsKey(inputName)) {
            System.out.println("You have written correct name");
            System.out.println("Write your password");
            String inputPassword = scanner.nextLine();
            if (inputPassword.equals(student.get(inputName))) {
                System.out.println("That was correct pasword! Welcome!");
                mainManu(inputName);
                return inputName;
            } else {
                System.out.println("I'm so sorry, that was not correct password");
                startMenu();
            }
        } else {
            System.out.println("I'm so sorry, that was not correct student name");
            System.out.println("This is your menu");
            startMenu();
        }

        return null;
    }

    private void mainManu(String student) {
        System.out.println("You are logged in");
        System.out.println("Choose your activity:");
        System.out.println("A. Register or edit registration");
        System.out.println("B. See all participants");
        System.out.println("C. See all participants from your program");
        System.out.println("D. Search for participants");
        System.out.println("E. See overall program");
        System.out.println("F. Exit");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "A" -> regOrNot(student);
            case "B" -> seeAllParticipants(student);
            case "C" -> seeAllParticipantsInYourProgram(student);
            case "D" -> searchForParticipant(student);
            case "E" -> printProgram();
            case "F" -> {System.out.println("Bye and welcome back!");
            System.exit(0);}

        }
    }

    private void regOrNot(String student) {
        System.out.println("1. Would you like to sign up?");
        System.out.println("2. Edit your registration?");
        System.out.println("3. Go back to main menu");

        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        switch (input) {
            case "1" -> checkRegister(student);
            case "2" -> editReg(student);
            case "3" -> mainManu(student);
        }
    }

    private void checkRegister(String student) {
        List<Participants> participants = dbCommunicatorEDB.getParticipants();
        boolean isRegistered = false;
        for (Participants name : participants) {
            if (student.equals(name.getStudentName())) {
                isRegistered = true;
                break;
            }
        }
        if (isRegistered) {
            System.out.println("You are already registered");
            regOrNot(student);
        } else {
            regStudent(student);
        }

    }

    private void regStudent(String student) {
        dbCommunicatorUDB.registerStudent(student);
        System.out.println("You are registered " + student);
        mainManu(student);
    }

    private void unRegStudent(String student) {
        dbCommunicatorUDB.unRegisterStudent(student);
        System.out.println("You are unregistered " + student);
        mainManu(student);
    }

    private void editReg(String student) {
        System.out.println("A.Would you like to unregister yourself?");
        System.out.println("B.Do you want to register guest?");
        System.out.println("C.Do you want to unregister guest?");
        System.out.println("D.Go back to main menu");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine().toUpperCase();
        switch (input) {
            case "A" -> unRegStudent(student);
            case "B" -> regGuest(student);
            case "C" -> unRegGuest(student);
            case "D" -> mainManu(student);

        }


    }

    private void unRegGuest(String student) {
        System.out.println("Who do you want to unregister as your guest?");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        dbCommunicatorUDB.unRegisterGuest(student, input);
        System.out.println("You did unregistered " + input);
        mainManu(student);
    }

    private void regGuest(String student) {
        System.out.println("How many people do you want to register? (max4)");
        Scanner scannerInt = new Scanner(System.in);
        int intInput = scannerInt.nextInt();
        if (intInput <= 4) {
            System.out.println("Who do you want to register as your guest?");
            Scanner scannerString = new Scanner(System.in);
            String input = scannerString.nextLine();
            dbCommunicatorUDB.registerGuest(student, input);
            System.out.println("You did registered " + input);
            mainManu(student);
        } else {
            System.out.println("That is too many!");
            regGuest(student);
        }

    }

    private void seeAllParticipants(String student) {
        List<Participants> participants = dbCommunicatorEDB.getParticipants();
        for (Participants participant : participants) {
            System.out.println(participant);
        }
        mainManu(student);
    }

    private void seeAllParticipantsInYourProgram(String student) {
        List<Participants> participants = dbCommunicatorEDB.getParticipantsWithJoin(student);

        for (Participants participant : participants) {
            System.out.println(participant);
        }
        mainManu(student);

    }
    private void searchForParticipant(String student) {
        System.out.println("Who do you want to find");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        dbCommunicatorEDB.getParticipantsFromInput(student,input);

    }
    private void printProgram(){
        System.out.println("The program:");
        System.out.println("Start time: 13:00");
        System.out.println("Introdution: 30 minutes");
        List<StudyProgram> studyPrograms = dbCommunicatorEDB.getStudyProgram();
        for (StudyProgram program: studyPrograms) {
            System.out.println(program + "Speech – 1 minute.");
        }
        System.out.println("It will be 5 min break between each program");
        System.out.println("Then it is open for someone too talk.");
        System.out.println("org.example.Program end.");

    }
}
