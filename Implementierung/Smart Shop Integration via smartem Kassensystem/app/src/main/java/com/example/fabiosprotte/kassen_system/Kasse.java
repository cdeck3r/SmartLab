package com.example.fabiosprotte.kassen_system;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.os.Bundle;


public class Kasse extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kasse);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        setupViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new Produkt_liste(), "ListView");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onPause() {
        super.onPause();
        Control_Klasse.temp_Kasse_Produkte.clear();
        Control_Klasse.adapter.deleteCompleteList();
        Control_Klasse.dynamicButtons.clear();
        Control_Klasse.produktArrayList.clear();
    }
}
