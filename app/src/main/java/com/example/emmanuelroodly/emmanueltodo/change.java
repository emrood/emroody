package com.example.emmanuelroodly.emmanueltodo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class change extends AppCompatActivity {

    EditText edit_box;
    String mon_text;
    TextView textView;

    int return_position;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change);
        edit_box = (EditText) findViewById(R.id.edit_box);
        textView = (TextView) findViewById(R.id.textView);

        mon_text = getIntent().getStringExtra("item").toString();
        final int position = getIntent().getIntExtra("pos", 0);
        return_position = position;
        edit_box.setText(mon_text);


    }

    public void onReturn(View view) {
        Intent data = new Intent();
        data.putExtra("element", edit_box.getText().toString());
        data.putExtra("pip", return_position);
        setResult(change.RESULT_OK, data);
        change.this.finish();
    }
}
