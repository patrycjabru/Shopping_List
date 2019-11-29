package com.shoppinglist.shoppinglist;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItemActivity extends AppCompatActivity {
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
        db.insertData(newItemName);
        navigateUpTo(new Intent(this, ShoppingItemListActivity.class));
    }
}
