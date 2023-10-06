package org.example;

public class Main {
    public static void main(String[] args) {
        DBCommunicator.getConnectionEDB();
        DBCommunicator.getConnectionUDB();

        Program program = new Program();
        program.run();

    }
}