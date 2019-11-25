package com.shoppinglist.shoppinglist;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddItem extends AppCompatActivity {

    Button addButton;
    EditText textField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        addButton = findViewById(R.id.addButton);
        textField = findViewById(R.id.textField);
    }

    public void onAddItem(View view) {
        String newItemName = textField.getText().toString();
        Intent intent = new Intent();
        intent.putExtra("someValue", newItemName);
        setResult(RESULT_OK, intent);
        finish();
    }
}
