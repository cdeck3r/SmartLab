package com.example.fabiosprotte.einkaufslistengenerator;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

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
import java.util.ResourceBundle;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;


public class MainActivity extends Activity {

    private Button button_einkaufsliste;
    private Button button_warenkorb;
    private Button button_historie;
    private Button button_anmelden;

    private ArrayList<Integer> integerArrayList;
    private long Tage_seit_einkauf;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Service service = new Service();

        button_einkaufsliste = (Button) findViewById(R.id.button_einkaufsliste);
        button_einkaufsliste.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_Einkaufsliste = new Intent(MainActivity.this, EinkaufslistenActivity.class);
                startActivity(intent_Einkaufsliste);
            }
        });

        button_warenkorb = (Button) findViewById(R.id.button_Warenkorb);
        button_warenkorb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_Warenkorb = new Intent(MainActivity.this, WarenkorbActivity.class);
                startActivity(intent_Warenkorb);
            }
        });

        button_historie = (Button) findViewById(R.id.button_Historie);
        button_historie.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_Historie = new Intent(MainActivity.this, HistorieActivity.class);
                startActivity(intent_Historie);
            }
        });

        button_anmelden = (Button) findViewById(R.id.button_anmelden);
        button_anmelden.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent_Anmelden = new Intent(MainActivity.this, Anmelden_Erzeugung_QRCode.class);
                startActivity(intent_Anmelden);
            }
        });

        //Alle Produkt setzen
        try
        {
            setAllProdukte(Control_Klasse.kunden_id);

            for(int i = 0; i < Control_Klasse.produktArrayList.size(); i++)
            {
                if(Control_Klasse.produktArrayList.get(i).isIn_warenkorb())
                {
                    Control_Klasse.warenkorb.add(Control_Klasse.produktArrayList.get(i));
                }
            }
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < Control_Klasse.warenkorb.size(); i++)
        {

                try
                {
                    integerArrayList = getVerbrauchsDaten_Produkt(Control_Klasse.warenkorb.get(i).getProdukt_id(), Control_Klasse.kunden_id);
                }
                catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                try
                {
                    Tage_seit_einkauf = getAbgelaufeneTage_seit_Einkauf(Control_Klasse.warenkorb.get(i).getProdukt_id(), Control_Klasse.kunden_id);
                }
                catch (ExecutionException e)
                {
                    e.printStackTrace();
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }

                if(integerArrayList.size() > 20)
                {
                    Control_Klasse.warenkorb.get(i).setVerbrauchswarscheinlichkeit(service.computeKumulativeWahrscheinlichkeit(integerArrayList, (int)Tage_seit_einkauf) *100); //Tage_seit_einkauf) * 100);

                    System.out.println(Control_Klasse.warenkorb.get(i).getProdukt_id() + "               " + Control_Klasse.warenkorb.get(i).getBezeichnung() + "             " + Control_Klasse.warenkorb.get(i).getVerbrauchswarscheinlichkeit() + "\n"
                            + "Tage Seit Einkauf:      " + Tage_seit_einkauf);

                    if(Control_Klasse.warenkorb.get(i).getVerbrauchswarscheinlichkeit() >= Control_Klasse.unter_grenze_rot)
                    {
                        try
                        {
                            setProdukt_Automatisch_auf_Einkaufsliste(Control_Klasse.warenkorb.get(i).getBezeichnung());
                        }
                        catch (ExecutionException e)
                        {
                            e.printStackTrace();
                        }
                        catch (InterruptedException e)
                        {
                            e.printStackTrace();
                        }
                    }
                }
        }

        // Update-Methode für Verteilungsfunktion und gegenbenfalls automatische Bestückung der Einkaufsliste
        // --> public int[]array getProdukt_Verbrauchs_Array(product_id);
        // --> public void berechnung(int[]array); --> date.today() - kaufdatum = diff
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void setAllProdukte(int kunden_id) throws ExecutionException, InterruptedException {
        Get_All_Produkte get_all_produkte = new Get_All_Produkte();
        Control_Klasse.produktArrayList = get_all_produkte.execute(kunden_id).get();
    }

    public ArrayList<Integer> getVerbrauchsDaten_Produkt(int produkt_id, int kunden_id) throws ExecutionException, InterruptedException {
        ArrayList<Integer> arrayList = new ArrayList<>();

        Verbrauch_In_Tagen_Produkt verbrauch_in_tagen_produkt = new Verbrauch_In_Tagen_Produkt();
        arrayList = verbrauch_in_tagen_produkt.execute(produkt_id, kunden_id).get();

        return arrayList;
    }

    public Long getAbgelaufeneTage_seit_Einkauf(int produkt_id, int kunden_id) throws ExecutionException, InterruptedException {
        Long abgelaufenTage;

        AbgelaufeneTage_Produkt abgelaufeneTage_produkt = new AbgelaufeneTage_Produkt();
        abgelaufenTage = abgelaufeneTage_produkt.execute(produkt_id, kunden_id).get();

        return abgelaufenTage;
    }

    public void setProdukt_Automatisch_auf_Einkaufsliste(String product) throws ExecutionException, InterruptedException {
        Fill_Einkaufsliste_Automatisch fill_einkaufsliste_automatisch = new Fill_Einkaufsliste_Automatisch();
        fill_einkaufsliste_automatisch.execute(product).get();
    }

    class Get_All_Produkte extends AsyncTask<Integer, String, ArrayList<Produkt>>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;
        private ArrayList<Produkt> arrayList;

        @Override
        protected ArrayList<Produkt> doInBackground(Integer... params) {

            int kunden_id = params[0];

            connection = Control_Klasse.getDBVerbindung();

            if (connection != null)
            {
                System.out.println("verbindung stshet");
            }

            try
            {
                arrayList = new ArrayList<>();

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT * FROM produkt");
                result.last();
                int anzahl = result.getRow();
                result.first();

                for(int i = 0; i < anzahl; i++)
                {
                    Produkt produkt = new Produkt();
                    produkt.setProdukt_id(result.getInt("produkt_id"));
                    produkt.setBezeichnung(result.getString("Bezeichnung"));
                    arrayList.add(produkt);
                    result.next();
                }


                result = statement.executeQuery("SELECT produkt_id FROM standard_warenkorb WHERE kunden_id = " + kunden_id);
                result.last();
                anzahl = result.getRow();
                result.first();


                for(int i = 0; i < anzahl; i++)
                {
                    for(int j = 0; j < arrayList.size(); j++)
                    {
                        if(arrayList.get(j).getProdukt_id() == result.getInt("produkt_id"))
                        {
                            arrayList.get(j).setIn_warenkorb(true);
                        }
                    }

                    result.next();
                }
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return arrayList;
        }
    }

    class Verbrauch_In_Tagen_Produkt extends AsyncTask<Integer, String, ArrayList<Integer>>
    {
        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;
        private ArrayList<Integer> arrayList;

        @Override
        protected ArrayList<Integer> doInBackground(Integer... params) {

            int product_id = params[0];
            int kunden_id = params[1];

            connection = Control_Klasse.getDBVerbindung();

            if (connection != null)
            {
                System.out.println("verbindung stshet");
            }

            try
            {
                arrayList = new ArrayList<>();

                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT verbrauch_in_tagen FROM produkt_verbrauchsstatistik WHERE produkt_id = " + product_id + " AND kunden_id = " + kunden_id);
                result.last();

                int anzahl = result.getRow();

                result.first();

                for(int i = 0; i < anzahl; i++)
                {
                    arrayList.add(result.getInt("verbrauch_in_tagen"));
                    System.out.println(product_id + "    " + result.getInt("verbrauch_in_tagen"));
                    result.next();
                }

                result = statement.executeQuery("SELECT ablauf_in_tagen FROM produkt_mhd_statistik WHERE produkt_id = " + product_id);
                result.last();

                anzahl = result.getRow();

                result.first();

                for(int i = 0; i < anzahl; i++)
                {
                    arrayList.add(result.getInt("ablauf_in_tagen"));
                    //System.out.println(product_id + "    " + result.getInt("ablauf_in_tagen"));
                    result.next();
                }

                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return arrayList;
        }
    }

    class AbgelaufeneTage_Produkt extends AsyncTask<Integer, String, Long>
    {

        private Connection connection = null;
        private Statement statement;
        private PreparedStatement preparedStatement;
        private ResultSet result;
        private int abgelaufenTage;

        Calendar c = Calendar.getInstance();
        Date date_today;
        String fdate;
        long diff;

        @Override
        protected Long doInBackground(Integer... params) {

            int product_id = params[0];
            int kunden_id = params[1];

            date_today = c.getTime();
            fdate = new SimpleDateFormat("yyyy-MM-dd").format(date_today);

            connection = Control_Klasse.getDBVerbindung();

            if (connection != null)
            {
                System.out.println("verbindung steht");
            }

            try
            {
                statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);

                result = statement.executeQuery("SELECT kauf_datum FROM einkaufs_historie WHERE produkt_id = " + product_id + " AND kunden_id = " + kunden_id);
                result.first();

                String datum = result.getString("kauf_datum");
                //String datum = "2018-05-01";
                Date kauf_date = new SimpleDateFormat("yyyy-MM-dd").parse(datum);
                diff = date_today.getTime() - kauf_date.getTime();
                diff = TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            System.out.println("Diff Tage:    " + diff);

            return diff;
        }
    }

    class Fill_Einkaufsliste_Automatisch extends AsyncTask<String, String, String>
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

            String product = params[0];

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
}
