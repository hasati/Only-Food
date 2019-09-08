package com.example.cool.foodie.breakfastMenu;

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

import java.util.HashMap;

/**
 * Created by cool on 30-09-2017.
 */

public class Sandwitch extends Fragment {
    ListView listview ;
    SparseBooleanArray sparseBooleanArray;
    String ValueHolder = "" ;
    private HashMap<String,Double> listWithPrice=new HashMap<String,Double>();
    String[] ListViewItems = new String[] {
            "Veg Plain Sandwitch ₹39.00",
            "Veg Club Sandwitch ₹49.00",
            "Veg Corn Sandwitch ₹59.00",
            "Non Veg Club Sandwitch ₹65.00",
            "Jam Roasted Sandwitch ₹49.00",
            "Chicken Sandwitch ₹65.00",

    };
    private Double[] ListViewItemsPrice = new Double[]{
            39.00,
            49.00,
            59.00,
            59.00,
            65.00,
            49.00,
            65.00,
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.breakfast_sandwitch, container, false);



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
                tv.setTextColor(Color.parseColor("#4c2601"));

                // Generate ListView Item using TextView
                return view;
            }
        };

        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub

                ValueHolder="";
                sparseBooleanArray = listview.getCheckedItemPositions();

                int i = 0 ;
                listWithPrice.clear();
                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {
                        listWithPrice.put(ListViewItems [ sparseBooleanArray.keyAt(i) ],ListViewItemsPrice[sparseBooleanArray.keyAt(i)]);
                    }

                    i++ ;
                }
            }
        });
        return rootView;
    }

    public HashMap <String,Double> get(){
        return  listWithPrice;
    }
}
