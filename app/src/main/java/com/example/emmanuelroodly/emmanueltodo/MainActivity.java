package com.example.emmanuelroodly.emmanueltodo;

import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ArrayList<String> todoItems;
    ArrayAdapter<String> aToDoAdapter;
    ListView lvItems;
    EditText etEditText;
    private final int REQUEST_CODE = 20;
    //int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        populateArrayItems();
        lvItems = (ListView) findViewById(R.id.lvItems);
        lvItems.setAdapter(aToDoAdapter);
        etEditText = (EditText) findViewById(R.id.etEditText);
        lvItems.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener(){
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id)
            {
                todoItems.remove(position);
                aToDoAdapter.notifyDataSetChanged();
                writeItems();
                return true;
            }
        });
        lvItems.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id)
            {

                String bagayLan = todoItems.get(position).toString();
                Intent i = new Intent(MainActivity.this, change.class);
                final int to_give = position;
                i.putExtra("item", bagayLan);
                i.putExtra("pos", to_give);
                startActivityForResult(i, REQUEST_CODE);
                //startActivity(i);
            }
        });
    }





    public void populateArrayItems()
    {
        readItems();
        /*
        todoItems = new ArrayList<String>();
        todoItems.add("Item 1");
        todoItems.add("Item 2");
        todoItems.add("Item 3");
        todoItems.add("Item 4");*/
        aToDoAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, todoItems);
    }


    private void readItems()
    {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try
        {
            todoItems = new ArrayList<String>(FileUtils.readLines(file));
        }catch (IOException e){
            todoItems = new ArrayList<String>();
        }
    }

    private void writeItems()
    {
        File filesDir = getFilesDir();
        File file = new File(filesDir, "todo.txt");
        try
        {
            FileUtils.writeLines(file, todoItems);
        }catch (IOException e){
            e.printStackTrace();
        }
    }


    public void onAddItem(View view) {
        aToDoAdapter.add(etEditText.getText().toString());
        etEditText.setText("");
        writeItems();
    }


    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if(resultCode == RESULT_OK && requestCode == REQUEST_CODE)
        {
            //Bundle donnee = getIntent().getExtras();
            final int place = data.getExtras().getInt("pip", 0);
            String changement = data.getExtras().getString("element").toString();
            todoItems.set(place, changement);
            writeItems();

        }
    }


}
