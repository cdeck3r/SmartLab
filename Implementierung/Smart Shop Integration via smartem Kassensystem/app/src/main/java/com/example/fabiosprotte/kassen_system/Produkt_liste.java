package com.example.fabiosprotte.kassen_system;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.ExecutionException;

public class Produkt_liste extends Fragment {

    private Context context;
    private ListView listView;



    public Produkt_liste() {
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

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_produkt_liste, container, false);
    }

    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        loadListView(view);
        OnClickEvent(view);
    }

    private void loadListView(View view) {
        listView = (ListView) view.findViewById(R.id.listView);
        Control_Klasse.adapter = new GridListAdapter(context, Control_Klasse.temp_Kasse_Produkte, true);
        listView.setAdapter(Control_Klasse.adapter);
    }

    private void OnClickEvent(final View view)
    {
        view.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view)
            {

                                String s = Control_Klasse.adapter.ControlSelectedPosition();

                                if (s.isEmpty()) {
                                    new AlertDialog.Builder(context).setTitle("Meldung")
                                            .setMessage("Es wurde kein Produkt ausgewählt !!")
                                            .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialog, int which) {
                                                    // Do nothing
                                                }
                                            }).show();
                                } else {
                                    Control_Klasse.adapter.deleteSelectedPosition(s);
                                }
            }
        });
        view.findViewById(R.id.show_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AlertDialog.Builder(context).setTitle("Meldung")
                        .setMessage("Bezahlvorgang Starten ?")
                        .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Kunde_Bezahlen kunde_bezahlen = new Kunde_Bezahlen();
                                try
                                {
                                    String s = kunde_bezahlen.execute().get();

                                    if(s == "READY")
                                    {
                                        Control_Klasse.temp_Kasse_Produkte.clear();
                                        Control_Klasse.adapter.deleteCompleteList();
                                        Control_Klasse.kunde_registriert = false;
                                        Control_Klasse.dynamicButtons.clear();
                                        Control_Klasse.kunden_id = 0;
                                        Control_Klasse.produktArrayList.clear();

                                        Intent intent_Kunden_Login = new Intent(getActivity(), MainActivity.class);
                                        startActivity(intent_Kunden_Login);

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


    class Kunde_Bezahlen extends AsyncTask<String, String, String>
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

                ArrayList<Integer> list_product_id = new ArrayList<>();

                for(int i = 0; i < Control_Klasse.temp_Kasse_Produkte.size(); i++)
                {
                    result = statement.executeQuery("SELECT produkt_id FROM produkt WHERE Bezeichnung = '" + Control_Klasse.temp_Kasse_Produkte.get(i) + "'");
                    result.first();
                    list_product_id.add(result.getInt("produkt_id"));
                }

                for(int j = 0; j < list_product_id.size(); j++)
                {
                    result = statement.executeQuery("SELECT verbrauch_datum FROM einkaufs_historie WHERE produkt_id = " + list_product_id.get(j) + " AND kunden_id = " + Control_Klasse.kunden_id);
                    result.last();

                    if(result.getRow() == 0)
                    {
                        if(Control_Klasse.kunde_registriert == true)
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
                                    "VALUES (" + einkauf_id + ", " + Control_Klasse.kunden_id + ", " + list_product_id.get(j) + ", '" + fdate + "')");
                        }
                    }
                    else
                    {

                        statement = connection.createStatement();

                        statement.executeUpdate("UPDATE einkaufs_historie " +
                                "SET kauf_datum = '" + fdate + "' " +
                                "WHERE produkt_id = " + list_product_id.get(j) + " AND kunden_id = " + Control_Klasse.kunden_id);
                    }

                    statement = connection.createStatement();

                    statement.executeUpdate("DELETE FROM temp_einkaufsliste " +
                            "WHERE produkt_id = " + list_product_id.get(j) + " AND kunden_id = " + Control_Klasse.kunden_id);

                    result = statement.executeQuery("SELECT warenkorb_id FROM standard_warenkorb WHERE kunden_id = " + Control_Klasse.kunden_id + " AND produkt_id = " + list_product_id.get(j));
                    result.last();

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
                                "VALUES (" + Control_Klasse.kunden_id + ", " + list_product_id.get(j) + ", '" + warenkorb_id + "')");
                    }
                }

                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return "READY";
        }
    }

}
