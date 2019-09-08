package com.example.cool.foodie.breakfastMenu;

import android.util.SparseBooleanArray;
import android.widget.ListView;


/**
 * Created by cool on 05-10-2017.
 */

 class Items {

    String item="";
    SparseBooleanArray sparseBooleanArray ;

 public String get(){
     return item;
 }


 public void items(ListView list,String[]items){


     sparseBooleanArray = list.getCheckedItemPositions();




     int i = 0 ;

     while (i < sparseBooleanArray.size()) {

         if (sparseBooleanArray.valueAt(i)) {

             item += items [ sparseBooleanArray.keyAt(i) ] + ",";
			 
         }

         i++ ;
     }

     item = item.replaceAll("(,)*$", "");

     //Toast.makeText(Items, "ListView Selected Values = " + ValueHolder, Toast.LENGTH_LONG).show();


 }
}
