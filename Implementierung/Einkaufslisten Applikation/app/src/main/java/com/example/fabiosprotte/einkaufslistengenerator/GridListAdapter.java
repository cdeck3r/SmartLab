package com.example.fabiosprotte.einkaufslistengenerator;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabiosprotte.einkaufslistengenerator.R;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Fabio Sprotte on 08.05.2018.
 */
public class GridListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Produkt> arrayList;
    private LayoutInflater inflater;
    private boolean isListView;
    private int selectedPosition = -1;

    public GridListAdapter(Context context, ArrayList<Produkt> arrayList, boolean isListView) {
        this.context = context;
        this.arrayList = arrayList;
        this.isListView = isListView;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return arrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder;

        if(view == null)
        {
            viewHolder = new ViewHolder();

                if (isListView)
                    view = inflater.inflate(R.layout.list_custom_row_layout, viewGroup, false);

                if(Control_Klasse.einkaufsliste == false)
                {

                    if (arrayList.get(i).getVerbrauchswarscheinlichkeit() < Control_Klasse.unter_grenze_gelb) {
                        view.setBackgroundColor(Color.GREEN);
                        view.getBackground().setAlpha(65);
                    }
                    if (arrayList.get(i).getVerbrauchswarscheinlichkeit() < Control_Klasse.unter_grenze_rot && arrayList.get(i).getVerbrauchswarscheinlichkeit() > Control_Klasse.unter_grenze_gelb) {
                        view.setBackgroundColor(Color.YELLOW);
                        view.getBackground().setAlpha(65);
                    }
                    if (arrayList.get(i).getVerbrauchswarscheinlichkeit() > Control_Klasse.unter_grenze_rot) {
                        view.setBackgroundColor(Color.RED);
                        view.getBackground().setAlpha(65);
                    }

                }

            /*else
                view = inflater.inflate(R.layout.grid_custom_row_layout, viewGroup, false);*/

                viewHolder.label = (TextView) view.findViewById(R.id.label);
                viewHolder.radioButton = (RadioButton) view.findViewById(R.id.radio_button);

                view.setTag(viewHolder);

            //inflate the layout on basis of boolean

        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }

        if(Control_Klasse.einkaufsliste == false)
        {
            //DecimalFormat f = new DecimalFormat("##.## %");
            NumberFormat n = NumberFormat.getInstance();
            n.setMaximumFractionDigits(2);

            if(arrayList.get(i).isIn_warenkorb()) {
                viewHolder.label.setText(arrayList.get(i).getBezeichnung() + " : " + n.format(arrayList.get(i).getVerbrauchswarscheinlichkeit()) + " %");
            }
        }
        else
        {
            viewHolder.label.setText(arrayList.get(i).getBezeichnung());
        }

        //check the radio button if both position and selectedPosition matches
        viewHolder.radioButton.setChecked(i == selectedPosition);

        //Set the position tag to both radio button and label
        viewHolder.radioButton.setTag(i);
        viewHolder.label.setTag(i);

        viewHolder.radioButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }
        });

        viewHolder.label.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                itemCheckChanged(v);
            }


        });

        return view;
    }

    //On selecting any view set the current position to selectedPositon and notify adapter
    private void itemCheckChanged(View v) {
        selectedPosition = (Integer) v.getTag();
        notifyDataSetChanged();
    }

    private class ViewHolder {
        private TextView label;
        private RadioButton radioButton;
    }

    //Return the selectedPosition item
    public String getSelectedItem() {
        if (selectedPosition != -1) {
            Toast.makeText(context, "Ausgewähltes Produkt: " + arrayList.get(selectedPosition).getBezeichnung() + " in Einkaufsliste " , Toast.LENGTH_SHORT).show();
            return arrayList.get(selectedPosition).getBezeichnung();
        }
        return "";
    }

    //Delete the selected position from the arrayList
    public String deleteSelectedPosition() {
        if (selectedPosition != -1) {
            String s = arrayList.get(selectedPosition).getBezeichnung();
            if(Control_Klasse.einkaufsliste)
            {
                arrayList.get(selectedPosition).setVerbrauchswarscheinlichkeit(0);
            }
            arrayList.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();

            return s;
        }

        return null;
    }

    public void insertNewPosition(String product)
    {
        Produkt produkt = new Produkt();
        produkt.setBezeichnung(product);
        arrayList.add(produkt);
        notifyDataSetChanged();
    }
}
