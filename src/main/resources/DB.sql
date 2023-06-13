DROP SCHEMA IF EXISTS `DB`;
create schema `DB`;
CREATE USER 'Sensor'@'localhost' IDENTIFIED BY 'HeiPåDeg';

GRANT select on DB* TO 'Sensor'@'localhost';

FLUSH PRIVILEGES;

    -- Connection
public static Connection getConnection() {
        String url = "jdbc:mysql://localhost:3306/BookDB";
        String username = "Sensor";
        String password = "HeiPåDeg";

        try {
            System.out.println("Database connectiom is made" + url);
return DriverManager.getConnection(url, username, password);
} catch (SQLException e){
            throw new RuntimeException(e);
}

    }
