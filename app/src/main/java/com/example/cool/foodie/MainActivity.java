package com.example.cool.foodie;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.cool.foodie.loginUtility.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private Button login;
    private Button admin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
      /*  Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);*/
        addListenerOnButton();
    }
    public void addListenerOnButton() {
        login = (Button) findViewById(R.id.button2);
        admin=(Button) findViewById(R.id.button);

        login.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent homeIntent = new Intent(MainActivity.this, LoginActivity.class);
               /* homeIntent.setAction(getIntent().getAction());
                homeIntent.putExtras(getIntent());*/
                startActivity(homeIntent);
            }
        });

        admin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"Still Application in developer stage", Toast.LENGTH_LONG).show();
            }
        });
    }
}
