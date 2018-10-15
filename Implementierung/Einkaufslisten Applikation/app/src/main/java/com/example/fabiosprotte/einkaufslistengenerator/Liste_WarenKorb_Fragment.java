package com.example.fabiosprotte.einkaufslistengenerator;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

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


public class Liste_WarenKorb_Fragment extends Fragment {

    private Context context;
    private GridListAdapter adapter;
    private ArrayList<String> arrayList;
    private ListView listView;
    private Fill_Warenkorb_Liste fill_warenkorb_liste;
    private String status;
    private String product;


    public Liste_WarenKorb_Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        Control_Klasse.einkaufsliste = false;

        // Inflate the layout for this fragment
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

        return inflater.inflate(R.layout.fragment_liste__waren_korb_, container, false);
    }


    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if(status == "READY")
        {
            loadListView(view);
        }

        onClickEvent(view);
    }

    private void fillArray() throws ExecutionException, InterruptedException {
        String s = "ja";
        fill_warenkorb_liste = new Fill_Warenkorb_Liste();
        status = fill_warenkorb_liste.execute(s).get();
    }

    private void loadListView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        adapter = new GridListAdapter(context, Control_Klasse.warenkorb, true);
        listView.setAdapter(adapter);
    }

    @Override
    public void onPause() {
        super.onPause();
        //Control_Klasse.warenkorb.clear();
    }

    private void onClickEvent(final View view) {
        view.findViewById(R.id.show_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Get the selected position
                product = adapter.getSelectedItem();

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
                    Fill_Einkaufsliste fill_einkaufsliste = new Fill_Einkaufsliste();
                    try
                    {
                        String s = fill_einkaufsliste.execute().get();

                        if(s == "FALSE")
                        {
                            new AlertDialog.Builder(context).setTitle("Meldung")
                                    .setMessage("Produkt ist schon in der Einkaufsliste !!")
                                    .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            // Do nothing
                                        }
                                    }).show();
                        }

                    }
                    catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                    catch (ExecutionException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        });
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Delete the selected position

                new AlertDialog.Builder(context).setTitle("Meldung")
                        .setMessage("Produkt wirklich aus dem Warenkorb entfernen ?")
                        .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                product = adapter.deleteSelectedPosition();

                                if (product == "") {
                                    new AlertDialog.Builder(context).setTitle("Meldung")
                                            .setMessage("Es wurde kein Produkt ausgewählt !!")
                                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Do nothing
                                                }
                                            }).show();
                                } else {
                                    Delete_Product_In_Warenkorb delete_product_in_warenkorb = new Delete_Product_In_Warenkorb();
                                    delete_product_in_warenkorb.execute();
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
        view.findViewById(R.id.new_product_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
                alertDialog.setTitle("Neues Produkt");
                alertDialog.setMessage("Bitte Produktbezeichnung eingeben !");

                LinearLayout layout = new LinearLayout(context);
                layout.setOrientation(LinearLayout.VERTICAL);

                final TextView textView = new TextView(context);
                layout.addView(textView);

                final EditText produkt_bezeichnung = new EditText(context);
                produkt_bezeichnung.setHint("Produktbezeichnung");
                layout.addView(produkt_bezeichnung);

                alertDialog.setView(layout);

                alertDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        new AlertDialog.Builder(context).setTitle("Abfrage")
                                .setMessage("Wollen sie dieses Produkt ihren Warenkorb hinzufügen ?")
                                .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {

                                        product = produkt_bezeichnung.getText().toString();

                                        New_Product_In_Warenkorb new_product_in_warenkorb = new New_Product_In_Warenkorb();

                                        try {
                                            status = new_product_in_warenkorb.execute().get();

                                            if (status == "READY") {
                                                adapter.insertNewPosition(product);
                                            } else {
                                                new AlertDialog.Builder(context).setTitle("Meldung")
                                                        .setMessage("Produkt existiert nicht, ist schon im Warenkorb oder keine DBVerbindung")
                                                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                            @Override
                                                            public void onClick(DialogInterface dialog, int which) {
                                                                // Do nothing
                                                            }
                                                        }).show();
                                            }
                                        } catch (InterruptedException e) {
                                            e.printStackTrace();
                                        } catch (ExecutionException e) {
                                            e.printStackTrace();
                                        }
                                    }
                                })
                                .setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // Do nothing
                                    }
                                }).show();
                    }
                });
                alertDialog.setNegativeButton("Abbrechen", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alertDialog.show();
            }
        });
    }

    class New_Product_In_Warenkorb extends AsyncTask<String, String, String>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;

        @Override
        protected String doInBackground(String... params) {

            try
            {
                //connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/einkaufslistengenerator", "root", "Thierry");
                connection = Control_Klasse.getDBVerbindung();

                if (connection != null)
                {
                    System.out.println("verbindung stshet");
                }

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
                result.first();

                if(result.getRow() != 0)
                {
                    int product_id = result.getInt("produkt_id");

                    result = statement.executeQuery("SELECT * FROM standard_warenkorb WHERE kunden_id = " + Control_Klasse.kunden_id + " AND produkt_id = " + product_id);
                    result.first();

                    if(result.getRow() == 0)
                    {
                        result = statement.executeQuery("SELECT max(warenkorb_id) AS warenkorb_id FROM standard_warenkorb");
                        result.first();
                        int warenkorb_id = result.getInt("warenkorb_id");

                        if(warenkorb_id == 0)
                        {
                            warenkorb_id = 1;
                        }
                        else
                        {
                            warenkorb_id++;
                        }

                        statement = connection.createStatement();

                        statement.executeUpdate("INSERT INTO standard_warenkorb (kunden_id, produkt_id, warenkorb_id)" +
                                "VALUES (" + Control_Klasse.kunden_id + ", " + product_id + ", " + warenkorb_id + ")");
                    }
                    else
                    {
                        //connection.commit();
                        connection.close();
                        return "FALSE";
                    }

                }
                else
                {
                    //connection.commit();
                    connection.close();
                    return "FALSE";
                }

                //connection.commit();
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return "FALSE";
            }

            return "READY";
        }
    }

    class Fill_Warenkorb_Liste extends AsyncTask<String, String, String>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;

        @Override
        protected String doInBackground(String... params) {

            try
            {
                //connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/einkaufslistengenerator", "root", "Thierry");
                connection = Control_Klasse.getDBVerbindung();

                if (connection != null)
                {
                    System.out.println("verbindung stshet");
                }

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
                result = statement.executeQuery("SELECT produkt_id FROM standard_warenkorb WHERE kunden_id = " + Control_Klasse.kunden_id);
                result.last();
                int anzahl = result.getRow();

                result.first();

                ArrayList<Integer> integerArrayList = new ArrayList<>();
                for(int j = 0; j < anzahl; j++)
                {
                    integerArrayList.add(result.getInt("produkt_id"));
                    result.next();
                }

                arrayList = new ArrayList<>();
                for (int i = 0; i < integerArrayList.size(); i++)
                {
                    result = statement.executeQuery("SELECT Bezeichnung FROM produkt WHERE produkt_id = " + integerArrayList.get(i));
                    result.first();
                    arrayList.add(result.getString("Bezeichnung"));
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

    class Fill_Einkaufsliste extends AsyncTask<String, String, String>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;

        Calendar c = Calendar.getInstance();
        Date date;
        String fdate;

        @Override
        protected String doInBackground(String... params) {

            date = c.getTime();
            fdate = new SimpleDateFormat("yyyy-MM-dd").format(date);


            try
            {
                //connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/einkaufslistengenerator", "root", "Thierry");
                connection = Control_Klasse.getDBVerbindung();

                if (connection != null)
                {
                    System.out.println("verbindung stshet");
                }

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
                result.first();
                int product_id = result.getInt("produkt_id");

                //Einfügen in EInkaufshistorie
                result = statement.executeQuery("SELECT * FROM einkaufs_historie WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);
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

                    statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, verbrauch_datum)" +
                            "VALUES (" + einkauf_id + ", " + Control_Klasse.kunden_id + ", " + product_id + ", '" + fdate + "')");
                }
                else
                {
                    statement = connection.createStatement();

                    statement.executeUpdate("UPDATE einkaufs_historie " +
                            "SET verbrauch_datum = '" + fdate + "' " +
                            "WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);
                }

                // EInfügen in EInkaufsliste
                result = statement.executeQuery("SELECT * FROM temp_einkaufsliste WHERE kunden_id = " + Control_Klasse.kunden_id + " AND produkt_id = " + product_id);
                result.first();

                if(result.getRow() == 0)
                {
                    statement = connection.createStatement();

                    statement.executeUpdate("INSERT INTO temp_einkaufsliste (kunden_id, produkt_id) " +
                            "VALUES (" + Control_Klasse.kunden_id + ", " + product_id + ")");

                }
                else
                {
                    //connection.commit();
                    connection.close();
                    return "FALSE";
                }

                //Verbrauch in Tagen ausrechnen

                result = statement.executeQuery("SELECT kauf_datum FROM einkaufs_historie WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);
                result.last();

                if(result.getRow() == 0)
                {
                    /*result = statement.executeQuery("SELECT max(einkauf_id) AS einkauf_id FROM einkaufs_historie");
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

                    statement.executeUpdate("INSERT INTO einkaufs_historie (einkauf_id, kunden_id, produkt_id, verbrauch_datum)" +
                            "VALUES (" + einkauf_id + ", " + Control_Klasse.kunden_id + ", " + product_id + ", '" + fdate + "')");*/

                    // do nothing
                }
                else
                {
                    result.first();
                    String datum = result.getString("kauf_datum");
                    //String datum = "2018-05-01";
                    Date kauf_date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
                    long diff = date.getTime() - kauf_date.getTime();
                    diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);

                    result = statement.executeQuery("SELECT max(ID) AS ID FROM produkt_verbrauchsstatistik");
                    result.first();
                    int id = result.getInt("ID") + 1;

                    // INsert der Differenz der Tage
                    statement = connection.createStatement();

                    statement.executeUpdate("INSERT INTO produkt_verbrauchsstatistik (ID, kunden_id, produkt_id, verbrauch_in_tagen)" +
                            "VALUES (" + id + ", " + Control_Klasse.kunden_id + ", " + product_id + ", " + diff + ")");

                }

                //connection.commit();
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
                return "FALSE";
            } catch (ParseException e) {
                e.printStackTrace();
            }

            return "READY";
        }
    }

    class Delete_Product_In_Warenkorb extends AsyncTask<String, String, Void>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;


        @Override
        protected Void doInBackground(String... params) {


            try
            {
                //connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/einkaufslistengenerator", "root", "Thierry");
                connection = Control_Klasse.getDBVerbindung();

                if (connection != null)
                {
                    System.out.println("verbindung stshet");
                }

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + product + "'");
                result.first();
                int product_id = result.getInt("produkt_id");

                statement = connection.createStatement();

                statement.executeUpdate("DELETE FROM standard_warenkorb " +
                        "WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);

                statement.executeUpdate("DELETE FROM temp_einkaufsliste " +
                        "WHERE produkt_id = " + product_id + " AND kunden_id = " + Control_Klasse.kunden_id);

                //connection.commit();
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return null;
        }
    }

}
