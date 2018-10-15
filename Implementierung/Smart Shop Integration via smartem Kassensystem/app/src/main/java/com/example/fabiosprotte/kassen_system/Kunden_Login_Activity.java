package com.example.fabiosprotte.kassen_system;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.zxing.Result;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Kunden_Login_Activity extends Activity implements ZXingScannerView.ResultHandler {

    private ZXingScannerView zXingScannerView;
    private LinearLayout linearLayout;
    private String vorname;
    private String nachname;
    private boolean kunde_existiert;
    private int k_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kunden__login_);
        linearLayout = (LinearLayout) findViewById(R.id.linear_layout_kunden_login);
        scan();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_kunden__login_, menu);
        return true;
    }

    public void scan()
    {
        zXingScannerView = new ZXingScannerView(getApplicationContext());
        //((RelativeLayout)view).addView(zXingScannerView);
        linearLayout.addView(zXingScannerView);
        zXingScannerView.setResultHandler(this);
        zXingScannerView.startCamera();
    }

    @Override
    public void onPause() {
        super.onPause();
        zXingScannerView.stopCamera();
    }

    @Override
    public void handleResult(Result result) {

        String name = result.getText().toString();
        //Toast.makeText(Kunden_Login_Activity.this , name, Toast.LENGTH_SHORT).show();
        //name = name.substring(7);

        String [] namenArray = name.split(" ");
        for(int i = 0; i < namenArray.length; i++)
        {
            if(vorname == null)
            {
                vorname = namenArray[i];
            }
            else
            {
                nachname = namenArray[i];
            }
        }

        //Toast.makeText(Kunden_Login_Activity.this , vorname + nachname, Toast.LENGTH_SHORT).show();
        //String sql = "SELECT Kunden_Id FROM kunde WHERE Vorname = '" + vorname + "' AND Nachname = '" + nachname + "'";
        //System.out.println(sql);

        Set_Kunden_id set_kunden_id = new Set_Kunden_id();

        try
        {
            String s = set_kunden_id.execute().get();
            Toast.makeText(Kunden_Login_Activity.this , s, Toast.LENGTH_SHORT).show();
            System.out.println(s);

            if(s == "TRUE")
            {
                Control_Klasse.kunden_id = k_id;
                Control_Klasse.kunde_registriert = true;

                Intent Kasse = new Intent(Kunden_Login_Activity.this, Kasse.class);
                startActivity(Kasse);
            }

            if(s == "FALSE")
            {
                new AlertDialog.Builder(this).setTitle("Meldung")
                        .setMessage("Kunde nicht registriert !!")
                        .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent Kasse = new Intent(Kunden_Login_Activity.this, Kasse.class);
                                startActivity(Kasse);
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

        zXingScannerView.resumeCameraPreview(this);
    }


    class Set_Kunden_id extends AsyncTask<String, String, String>
    {
        private Connection connection = null;
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


                result = statement.executeQuery("SELECT Kunden_Id FROM kunde WHERE Vorname = '" + vorname + "' AND Nachname = '" + nachname + "'");
                result.first();

                if(result.getRow() == 0)
                {
                    kunde_existiert = false;

                    return "FALSE";
                }
                else
                {
                    k_id = result.getInt("Kunden_Id");

                    return "TRUE";
                }

            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }

            return "READY";
        }
    }
}



