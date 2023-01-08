package com.maxis.javadb;

import java.sql.*;

public class Main {

    public static void main(String[] args) {
        try (
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/flowers",
                        "flower-user",
                        "flower-user123"))
        {


            PreparedStatement vlozeni = con.prepareStatement("INSERT INTO flowerstable (name, color, description, poisonous) VALUES (?, ?, ?, ? )");
            vlozeni.setString(1,"Bledule");
            vlozeni.setString(2,"Bílá");
            vlozeni.setString(3,"pozor");
            vlozeni.setString(4,"1");
            vlozeni.executeUpdate();

            vlozeni.setString(1,"Kopretina");
            vlozeni.setString(2,"Bílá");
            vlozeni.setString(3,"luční květina");
            vlozeni.setString(4,"0");
            vlozeni.executeUpdate();

            PreparedStatement update = con.prepareStatement("UPDATE flowerstable SET description =? WHERE description =? ");
            update.setString(1,"'Pozor na cibulku - obsahuje největší koncentraci jedu!");
            update.setString(2,"pozor");
            update.executeUpdate();

            PreparedStatement delete = con.prepareStatement("DELETE FROM flowerstable WHERE poisonous =? ");
            delete.setString(1,"0");
            delete.executeUpdate();

            PreparedStatement statement = con.prepareStatement("SELECT * FROM flowerstable");
            ResultSet vysledky = statement.executeQuery();

            while (vysledky.next()){
                String name = vysledky.getString("name");
                String color = vysledky.getString("color");
                String description = vysledky.getString("description");
                int poisonous = vysledky.getInt("poisonous");
                System.out.println(name+ ", "+ color+", "+description + ", " +poisonous +"." );
            }


        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}