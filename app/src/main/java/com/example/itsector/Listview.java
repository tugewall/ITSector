package com.example.itsector;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.HashMap;

import static java.security.AccessController.getContext;

public class Listview extends AppCompatActivity {

    DatabaseHelper mDatabaseHelper;
    private static final String TAG = "Listview";
    private ListView mListView;
    Switch mSwitch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview);

        mDatabaseHelper = new DatabaseHelper(this);
        mListView = (ListView) findViewById(R.id.ListDivisions);


    }




}