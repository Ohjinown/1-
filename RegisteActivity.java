package com.example.test1;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
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

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static com.loopj.android.http.AsyncHttpClient.log;

public class RegisteActivity extends AppCompatActivity {

    private ArrayAdapter adapter;
    private Spinner rebt1;
    private String successListener,errorListener;

    EditText reet6;
    DatePickerDialog.OnDateSetListener callbackMethod;
    Button rebt2,button;
    EditText reet1, reet2, reet3, reet4, reet5, reet7, reemail;
    String empNo, id, pw, nm;//EditText에 입력안했을떄 했을떄 틀리게 하는거



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registe);

        rebt1 = (Spinner) findViewById(R.id.rebt1);
        button =(Button) findViewById(R.id.but);
        adapter = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        rebt1.setAdapter(adapter);
        rebt2 = (Button) findViewById(R.id.rebt2);
        Object symbol;

        //URL
        final String url = "http://192.168.0.4:8080/mbr/member";


        reet1 = (EditText) findViewById(R.id.reet1);//사원번호
        reet2 = (EditText) findViewById(R.id.reet2);//아이디
        reet3 = (EditText) findViewById(R.id.reet3);//비밀번호
        reet4 = (EditText) findViewById(R.id.reet4);//이름
        rebt1 = (Spinner) findViewById(R.id.rebt1);//직책
        empNo = reet1.getText().toString();//EditText
        id = reet2.getText().toString();//EditText
        pw = reet3.getText().toString();//EditText
        nm = reet4.getText().toString();//EditText
        //URL

        //rebt2를 눌렀을때 실행되는 이벤트
        rebt2.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                reet1 = (EditText) findViewById(R.id.reet1);//사원번호
                reet2 = (EditText) findViewById(R.id.reet2);//아이디
                reet3 = (EditText) findViewById(R.id.reet3);//비밀번호
                reet4 = (EditText) findViewById(R.id.reet4);//이름
                rebt1 = (Spinner) findViewById(R.id.rebt1);//직책
                empNo = reet1.getText().toString();//EditText
                id = reet2.getText().toString();//EditText
                pw = reet3.getText().toString();//EditText
                nm = reet4.getText().toString();//EditText
                //URL
                if (empNo.trim().equals("") || id.trim().equals("") || pw.trim().equals("") || nm.trim().equals("")) {
                    Toast.makeText(getApplicationContext(), "다시 입력해주세요", Toast.LENGTH_LONG).show();
                } else {
                    RequestQueue queue = Volley.newRequestQueue(RegisteActivity.this);
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {
                                Log.d("Resonse", response);
                                JSONObject tmpResponse = new JSONObject(response);
                                if ((Boolean) tmpResponse.get("result")) {
                                    Toast.makeText(getApplicationContext(), "정상적으로 회원가입 했습니다", Toast.LENGTH_LONG).show();
                                } else {
                                    Toast.makeText(getApplicationContext(), "회원가입 실패하셨습니다.", Toast.LENGTH_LONG).show();
                                }

                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Log.d("Error.Response", error.getMessage());
                        }
                    }
                    ) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> map = new HashMap<String, String>();
                            map.put("empNo", empNo);
                            map.put("id", id);
                            map.put("pw", pw);
                            map.put("nm", nm);
                            return map;
                        }
                    };

                    queue.add(stringRequest);

                }
            }
            });
                    button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent registerIntent = new Intent(RegisteActivity.this,LoginActivity.class);
                            RegisteActivity.this.startActivity(registerIntent);
                            Toast.makeText(getApplicationContext(),"뒤로갑니다",Toast.LENGTH_LONG).show();
                        }
                    });


        }


}
