package com.example.cool.foodie.lunchMenu;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cool.foodie.R;

/**
 * Created by cool on 9/10/2017.
 */

public class IndianVeg extends Fragment {
    ListView listview ;
    SparseBooleanArray sparseBooleanArray;
    String ValueHolder = "" ;
    String[] ListViewItems = new String[] {
            "ListView ITEM-1",
            "ListView ITEM-2",
            "ListView ITEM-3",
            "ListView ITEM-4",
            "ListView ITEM-5",
            "ListView ITEM-6",
            "ListView ITEM-7",
            "ListView ITEM-8",
            "ListView ITEM-9",
            "ListView ITEM-10"

    };
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.indian_veg,container, false);



        listview  = (ListView) rootView.findViewById(R.id.listView);


        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (getContext(),android.R.layout.simple_list_item_multiple_choice,
                        android.R.id.text1, ListViewItems){
            @Override
            public View getView(int position, View convertView, ViewGroup parent){
                // Get the Item from ListView
                View view = super.getView(position, convertView, parent);

                // Initialize a TextView for ListView each Item
                TextView tv = (TextView) view.findViewById(android.R.id.text1);

                // Set the text color of TextView (ListView Item)
                tv.setTextColor(Color.RED);

                // Generate ListView Item using TextView
                return view;
            }
        };

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                // Toast.makeText(getContext(), "ListView Selected Values = "+ item(), Toast.LENGTH_LONG).show();

                ValueHolder="";
                sparseBooleanArray = listview.getCheckedItemPositions();

                int i = 0 ;

                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder += ListViewItems [ sparseBooleanArray.keyAt(i) ] + ",";
                    }

                    i++ ;
                }

                // ValueHolder = ValueHolder.replaceAll("(,)*$", "");

                Toast.makeText(getContext(), "ListView Selected Values = " + ValueHolder, Toast.LENGTH_LONG).show();


            }
        });
        return rootView;
    }

    public String get(){
        return  ValueHolder;
    }
}

