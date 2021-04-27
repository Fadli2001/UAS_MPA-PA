package com.example.loginvolley;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class homepage extends AppCompatActivity {

    //deklarasi sebuah data
    private TextView txtJSON;
    private RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);

        mQueue = Volley.newRequestQueue(this);

//        bindding id
        txtJSON = findViewById(R.id.txtJson);

//        simpan nama fungsi uraijson ke dalam fungsi oncreate untuk menampilan data langsung stelah login
        uraiJson();

    }

    private void uraiJson() {
        String username = getIntent().getStringExtra("NAME_SESSION");
        String url = "http://192.168.0.22/Semester2/Pengembanganaplikasi/pertemuan12/UAS/API/homepage.php?nama=" + username;

        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject obj = response.getJSONObject("data");
                            String tampil="Nama : "+obj.getString("nama") + "\n" + "Hobby : ";
                            txtJSON.append(tampil);
                            JSONArray hobi = obj.getJSONArray("kegemaran");
                            for (int i = 0; i < hobi.length(); i++) {
                                txtJSON.append(hobi.getString(i) + ", ");

                            }
                        }catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(homepage.this,"Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
                Toast.makeText(homepage.this,"" + error, Toast.LENGTH_SHORT).show();
            }
        });
        mQueue.add(request);
    }
}