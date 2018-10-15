package com.example.fabiosprotte.einkaufslistengenerator;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.ExecutionException;


public class Anmelden_Erzeugung_QRCode extends Activity {

    private String name;
    private String vorname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anmelden__erzeugung__qrcode);

        Get_Kunde get_kunde = new Get_Kunde();
        try
        {
            get_kunde.execute().get();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }

        QRCodeWriter writer = new QRCodeWriter();
        try
        {
            //Erzeugung einer BitMatrix mit angegeben String
            BitMatrix bitMatrix = writer.encode(vorname + " " + name, BarcodeFormat.QR_CODE, 512, 512);

            int width = bitMatrix.getWidth();
            int height = bitMatrix.getHeight();
            Bitmap bmp = Bitmap.createBitmap(width, height, Bitmap.Config.RGB_565);

            //Erzeugung des QRCodes in einer Bitmap
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    bmp.setPixel(x, y, bitMatrix.get(x, y) ? Color.BLACK : Color.WHITE);
                }
            }

            ((ImageView) findViewById(R.id.imageView_qrcode)).setImageBitmap(bmp);

        }
        catch (WriterException e)
        {
            e.printStackTrace();
        }
    }

    class Get_Kunde extends AsyncTask<String, String, String> {
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
                result = statement.executeQuery("SELECT Vorname, Nachname FROM kunde WHERE Kunden_Id = " + Control_Klasse.kunden_id);
                result.first();

                vorname = result.getString("Vorname");
                name = result.getString("Nachname");

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
