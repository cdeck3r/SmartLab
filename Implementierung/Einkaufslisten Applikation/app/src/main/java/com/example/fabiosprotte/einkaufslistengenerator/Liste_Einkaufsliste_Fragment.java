package com.example.fabiosprotte.einkaufslistengenerator;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class Liste_Einkaufsliste_Fragment extends Fragment {

    private Context context;
    private GridListAdapter adapter;
    private ArrayList<Produkt> arrayList;
    private Fill_Einkaufsliste_Liste fill_einkaufsliste_liste;
    private String status;
    private String product;

    public Liste_Einkaufsliste_Fragment() {

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Control_Klasse.einkaufsliste = true;

        try
        {
            fillArray();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_liste__einkaufsliste_, container, false);
    }

    private void fillArray() throws ExecutionException, InterruptedException {
        fill_einkaufsliste_liste = new Fill_Einkaufsliste_Liste();
        status = fill_einkaufsliste_liste.execute().get();
    }

    @Override
    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(status == "READY")
        {
            loadListView(view);
        }

        onClickEvent(view);
    }

    private void loadListView(View view) {
        ListView listView = (ListView) view.findViewById(R.id.listView);
        adapter = new GridListAdapter(context, Control_Klasse.einkaufsliste_Array, true);
        listView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        Control_Klasse.einkaufsliste_Array.clear();
    }

    private void onClickEvent(View view) {
        view.findViewById(R.id.show_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the selected position
                product = adapter.deleteSelectedPosition();

                if(product == "")
                {
                    new AlertDialog.Builder(context).setTitle("Meldung")
                            .setMessage("Es wurde kein Produkt ausgewählt !!")
                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    // Do nothing
                                }
                            }).show();
                }
                else
                {
                    Buy_Product_In_Einkaufsliste buy_product_in_einkaufsliste = new Buy_Product_In_Einkaufsliste();
                    buy_product_in_einkaufsliste.execute();
                }
            }
        });
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the selected position
                new AlertDialog.Builder(context).setTitle("Meldung")
                        .setMessage("Produkt aus der Einkaufsliste entfernen ?")
                        .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                product = adapter.deleteSelectedPosition();

                                if(product == "")
                                {
                                    new AlertDialog.Builder(context).setTitle("Meldung")
                                            .setMessage("Es wurde kein Produkt ausgewählt !!")
                                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Do nothing
                                                }
                                            }).show();
                                }
                                else
                                {
                                    Delete_Product_In_Einkaufsliste delete_product_in_einkaufsliste = new Delete_Product_In_Einkaufsliste();
                                    delete_product_in_einkaufsliste.execute();
                                }

                            }
                        })
                        .setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do Nothing
                            }
                        })
                        .show();
            }
        });
    }

    class Fill_Einkaufsliste_Liste extends AsyncTask<String, String, String>
    {
        private Connection connection;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;

        @Override
        protected String doInBackground(String... params) {

            connection = Control_Klasse.getDBVerbindung();

            if (connection != null)
            {
                System.out.println("verbindung stshet");
            }

            try
            {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                result = statement.executeQuery("SELECT produkt_id FROM temp_einkaufsliste WHERE kunden_id = " + Control_Klasse.kunden_id);
                result.last();
                int anzahl = result.getRow();

                result.first();

                for(int j = 0; j < anzahl; j++)
                {
                    for(int i = 0; i < Control_Klasse.warenkorb.size(); i++)
                    {
                        if(Control_Klasse.warenkorb.get(i).getProdukt_id() == result.getInt("produkt_id"))
                        {
                            Control_Klasse.einkaufsliste_Array.add(Control_Klasse.warenkorb.get(i));
                        }
                    }

                    result.next();
                }

                //connection.commit();
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return "READY";
        }
    }

    class Buy_Product_In_Einkaufsliste extends AsyncTask<String, String, String>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;

        Calendar c = Calendar.getInstance();
        Date kauf_date;
        String fdate;

        @Override
        protected String doInBackground(String... params) {

            kauf_date = c.getTime();
            fdate = new SimpleDateFormat("yyyy-MM-dd").format(kauf_date);

            connection = Control_Klasse.getDBVerbindung();

            if (connection != null)
            {
                System.out.println("verbindung stshet");
            }

            try
            {

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
                result.first();
                int product_id = result.getInt("produkt_id");

                result = statement.executeQuery("SELECT verbrauch_datum FROM einkaufs_historie WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);
                result.last();

                if(result.getRow() == 0)
                {
                    result = statement.executeQuery("SELECT max(einkauf_id) AS einkauf_id FROM einkaufs_historie");
                    result.first();
                    int einkauf_id = result.getInt("einkauf_id");

                    if(einkauf_id == 0)
                    {
                        einkauf_id = 1;
                    }
                    else
                    {
                        einkauf_id++;
                    }

                    statement = connection.createStatement();

                    statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, kauf_datum)" +
                            "VALUES (" + einkauf_id + ", " + Control_Klasse.kunden_id + ", " + product_id + ", '" + fdate + "')");
                }
                else
                {
                    /*result.first();
                    String datum = result.getString("verbrauch_datum");
                    //String datum = "2018-05-01";
                    Date verbrauch_date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
                    long diff = kauf_date.getTime() - verbrauch_date.getTime();
                    diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                    result = statement.executeQuery("SELECT max(ID) AS ID FROM produkt_verbrauchsstatistik");
                    result.first();
                    int id = result.getInt("ID") + 1;

                    // INsert der Differenz der Tage
                    statement = connection.createStatement();

                    statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)" +
                            "VALUES (" + id + ", " + Control_Klasse.kunden_id + ", " + product_id + ", " + diff + ")");*/

                    //Update des Kaufdatums eines Produkts in der Historie


                    statement = connection.createStatement();

                    statement.executeUpdate("UPDATE einkaufs_historie " +
                            "SET kauf_datum = '" + fdate + "' " +
                            "WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);
                }

                statement = connection.createStatement();

                statement.executeUpdate("DELETE FROM temp_einkaufsliste " +
                        "WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);

                //connection.commit();
                connection.close();

                return null;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return "READY";
        }
    }

    class Delete_Product_In_Einkaufsliste extends AsyncTask<String, String, Void> {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;


        @Override
        protected Void doInBackground(String... params) {

            connection = Control_Klasse.getDBVerbindung();

            if (connection != null)
            {
                System.out.println("verbindung stshet");
            }

            try
            {

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
                result.first();
                int product_id = result.getInt("produkt_id");

                statement = connection.createStatement();

                statement.executeUpdate("DELETE FROM temp_einkaufsliste " +
                        "WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);

                //connection.commit();
                connection.close();

                return null;
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }

}
