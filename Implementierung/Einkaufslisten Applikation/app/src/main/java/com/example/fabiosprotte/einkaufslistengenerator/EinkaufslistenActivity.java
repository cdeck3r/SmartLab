package com.example.fabiosprotte.einkaufslistengenerator;

import android.app.Activity;
import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
//import android.app.Fragment;
//import android.app.FragmentManager;
//import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.List;


public class EinkaufslistenActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_einkaufslisten);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);

        /*Fragment fragment_Liste = new Liste_Einkaufsliste_Fragment();
        getSupportFragmentManager().beginTransaction().add(fragment_Liste, "Liste");//add(R.id.fragment_liste, fragment_Liste).commit();*/

        /*Fragment fragment_Button = new Button_Einkaufsliste();
        getFragmentManager().beginTransaction().add(R.id.fragment_button, fragment_Button).commit();*/
    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Liste_Einkaufsliste_Fragment(), "ListView");
        viewPager.setAdapter(adapter);
    }

}
