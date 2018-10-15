package com.example.fabiosprotte.kassen_system;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class product_list_warenkorb_Fragment extends Fragment {

    private Context context;

    private ArrayList<String> arrayList;
    private ListView listView;


    HorizontalScrollView scrollView;

    private String status;
    private String product;

    int button_anzahl = 0;
    private Intent intent;

    boolean oben = true;

    private Fill_Warenkorb_Liste fill_warenkorb_liste;


    public product_list_warenkorb_Fragment() {
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

        Control_Klasse.dynamicButtons = new ArrayList<>();

        try
        {
            String s = "ja";
            fill_warenkorb_liste = new Fill_Warenkorb_Liste();
            status = fill_warenkorb_liste.execute(s).get();
            //fillArray();
        }
        catch (ExecutionException e)
        {
            e.printStackTrace();
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }

        if(status == "READY")
        {
            scrollView = new HorizontalScrollView(getActivity());

            View view = inflater.inflate(R.layout.fragment_product_list_warenkorb_, container, false);


            LinearLayout ll = new LinearLayout(getActivity()); //root LinearLayout
            LinearLayout l2 = new LinearLayout(getActivity()); //sub linearlayout
            LinearLayout l3 = new LinearLayout(getActivity()); //sub linearlayout

            ll.setOrientation(LinearLayout.VERTICAL);//with horizontal orientation
            //ll.setBackgroundColor(Color.BLUE);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);

            l2.setOrientation(LinearLayout.HORIZONTAL);//with horizontal orientation
            //l2.setBackgroundColor(Color.GREEN);
            LinearLayout.LayoutParams layoutParams3 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            l3.setOrientation(LinearLayout.HORIZONTAL);//with horizontal orientation
            //l3.setBackgroundColor(Color.RED);
            LinearLayout.LayoutParams layoutParams4 = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT, 1);

            LinearLayout.LayoutParams layoutParams5 = new LinearLayout.LayoutParams(200, 200);
            layoutParams5.setMargins(75, 85, 20, 100);
            layoutParams5.gravity = Gravity.CENTER;

            GradientDrawable shape_a = new GradientDrawable();
            shape_a.setCornerRadius(15);
            shape_a.setColor(Color.GREEN);

            /*GradientDrawable shape_b = new GradientDrawable();
            shape_b.setCornerRadius(15);
            shape_b.setColor(Color.GREEN);

            GradientDrawable shape_c = new GradientDrawable();
            shape_c.setCornerRadius(15);
            shape_c.setColor(Color.GREEN);*/

            ll.setOrientation(LinearLayout.VERTICAL);
            ll.addView(l2, layoutParams3);
            ll.addView(l3, layoutParams4);

            scrollView.addView(ll, layoutParams);

            for (int i = 0; i < Control_Klasse.produktArrayList.size(); i++)
            {
                Control_Klasse.dynamicButtons.add(new Button(getActivity()));
                Control_Klasse.dynamicButtons.get(button_anzahl).setText(Control_Klasse.produktArrayList.get(i).getBezeichnung());
                Control_Klasse.dynamicButtons.get(button_anzahl).setId(button_anzahl);
                Control_Klasse.dynamicButtons.get(button_anzahl).setTextSize(15.0f);
                Control_Klasse.dynamicButtons.get(button_anzahl).setLayoutParams(layoutParams5);
                if(Control_Klasse.produktArrayList.get(i).isWarenkorb())
                {
                    Control_Klasse.dynamicButtons.get(button_anzahl).setBackground(context.getDrawable(R.drawable.xml_button_produkt));
                }
                else
                {
                    Control_Klasse.dynamicButtons.get(button_anzahl).setBackground(context.getDrawable(R.drawable.xml_button_produkt_liste));
                }
                Control_Klasse.dynamicButtons.get(button_anzahl).getBackground().setAlpha(65);
                Control_Klasse.dynamicButtons.get(button_anzahl).setEnabled(true);

                if (oben) {
                    l2.addView(Control_Klasse.dynamicButtons.get(button_anzahl));
                    oben = false;
                } else {
                    l3.addView(Control_Klasse.dynamicButtons.get(button_anzahl));
                    oben = true;
                }

                button_anzahl++;
            }

            ((RelativeLayout)view).addView(scrollView);

            for(int i = 0; i < Control_Klasse.dynamicButtons.size(); i++) {
                Control_Klasse.dynamicButtons.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int j = v.getId();

                        for (int i = 0; i < Control_Klasse.produktArrayList.size(); i++) {
                            if (Control_Klasse.dynamicButtons.get(i).getId() == j) {
                                System.out.println(Control_Klasse.produktArrayList.get(i));
                                boolean control = false;

                                if(Control_Klasse.temp_Kasse_Produkte.size() == 0)
                                {
                                    //Control_Klasse.temp_Kasse_Produkte.add(Control_Klasse.produktArrayList.get(i));
                                    Control_Klasse.adapter.insertNewPosition(Control_Klasse.produktArrayList.get(i).getBezeichnung());
                                }
                                else
                                {
                                    for(int k = 0; k < Control_Klasse.temp_Kasse_Produkte.size(); k++ )
                                    {
                                        if(Control_Klasse.temp_Kasse_Produkte.get(k) == Control_Klasse.produktArrayList.get(i).getBezeichnung())
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
                                        Control_Klasse.adapter.insertNewPosition(Control_Klasse.produktArrayList.get(i).getBezeichnung());
                                    }
                                    else
                                    {
                                        Toast.makeText(context, "Ausgewähltes Produkt schon abgehackt", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                Control_Klasse.dynamicButtons.get(i).setEnabled(false);

                            }
                        }
                    }
                });
            }

            return view;
        }

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_product_list_warenkorb_, container, false);
        //return inflater.inflate(scrollView, container, false);

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
                connection = Control_Klasse.getDBVerbindung();
                //connection = DriverManager.getConnection("jdbc:mysql://10.0.2.2:3306/einkaufslistengenerator", "root", "Thierry");

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


                for (int i = 0; i < integerArrayList.size(); i++)
                {
                        Produkt produkt = new Produkt();
                        produkt.setProdukt_id(integerArrayList.get(i));
                        result = statement.executeQuery("SELECT Bezeichnung FROM produkt WHERE produkt_id = " + integerArrayList.get(i));
                        result.first();
                        produkt.setBezeichnung(result.getString("Bezeichnung"));
                        produkt.setWarenkorb(true);
                        Control_Klasse.produktArrayList.add(produkt);
                }

                result = statement.executeQuery("SELECT produkt_id FROM produkt");

                result.last();
                int anzahl2 = result.getRow();

                result.first();

                ArrayList<Integer> integerArrayList2 = new ArrayList<>();

                for(int j = 0; j < anzahl2; j++)
                {
                    integerArrayList2.add(result.getInt("produkt_id"));
                    result.next();
                }

                boolean control = false;

                for (int i = 0; i < integerArrayList2.size(); i++)
                {
                    control = false;

                    for(int k = 0; k < Control_Klasse.produktArrayList.size(); k++)
                    {
                        if(integerArrayList2.get(i) == Control_Klasse.produktArrayList.get(k).getProdukt_id())
                        {
                            control = true;
                            break;
                        }
                    }

                    if(control == false)
                    {
                        Produkt produkt = new Produkt();
                        produkt.setProdukt_id(integerArrayList2.get(i));
                        result = statement.executeQuery("SELECT Bezeichnung FROM produkt WHERE produkt_id = " + integerArrayList2.get(i));
                        result.first();
                        produkt.setBezeichnung(result.getString("Bezeichnung"));
                        Control_Klasse.produktArrayList.add(produkt);
                    }
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

}
