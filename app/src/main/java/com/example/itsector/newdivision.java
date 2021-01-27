package com.example.itsector;

import android.database.Cursor;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class newdivision extends AppCompatActivity {


    private Button addbtn;
    private EditText txtnew;
    String newdev;
    DatabaseHelper mDatabaseHelper;
    ArrayList<String> listData = new ArrayList<>();
    ArrayList<String> listData2 = new ArrayList<>();
    private ListView mListView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newdivision);

        mDatabaseHelper = new DatabaseHelper(this);
        mListView = (ListView) findViewById(R.id.ListDivisions);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.Maintoolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);



    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public void  AddData(String newEntry, String newEntry2){

        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry2);

        if(insertData){
            toastMessage("Data Successfully Inserted");
        }else{
            toastMessage("Something went wrong");
        }

    }




    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    public void addnew(View view) {

        txtnew  = (EditText) findViewById(R.id.newdevtext);
        String name = txtnew.getText().toString();


       AddData(name, "false");
       populateListView();
        finish();

    }


    private void populateListView(){

        //get the data and populate a list
        Cursor data = mDatabaseHelper.getData();

        while (data.moveToNext()){
            listData.add(data.getString(1));
            listData2.add(data.getString(2));
        }

        String[] stringarray = listData.toArray(new String[0]);
        String[] stringarray2 = listData2.toArray(new String[0]);

        mListView = (ListView)findViewById(R.id.ListDivisions);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), stringarray, stringarray2);
        mListView.setAdapter(customAdapter);


    }


}