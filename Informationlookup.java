package com.example.test1;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class Informationlookup extends AppCompatActivity {
    private TextView textView, textView1, textView3;
    private Button but1, but;
    private EditText et;
    String pw, empNo,changepw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_information_lookup);
        but1 = findViewById(R.id.but1);//버튼 아이디
        but = findViewById(R.id.but);//정보 저장

        final TextView textView = findViewById(R.id.text);//사원 번호
        final TextView textView1 = findViewById(R.id.text1);//ID
        final EditText et = findViewById(R.id.et);
        final TextView textView3 = findViewById(R.id.text3);//NAME


        Intent intent = getIntent();

        empNo = intent.getExtras().getString("empNo");
        textView.setText(empNo);

        String id = intent.getExtras().getString("id");
        textView1.setText(id);

        pw = intent.getExtras().getString("pw");
        et.setText(pw);

        String nm = intent.getExtras().getString("nm");
        textView3.setText(nm);

        but.setOnClickListener(new View.OnClickListener() {
            String url ;
            @Override
            public void onClick(View view) {
                pw = et.getText().toString();
                if (pw.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "정보 수정 실패", Toast.LENGTH_LONG).show();
                } else {
                    url = "http://localhost:8080/mbr/change";
                     RequestQueue queue = Volley.newRequestQueue(Informationlookup.this);
                    StringRequest putRequest = new StringRequest(Request.Method.PUT, url,
                            new Response.Listener<String>() {
                                @Override
                                public void onResponse(String response) {
                                    // response
                                    Log.d("Response", response);
                                }
                            },
                            new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    // error
                                    Log.d("Error.Response", error.getMessage());
                                }
                            }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<String, String>();
                            params.put("pw", pw);
                            params.put("empNo", empNo);
                            return params;
                        }

                    };

                    queue.add(putRequest);
                }
                Toast.makeText(getApplicationContext(), "정보 수정 성공", Toast.LENGTH_LONG).show();
            }
        });
        but1.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View view){
                Toast.makeText(getBaseContext(), "뒤로가기", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }
}
