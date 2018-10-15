package com.example.fabiosprotte.kassen_system;

import android.widget.Button;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Fabio Sprotte on 23.08.2018.
 */
public class Control_Klasse {

    public static int kunden_id;
    public static boolean kunde_registriert;
    static ArrayList<Produkt> produktArrayList = new ArrayList<>();
    static ArrayList<String> temp_Kasse_Produkte = new ArrayList<>();
    static GridListAdapter adapter;
    public static ArrayList<Button> dynamicButtons;


    public static Connection getDBVerbindung() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (ClassNotFoundException e) {
            System.out.println("Error: unable to load driver class!");
            System.exit(1);
        }

        try {
            //connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/einkaufslistengenerator", "root", "Thierry");
            connection = DriverManager.getConnection("jdbc:mysql://95.179.151.124:3306/smartlab", "smartlab", "Sm4rtL4b2018!");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }
}
