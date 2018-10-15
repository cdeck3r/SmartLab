package com.example.fabiosprotte.einkaufslistengenerator;

import android.app.Activity;
import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;


public class HistorieActivity extends ListActivity {

    //LIST OF ARRAY STRINGS WHICH WILL SERVE AS LIST ITEMS
    ArrayList<String> listItems=new ArrayList<String>();

    //DEFINING A STRING ADAPTER WHICH WILL HANDLE THE DATA OF THE LISTVIEW
    ArrayAdapter<String> adapter;

    ListView listView;

    ArrayList<String> array_Historie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historie);

        adapter=new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1,
                listItems);
        setListAdapter(adapter);

        listView = (ListView) findViewById(R.id.listView);

        Fill_Historie_Liste fill_historie_liste = new Fill_Historie_Liste();
        try
        {
            String s = fill_historie_liste.execute().get();

            if(s == "READY")
            {
                for (int i = 0; i < array_Historie.size(); i++)
                {
                    addItems(listView, array_Historie.get(i));
                }
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

    //METHOD WHICH WILL HANDLE DYNAMIC INSERTION
    public void addItems(View v, String s) {
        listItems.add(s);
        adapter.notifyDataSetChanged();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_historie, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    class Fill_Historie_Liste extends AsyncTask<String, String, String>
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
                result = statement.executeQuery("SELECT produkt_id, kauf_datum, verbrauch_datum FROM einkaufs_historie WHERE kunden_id = " + Control_Klasse.kunden_id);
                result.last();
                int anzahl = result.getRow();

                result.first();

                ArrayList<Integer> integerArrayList = new ArrayList<>();
                ArrayList<String> stringArrayList = new ArrayList<>();

                for(int j = 0; j < anzahl; j++)
                {
                    integerArrayList.add(result.getInt("produkt_id"));
                    stringArrayList.add("Kauf: " + result.getString("kauf_datum") + "    Vebrauch:  " + result.getString("verbrauch_datum"));

                    result.next();
                }

                array_Historie = new ArrayList<>();

                for (int i = 0; i < integerArrayList.size(); i++)
                {
                    result = statement.executeQuery("SELECT Bezeichnung FROM produkt WHERE produkt_id = " + integerArrayList.get(i));
                    result.first();
                    array_Historie.add(result.getString("Bezeichnung") + "\n" + stringArrayList.get(i));
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
