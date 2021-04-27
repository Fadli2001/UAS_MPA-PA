package com.example.loginvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    Button btn_login;
    EditText et_username,et_password;
    private String username,password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_login = findViewById(R.id.btn_login);
        et_username = findViewById(R.id.username);
        et_password = findViewById(R.id.password);
        username = password = "";
        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

    }


    public void login() {
        username = et_username.getText().toString().trim();
        password = et_password.getText().toString().trim();

        if (!username.equals("") && !password.equals("")) {
//            http://localhost/Semester2/Pengembanganaplikasi/pertemuan12/UAS/APIPOST/login.php
            StringRequest request = new StringRequest(Request.Method.POST, "http://192.168.0.22/Semester2/Pengembanganaplikasi/pertemuan12/UAS/API/login.php",
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            if (response.contains("success")) {
                                Intent inten = new Intent(getApplicationContext(),homepage.class);
                                inten.putExtra("NAME_SESSION", username);
                                startActivity(inten);

                            } else {
                                Toast.makeText(getApplicationContext(), "username atau passwrod salah", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                }
            }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("nama", et_username.getText().toString());
                    params.put("password", et_password.getText().toString());
                    return params;
                }
            };

            Volley.newRequestQueue(this).add(request);

        }else{
            Toast.makeText(getApplicationContext(), "Silahkan masukan username dan password", Toast.LENGTH_SHORT).show();
        }
    }

}