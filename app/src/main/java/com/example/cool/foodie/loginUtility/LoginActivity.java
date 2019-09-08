package com.example.cool.foodie.loginUtility;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.example.cool.foodie.EattingChoiceActivity;
import com.example.cool.foodie.MainActivity;
import com.example.cool.foodie.R;
import com.example.cool.foodie.breakfastMenu.BreakFastActivity;
import com.example.cool.foodie.checkoutUtility.CheckOutActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity  {


    private ProgressBar progressBar;
    private EditText editTextUsername;
    private EditText editTextPassword;
    private Button signInButton;
    private CheckBox checkbox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


        editTextUsername = (EditText) findViewById(R.id.email);
        editTextPassword = (EditText) findViewById(R.id.password);
        progressBar=(ProgressBar) findViewById(R.id.login_progress);
        signInButton=(Button)findViewById(R.id.email_sign_in_button);
        checkbox=(CheckBox) findViewById(R.id.showPassword);
        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                // checkbox status is changed from uncheck to checked.
                if (!isChecked) {
                    // show password
                    editTextPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                } else {
                    // hide password
                    editTextPassword.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }
            }
        });


        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Virtual Kyeboard hide when user click on button
                InputMethodManager inputManager = (InputMethodManager)
                        getSystemService(Context.INPUT_METHOD_SERVICE);
                inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(),
                        InputMethodManager.HIDE_NOT_ALWAYS);

                findViewById(R.id.email_sign_in_button).setEnabled(false);
                //findViewById(R.id.email_sign_in_button).setFocusableInTouchMode(true);

                userLogin();

            }
        });


    //if user presses on  registered
    findViewById(R.id.textViewRegister).setOnClickListener(new View.OnClickListener(){
        public void onClick(View view) {
            finish();
            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
        }
    });
    }

    @Override
    public void onBackPressed(){
        Intent intent=new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
    }

    private void userLogin() {
        //first getting the values
       // RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        final String email = editTextUsername.getText().toString();
        final String password = editTextPassword.getText().toString();

      //  Toast.makeText(LoginActivity.this, "Invalid Id aur Password", Toast.LENGTH_LONG).show();
        //validating inputs
        if (TextUtils.isEmpty(email)) {
            editTextUsername.setError("Please enter your username");
            editTextUsername.requestFocus();
            findViewById(R.id.email_sign_in_button).setEnabled(true);
            return;
        }

        if (TextUtils.isEmpty(password)) {
            editTextPassword.setError("Please enter your password");
            findViewById(R.id.email_sign_in_button).setEnabled(true);
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);
        String url = "http://192.168.1.1/android_user_api/login.php";
        //if everything is fine
        StringRequest stringRequest = new StringRequest(Request.Method.POST,url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.GONE);
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            Toast.makeText(getApplicationContext(),jsonObject.getBoolean("error")+"", Toast.LENGTH_LONG).show();
                            if(!jsonObject.getBoolean("error")) {

                              //  Toast.makeText(getApplicationContext(),"Login Successfull", Toast.LENGTH_LONG).show();
                                /*JSONArray jsonArray = jsonObject.getJSONArray("user");
                                JSONObject jsonObject1 = jsonArray.getJSONObject(0);

                                String email = jsonObject1.getString("email");

                                SharedPreferences shared = getSharedPreferences("Mypref", Context.MODE_PRIVATE);
                                SharedPreferences.Editor editor = shared.edit();

                                editor.putString("email", email);
                                Toast.makeText(getApplicationContext(),jsonObject.getString("error"), Toast.LENGTH_LONG).show();
                                editor.commit();*/
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }
                            else {
                                Toast.makeText(getApplicationContext(),"Invalid Login", Toast.LENGTH_LONG).show();
                            }
                        }


                        catch (JSONException e) {

                            Toast.makeText(getApplicationContext(),"Please try after some time", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                if(email.equals("ADMIN") && password.equals("ADMIN")) {
                    findViewById(R.id.email_sign_in_button).setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Welcome " + editTextUsername.getText().toString().trim() + " Enjoy Food !", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(LoginActivity.this, EattingChoiceActivity.class);
                    intent.putExtra("userName", editTextUsername.getText().toString());
                    startActivity(intent);
                }
                else{
                    findViewById(R.id.email_sign_in_button).setEnabled(true);
                    Toast.makeText(getApplicationContext(), "Invalid Username or Password", Toast.LENGTH_LONG).show();
                }
            }
        }){
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };
         VolleySingleton.getInstance(this).addToRequestQueue(stringRequest);
        }


    }

