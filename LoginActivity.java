package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.Toolbar;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;

public class LoginActivity extends AppCompatActivity{
    private ArrayAdapter adapter1;
    private Spinner rebt;
    EditText et1, et2;
    Button but1, but2;
    String edit , edit1, empNo, nm;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        rebt = (Spinner) findViewById(R.id.rebt);
        adapter1 = ArrayAdapter.createFromResource(this, R.array.major, android.R.layout.simple_spinner_dropdown_item);
        rebt.setAdapter(adapter1);


        but1=(Button)findViewById(R.id.but1);
        but2=(Button)findViewById(R.id.but2);
        et1=(EditText)findViewById(R.id.et1);
        et2=(EditText)findViewById(R.id.et2);


        //로그인 버튼
        but1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String id = et1.getText().toString();
                String pw = et2.getText().toString();

                Response.Listener<String> responseListener = new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject JsonResponse = new JSONObject( response );
                            boolean success = JsonResponse.getBoolean( "success" );
                            if(success) {//로그인 성공시
                                JSONObject result = JsonResponse.getJSONObject("result");
                                String empNo = result.getString("empNo");
                                String id = result.getString( "id" );
                                String pw = result.getString( "pw" );
                                String nm = result.getString( "nm" );
                                Toast.makeText( getApplicationContext(), "로그인 성공", Toast.LENGTH_SHORT ).show();
                                Intent intent = new Intent( getApplicationContext(), menu.class );
                                intent.putExtra("empNo",empNo);
                                intent.putExtra( "id", id );
                                intent.putExtra( "pw", pw );
                                intent.putExtra("nm",nm);
                                startActivity( intent );

                            } else {//로그인 실패시
                                Toast.makeText( getApplicationContext(), "로그인 실패", Toast.LENGTH_SHORT ).show();
                                return;
                            }

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                };
                LoginRequest loginRequest = new LoginRequest( id, pw, responseListener );
                RequestQueue queue = Volley.newRequestQueue( LoginActivity.this );
                queue.add(loginRequest);

            }
        });

        //회원가입 버튼
        but2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent registerIntent = new Intent(LoginActivity.this,RegisteActivity.class);
                LoginActivity.this.startActivity(registerIntent);
                Toast.makeText(getApplicationContext(),"회원가입을 누르셨습니다.",Toast.LENGTH_SHORT).show();
            }
        });


    }
}
