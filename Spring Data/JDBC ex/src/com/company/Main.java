package com.company;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    // Enter your credentials. Enter ex number. Await response.
    private static final Scanner scanner = new Scanner(System.in);
    private static Connection connection;
    public static void main(String[] args) throws SQLException {

        connection = authenticate();
        System.out.println("Enter exercise:");
        int exN = Integer.parseInt(scanner.nextLine());

            switch (exN) {
                case 2: secondEx(); break;
                case 3: thirdEx(); break;
                case 5: fifthEx(); break;
                case 7: seventhEx(); break;
                case 8: eightEx(); break;


                default:
                    System.out.println("This exercise doesn't exist. Please try another one.");
            }

    }

    private static void seventhEx() throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT `name` FROM minions;");
        ResultSet resultSet = preparedStatement.executeQuery();
        List<String> names = new ArrayList<>();
        while (resultSet.next()) {
            names.add(resultSet.getString(1));
        }
        for (int i = 0; i < names.size()/2; i++) {
            System.out.println(names.get(i));
            System.out.println(names.get(names.size() - 1 - i));

        }

    }

    private static void eightEx() throws SQLException {
        System.out.println("Enter ids");
        int[] tokens = Arrays.stream(scanner.nextLine().split("\\s+")).mapToInt(Integer::parseInt).toArray();
        PreparedStatement preparedStatement = connection.prepareStatement(" UPDATE minions SET `name` = LOWER(`name`), `age` = `age`+1 WHERE `id` = ?;");
        for (int token : tokens) {
            preparedStatement.setInt(1, token);
            preparedStatement.executeUpdate();
        }

        PreparedStatement statement = connection.prepareStatement("SELECT `name`, age FROM minions;");
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            System.out.printf("%s %d%n", resultSet.getString(1), resultSet.getInt(2));
        }
    }

    private static void fifthEx() throws SQLException {
        System.out.println("Please enter country name:");
        String cname = scanner.nextLine();
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT name FROM towns WHERE country = ?;");
        preparedStatement.setString(1,cname);
        ResultSet resultSet = preparedStatement.executeQuery();

        if (!resultSet.next()) {
            System.out.println("No town names were affected");
            return;
        }

        PreparedStatement changeNames = connection.prepareStatement("UPDATE towns SET `name` = UPPER(`name`) WHERE country = ?");
        changeNames.setString(1,cname);
        int affectedRows = changeNames.executeUpdate();

        System.out.printf("%d towns were affected:%n", affectedRows);

        PreparedStatement namesStatement = connection.prepareStatement("SELECT name FROM towns WHERE country = ?;");
        namesStatement.setString(1,cname);
        ResultSet names = preparedStatement.executeQuery();

        while (names.next()) {
            System.out.println(names.getString("name"));
        }

    }

    private static void thirdEx() throws SQLException {
        System.out.println("Please enter villain id:");
        int vid = Integer.parseInt(scanner.nextLine());

        PreparedStatement st2 = connection.prepareStatement("SELECT `name` FROM villains\n" +
                "WHERE id = ?;");

        st2.setInt(1,vid);


        ResultSet rs2 = st2.executeQuery();

        PreparedStatement statement = connection.prepareStatement("SELECT m.`name`, m.age FROM minions AS m\n" +
                "JOIN minions_villains mv on m.id = mv.minion_id\n" +
                "WHERE villain_id = ?;");

        statement.setInt(1, vid);
        ResultSet rs = statement.executeQuery();


        if(rs2.next()) {
            System.out.printf("Villain: %s%n", rs2.getString("name"));

            int counter = 1;

            while (rs.next()) {
                System.out.printf("%d. %s %d%n", counter++, rs.getString("name"), rs.getInt("age"));
        }
        return;
        }
        System.out.println("No such villain in db");
        thirdEx();
    }

    private static Connection authenticate() throws SQLException {
        System.out.println("Please enter username");
        String user = scanner.nextLine();
        System.out.println("Please enter password");
        String pass = scanner.nextLine();
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/minions_db", user, pass);
    }

    private static void secondEx() throws SQLException {

        PreparedStatement statement = connection.prepareStatement("SELECT v.name, COUNT(DISTINCT mv.minion_id) AS count FROM villains AS v\n" +
                "JOIN minions_villains AS mv on v.id = mv.villain_id\n" +
                "GROUP BY v.id\n" +
                "HAVING count > 15\n" +
                "ORDER BY count DESC;");
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            System.out.printf("%s %d", resultSet.getString("name"), resultSet.getInt(2));

        }
    }
}
