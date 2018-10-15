package com.example.fabiosprotte.kassen_system;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import java.text.NumberFormat;
import java.util.ArrayList;

/**
 * Created by Fabio Sprotte on 23.08.2018.
 */
public class GridListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> arrayList;
    private LayoutInflater inflater;
    private boolean isListView;
    private int selectedPosition = -1;

    public GridListAdapter(Context context, ArrayList<String> arrayList, boolean isListView) {
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

            //inflate the layout on basis of boolean
            if (isListView)
                view = inflater.inflate(R.layout.list_costum_row_layout, viewGroup, false);
            /*else
                view = inflater.inflate(R.layout.grid_custom_row_layout, viewGroup, false);*/

                viewHolder.label = (TextView) view.findViewById(R.id.label);
                viewHolder.radioButton = (RadioButton) view.findViewById(R.id.radio_button);

                view.setTag(viewHolder);
        }
        else
        {
            viewHolder = (ViewHolder) view.getTag();
        }


        viewHolder.label.setText(arrayList.get(i));


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
            Toast.makeText(context, "Ausgewähltes Produkt: " + arrayList.get(selectedPosition) + " in Einkaufsliste " , Toast.LENGTH_SHORT).show();
            return arrayList.get(selectedPosition);
        }
        return "";
    }

    //Delete the selected position from the arrayList
    public String ControlSelectedPosition() {
        if (selectedPosition != -1) {
            String s = arrayList.get(selectedPosition);
            //selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();

            return s;
        }

        return "";
    }

    public void insertNewPosition(String product)
    {
        arrayList.add(product);
        notifyDataSetChanged();
    }

    public void deleteSelectedPosition(String product)
    {
        if (selectedPosition != -1) {

            for(int i = 0; i < Control_Klasse.dynamicButtons.size(); i++)
            {
                if(Control_Klasse.dynamicButtons.get(i).getText() == arrayList.get(selectedPosition))
                {
                    Control_Klasse.dynamicButtons.get(i).setEnabled(true);
                }
            }
            arrayList.remove(selectedPosition);
            selectedPosition = -1;//after removing selectedPosition set it back to -1
            notifyDataSetChanged();
        }
    }

    public void deleteCompleteList()
    {
        arrayList.clear();
        notifyDataSetChanged();
    }
}
