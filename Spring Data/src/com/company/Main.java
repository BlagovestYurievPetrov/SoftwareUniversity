package com.company;

import java.sql.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException {
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/soft_uni");

        PreparedStatement statement = connection.prepareStatement("SELECT * FROM employees WHERE salary > ?");
        Scanner sc = new Scanner(System.in);
        double salary = Double.parseDouble(sc.nextLine());
        statement.setDouble(1, salary);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            String jobTitle = resultSet.getString(5);
            System.out.println(jobTitle);

        }
    }
}
