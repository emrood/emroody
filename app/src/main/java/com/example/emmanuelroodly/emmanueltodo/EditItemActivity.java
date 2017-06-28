package com.example.emmanuelroodly.emmanueltodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;

public class EditItemActivity extends AppCompatActivity {

    EditText etModify;
    int placement;
    String myItem;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_item);
        etModify = (EditText) findViewById(R.id.etEditText);
        placement = getIntent().getIntExtra("position", 0);
        Bundle extras = getIntent().getExtras();
        if(extras != null){
            myItem = extras.getString("item");
            placement = extras.getInt("position");
            etModify.setText(myItem);
        }

    }

    public void onSaveItem(View view) {
        Intent data = new Intent();
        String modification = etModify.getText().toString();
        data.putExtra("modification", modification);
        data.putExtra("position", placement);
        setResult(RESULT_OK, data);
        EditItemActivity.this.finish();

    }
}
