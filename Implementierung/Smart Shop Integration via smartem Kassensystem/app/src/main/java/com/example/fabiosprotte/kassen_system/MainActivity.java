package com.example.fabiosprotte.kassen_system;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {

    private Button button_kasse;
    private Button button_kunden_login;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button_kasse = (Button) findViewById(R.id.button_kasse);
        button_kasse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                new AlertDialog.Builder(MainActivity.this).setTitle("Meldung")
                        .setMessage("Will sich der Kunde Einloggen ?")
                        .setPositiveButton("JA", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

                                Intent intent_Kunden_Login = new Intent(MainActivity.this, Kunden_Login_Activity.class);
                                startActivity(intent_Kunden_Login);

                            }
                        })
                        .setNegativeButton("NEIN", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent_Kasse = new Intent(MainActivity.this, Kasse.class);
                                startActivity(intent_Kasse);
                            }
                        })
                        .show();
            }
        });

        button_kunden_login = (Button) findViewById(R.id.button_kunden_login);
        button_kunden_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_Kunden_Login = new Intent(MainActivity.this, Kunden_Login_Activity.class);
                startActivity(intent_Kunden_Login);
            }
        });
    }

}
