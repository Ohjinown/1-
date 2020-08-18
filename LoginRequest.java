package com.example.test1;

import androidx.annotation.Nullable;

import com.android.volley.Response;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class LoginRequest extends StringRequest{
    final static private String url = "http://192.168.0.4:8080/mbr/login";
    private Map<String,String> parameters;


    public LoginRequest(String id, String pw, Response.Listener<String> listener) {
        super(Method.POST, url, listener,null);
        parameters = new HashMap<>();
        parameters.put("id",id);
        parameters.put("pw",pw);
    }

    @Override
    public Map<String,String> getParams() {return  parameters;}
}
