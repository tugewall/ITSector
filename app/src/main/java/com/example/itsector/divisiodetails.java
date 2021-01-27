package com.example.itsector;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class divisiodetails extends AppCompatActivity {

    public static final String MY_PREFS_NAME = "MyPrefsFile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_divisiodetails);

        SharedPreferences prefs = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        String name = prefs.getString("name", "No name defined");//"No name defined" is the default value.
        String turn = prefs.getString("turn", "No turn defined");//"No name defined" is the default value.

        Toolbar myToolbar = (Toolbar) findViewById(R.id.Maintoolbar);
        setSupportActionBar(myToolbar);
        ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);
        TextView textView = (TextView)   myToolbar.findViewById(R.id.toolbar_title);
        textView.setText(name);


        TextView tv1 = findViewById(R.id.namedivision);
        TextView tv2 = findViewById(R.id.turndivison);
        tv1.setText(name);


        ImageView img= (ImageView) findViewById(R.id.imagelight);
        if(turn.equals("false")){
            img.setImageResource(R.drawable.lightoff);
            tv2.setText("OFF");
        }else{
            img.setImageResource(R.drawable.lighton);
            tv2.setText("ON");
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            onBackPressed();  return true;
        }

        return super.onOptionsItemSelected(item);
    }
}