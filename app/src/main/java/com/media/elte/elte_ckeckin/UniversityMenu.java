package com.media.elte.elte_ckeckin;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UniversityMenu extends AppCompatActivity {
    UniversityMenu thisInstance;
    Intent i;
    ListView listview;
    String[] text;
    Integer[] imageId;
    Thread threadPopulation1;
    Thread threadPopulation2;

    @Override
    protected void onDestroy() {
        super.onDestroy();
        MainMenu.mProgressDialog.dismiss();
        threadPopulation1.interrupt();
        threadPopulation2.interrupt();
        text = null;
        imageId = null;
        listview = null;
        i = null;
        thisInstance = null;
    }

    public UniversityMenu()
    {
        //Long process
        threadPopulation1 = new Thread(new Runnable() {
            public void run() {
                try {
                    populateMenuStep1();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        threadPopulation1.start();
        //end long process
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_university_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        thisInstance = this;

        threadPopulation2 = new Thread(new Runnable() {
            public void run() {
                try {
                    while (imageId == null || text == null) Thread.sleep(100);
                    populateMenuStep2();
                    MainMenu.mProgressDialog.dismiss();
                } catch (Exception e) {
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
                }
            }
        });
        threadPopulation2.start();
    }

    protected void populateMenuStep1() {
        text = new String[] {
                "Neptun Code",
                "Nek Number",
                "Wifi Connection",
                "Registr. Office",
                "Student Card"
        };

        imageId = new Integer[] {
                R.drawable.neptun,
                R.drawable.nek,
                R.drawable.wifi,
                R.drawable.registrationoffice,
                R.drawable.studentcard
        };
    }

    protected void populateMenuStep2() {
        i = new Intent();
        listview = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, text, imageId);
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (text[+position]) {
                    case "Neptun Code":
                        i.setClass(thisInstance, NeptunCode.class);
                        startActivity(i);
                        break;
                    case "Nek Number":
                        i.setClass(thisInstance, NekNumber.class);
                        startActivity(i);
                        break;
                    case "Wifi Connection":
                        i.setClass(thisInstance, WifiConnection.class);
                        startActivity(i);
                        break;
                    case "Registr. Office":
                        i.setClass(thisInstance, RegistrationOffice.class);
                        startActivity(i);
                        break;
                    case "Student Card":
                        i.setClass(thisInstance, StudentCard.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }

}
