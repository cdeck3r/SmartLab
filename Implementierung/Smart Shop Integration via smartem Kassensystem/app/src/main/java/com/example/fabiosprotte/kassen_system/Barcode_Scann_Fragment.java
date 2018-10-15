package com.example.fabiosprotte.kassen_system;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;


public class Barcode_Scann_Fragment extends Fragment implements ZXingScannerView.ResultHandler {

    private Context context;
    private View view;

    private ZXingScannerView zXingScannerView;
    private LinearLayout surfaceView;

    public Barcode_Scann_Fragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_barcode__scann_, container, false);
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    public void onViewCreated(View view,  Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        OnClickEvent(view);
        surfaceView = (LinearLayout) view.findViewById(R.id.linear_layout);
        zXingScannerView = new ZXingScannerView(context);
    }

    private void OnClickEvent(final View view)
    {
        view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scan(view);
            }
        });
    }

    public void scan(View view)
    {
        //((RelativeLayout)view).addView(zXingScannerView);
        surfaceView.addView(zXingScannerView);
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

        String ergebnis = result.getText().toString();

        boolean control = false;

        for(int i = 0; i < Control_Klasse.dynamicButtons.size(); i++)
        {
            if(Control_Klasse.dynamicButtons.get(i).getText().toString().equals(ergebnis))
            {
                Control_Klasse.dynamicButtons.get(i).setEnabled(false);
                break;
            }
        }

        if(Control_Klasse.temp_Kasse_Produkte.size() == 0)
        {
            //Control_Klasse.temp_Kasse_Produkte.add(Control_Klasse.produktArrayList.get(i));
            Control_Klasse.adapter.insertNewPosition(ergebnis);
        }
        else
        {
            for(int k = 0; k < Control_Klasse.temp_Kasse_Produkte.size(); k++ )
            {
                if(Control_Klasse.temp_Kasse_Produkte.get(k).equals(ergebnis))// == ergebnis)
                {
                    control = false;
                    break;
                }
                else
                {
                    control = true;
                }
            }

            if(control == true)
            {
                //Control_Klasse.temp_Kasse_Produkte.add(Control_Klasse.produktArrayList.get(i));
                Control_Klasse.adapter.insertNewPosition(result.getText());
            }
            else
            {
                Toast.makeText(context, "Ausgewähltes Produkt schon abgehackt", Toast.LENGTH_SHORT).show();
            }
        }


        zXingScannerView.resumeCameraPreview(this);
    }
}
