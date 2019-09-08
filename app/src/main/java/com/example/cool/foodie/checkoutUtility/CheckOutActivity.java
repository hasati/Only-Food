package com.example.cool.foodie.checkoutUtility;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cool.foodie.R;
import com.example.cool.foodie.breakfastMenu.BreakFastActivity;

import java.util.HashMap;
import java.util.Set;


public class CheckOutActivity extends AppCompatActivity {
    private ListView listview;
    private TextView totalTextView;
    private String userName;


    private Button proceedtoCheckout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        listview = (ListView) findViewById(R.id.ListViewCatalog);
        totalTextView = (TextView) findViewById(R.id.totalTextView);


       HashMap<String,Double> listWithPrice= (HashMap<String,Double>)getIntent().getSerializableExtra("selectedItem");
        userName= getIntent().getStringExtra("userName");
       String [] items=new String[listWithPrice.size()];
       Double [] itemsPrice=new Double[listWithPrice.size()];;
       int i=0;
       double totalAmount=0;
       for(String item: listWithPrice.keySet()){
            items[i]=item;
            itemsPrice[i]=listWithPrice.get(item);
            totalAmount=totalAmount+itemsPrice[i];
            i++;
       }

        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (this, android.R.layout.simple_list_item_1,
                        android.R.id.text1, items);

        listview.setAdapter(adapter);
        totalTextView.setText("Total Amount "+totalAmount);

        addListenerOnButton();

    }

    @Override
    public void onBackPressed(){


        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CheckOutActivity.this);

        alertDialog.setMessage("Are you sure you want Cancel?").setCancelable(false);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Intent intent=new Intent(CheckOutActivity.this, BreakFastActivity.class);
                intent.putExtra("userName",userName);
                startActivity(intent);
            }
        });

        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    public void addListenerOnButton() {
       // removeItems = (Button) findViewById(R.id.ButtonRemoveFromCart);
        proceedtoCheckout = (Button) findViewById(R.id.CheckoutButton);
       /* removeItems.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                ValueHolder = "";
                sparseBooleanArray = listview.getCheckedItemPositions();

                int i = 0;

                while (i < sparseBooleanArray.size()) {

                    if (sparseBooleanArray.valueAt(i)) {

                        ValueHolder += item[sparseBooleanArray.keyAt(i)] + ",";
                    }

                    i++;
                }
                String[] reomoveElement = ValueHolder.split(",");

                for (i = 0; i < item.length; i++) {
                    for (int j = 0; j < reomoveElement.length; j++) {
                        if (item[i].equals(reomoveElement[j]))
                            item[i] = null;
                    }
                }

            }


        });*/

        proceedtoCheckout.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {

                Toast.makeText(getApplicationContext(),"Payment Gateway is in development stage!", Toast.LENGTH_SHORT).show();


            }
        });
    }

}

