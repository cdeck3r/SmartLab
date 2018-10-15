package com.example.fabiosprotte.einkaufslistengenerator;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Created by Fabio Sprotte on 08.05.2018.
 */
public class Control_Klasse {

    public static int kunden_id = 1;

    public static double unter_grenze_gelb = 50;
    public static double unter_grenze_rot = 90;

    public static boolean einkaufsliste = false;

    static ArrayList<Produkt> produktArrayList = new ArrayList<>();
    static ArrayList<Produkt> warenkorb = new ArrayList<>();
    static ArrayList<Produkt> einkaufsliste_Array = new ArrayList<>();

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
