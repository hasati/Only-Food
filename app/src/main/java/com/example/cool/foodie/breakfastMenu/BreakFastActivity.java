package com.example.cool.foodie.breakfastMenu;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import android.widget.Toast;

import com.example.cool.foodie.EattingChoiceActivity;
import com.example.cool.foodie.checkoutUtility.CheckOutActivity;
import com.example.cool.foodie.R;
import com.example.cool.foodie.loginUtility.LoginActivity;

import java.util.HashMap;

public class BreakFastActivity extends AppCompatActivity {


    private SectionsPagerAdapter mSectionsPagerAdapter;

    private String selectedItem;
    private ViewPager mViewPager;
    private String userName;

    SouthIndian t0= new SouthIndian();
    Punjabi t1=new Punjabi();
    Sandwitch t2=new Sandwitch();
    Indori t3=new Indori();
    Maharashtrian t4=new Maharashtrian();
    Beverages t5=new Beverages();


    @Override
    protected void onCreate(Bundle savedInstanceState)  {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_breakfast);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        Intent intent = getIntent();
        userName = intent.getStringExtra("userName");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);


        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BreakFastActivity.this);


                // Setting Dialog Message
                alertDialog.setMessage("Are you sure you want clear the selected items?").setCancelable(false);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog,int which) {
                        Toast.makeText(getApplicationContext(), "You clicked on YES", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(BreakFastActivity.this,EattingChoiceActivity.class);
                        intent.putExtra("userName",userName);
                        startActivity(intent);
                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
            }

        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }



    @Override
    public void onBackPressed(){

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(BreakFastActivity.this);

        alertDialog.setMessage("Are you sure you want clear the selected items ?").setCancelable(false);
        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getApplicationContext(), "Sign Out", Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(BreakFastActivity.this, LoginActivity.class);
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


    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.

        switch (item.getItemId()) {
            case R.id.action_favorite:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder(BreakFastActivity.this);
                alertDialog.setMessage("Add these items to card?").setCancelable(false);
                alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        HashMap<String,Double> listWithPrice=new HashMap<String,Double>();
                        listWithPrice=t0.get();
                        listWithPrice.putAll(t1.get());
                        listWithPrice.putAll(t2.get());
                        listWithPrice.putAll(t3.get());
                        listWithPrice.putAll(t4.get());
                        listWithPrice.putAll(t5.get());

                        if(listWithPrice.size()==0){
                            Toast.makeText(getApplicationContext(), "No Item is selected", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        Intent intent=new Intent(BreakFastActivity.this, CheckOutActivity.class);
                        intent.putExtra("selectedItem",listWithPrice);
                        intent.putExtra("userName",userName);
                        startActivity(intent);

                    }
                });

                // Setting Negative "NO" Button
                alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to invoke NO event
                        Toast.makeText(getApplicationContext(), "You clicked on NO", Toast.LENGTH_SHORT).show();
                        dialog.cancel();
                    }
                });

                // Showing Alert Message
                alertDialog.show();
                return  true;

            default:

                return super.onOptionsItemSelected(item);
        }
    }
    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            //Return Current Tab
            switch (position) {
                case 0:
                    return t0;
                case 1:
                    return t1;
                case 2:

                    return t2;
                case 3:

                    return t3;
                case 4:

                    return t4;
                case 5:

                    return t5;
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            // Show 5 total pages.
            return 6;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SOUTH INDIAN ";
                case 1:
                    return "PUNJABI";
                case 2:
                    return "SANDWITCH";
                case 3:
                    return "INDORI";
                case 4:
                    return "MAHARASHTRIAN";
                case 5:
                    return "BEVERAGES";
            }
            return null;
        }
    }
}
