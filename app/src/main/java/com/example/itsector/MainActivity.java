package com.example.itsector;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity  {

    ArrayList<String> listData = new ArrayList<>();
    ArrayList<String> listData2 = new ArrayList<>();
    String[] stringarray;
    String[] stringarray2;

    DatabaseHelper mDatabaseHelper;
    private ListView mListView;
    private Switch switchturn;

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mDatabaseHelper = new DatabaseHelper(this);

        isFirstTime();
        Toolbar myToolbar = (Toolbar) findViewById(R.id.Maintoolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeButtonEnabled(true);

        mListView = (ListView) findViewById(R.id.ListDivisions);
        switchturn = (Switch) findViewById(R.id.switch1);
        populateListView();

        //Single click to check the division details
        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent, View view,int position, long id) {


                String getdivision = stringarray[position];
                String getturn = stringarray2[position];

                Intent myIntent = new Intent(MainActivity.this, divisiodetails.class);
                MainActivity.this.startActivity(myIntent);


                // SHARED PREFERENCES
                SharedPreferences.Editor editor = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE).edit();
                editor.putString("name", getdivision);
                editor.putString("turn", getturn);
                editor.apply();

            }


        });

        //LongClick to turn On or Off the lights
        mListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int position, long id) {


                String getdivision = stringarray[position];
                String getturn = stringarray2[position];

                if (getturn.equals("false")){
                    int updateData = mDatabaseHelper.UpdateSwitch(getdivision, "true", position+1);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }else{
                    int updateData = mDatabaseHelper.UpdateSwitch(getdivision, "false", position+1);
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(getIntent());
                    overridePendingTransition(0, 0);
                }


                return true;
            }
        });


            }




        //Function to populate the Listview
    private void populateListView(){

        //get the data and populate a list
        Cursor data = mDatabaseHelper.getData();

        while (data.moveToNext()){
            listData.add(data.getString(1));
            listData2.add(data.getString(2));
        }

        stringarray = listData.toArray(new String[0]);
        stringarray2 = listData2.toArray(new String[0]);

        mListView = (ListView)findViewById(R.id.ListDivisions);
        CustomAdapter customAdapter = new CustomAdapter(getApplicationContext(), stringarray, stringarray2);
        mListView.setAdapter(customAdapter);


    }


    //Function to Add the 4 required divisions on the database
    public void  AddData(String newEntry, String newEntry2){

        boolean insertData = mDatabaseHelper.addData(newEntry, newEntry2);

    }

    //Menu "creation"
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
                Intent myIntent = new Intent(MainActivity.this, newdivision.class);
                MainActivity.this.startActivity(myIntent);
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);

        }
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);

        return super.onCreateOptionsMenu(menu);
    }


    //Funtion toast to easly test features
    private void toastMessage(String message){
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    //Funtion to check if it is the first time, to add the 4 required divisions only the first time the app was installed
    private boolean isFirstTime()
    {
        SharedPreferences preferences = getPreferences(MODE_PRIVATE);
        boolean ranBefore = preferences.getBoolean("RanBefore", false);
        if (!ranBefore) {

            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("RanBefore", true);
            editor.commit();

            AddData("Kitchen", "false");
            AddData("Living Room", "false");
            AddData("Master bedroom", "false");
            AddData("Guest's bedroom", "false");

        }
        return ranBefore;

    }


}





