package com.shoppinglist.shoppinglist;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class AddItemActivity extends AppCompatActivity {
    private final String IP_ADDR = "192.168.1.32";

    DatabaseHelper db;
    Button addButton;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        db = new DatabaseHelper(this);

        addButton = findViewById(R.id.addButton);
        textField = findViewById(R.id.textField);
    }

    public void onAddItem(View view) {
        String newItemName = textField.getText().toString();
        System.out.println("Read data from input box: "+newItemName);

        makeAPICall(newItemName);

        navigateUpTo(new Intent(this, ShoppingItemListActivity.class));
    }

    public void makeAPICall(String query) {
        // Instantiate the RequestQueue.
        RequestQueue queue = Volley.newRequestQueue(this);
        String url = null;

        try {
            url = "http://" + IP_ADDR + ":3000/get-search-count?query="+ URLDecoder.decode(query, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        final long id = db.insertData(query);

        // Request a string response from the provided URL.
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Integer result = Integer.parseInt(response);
                        System.out.println("response is " + result);

                        db.updateNumberOfItems(id, result);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                System.err.println("fetch error");
                System.err.println(error);
            }
        });

        // Add the request to the RequestQueue.
        queue.add(stringRequest);
    }
}
