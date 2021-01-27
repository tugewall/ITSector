package com.example.itsector;


import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v4.app.Fragment;
import java.util.ArrayList;
import java.util.zip.Inflater;

public class CustomAdapter extends BaseAdapter {
    Context context;
    String division[];
    String switchs[];
    LayoutInflater inflter;
    DatabaseHelper mDatabaseHelper;

    public CustomAdapter(Context applicationContext, String[] division, String[] switchs) {
        this.context = context;
        this.division = division;
        this.switchs = switchs;
        inflter = (LayoutInflater.from(applicationContext));

    }

    @Override
    public int getCount() {

        return division.length;

    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.activity_listview, null);
        TextView country = (TextView)  view.findViewById(R.id.textView1);
        final Switch switches = (Switch)    view.findViewById(R.id.switch1);

        country.setText(division[i]);
        final boolean stat = Boolean.parseBoolean(switchs[i]);

        switches.setChecked(stat);

        switches.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {



            }
        });


/*
*
* */

        return view;
    }



}