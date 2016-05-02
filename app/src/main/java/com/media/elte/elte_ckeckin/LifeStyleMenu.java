package com.media.elte.elte_ckeckin;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class LifeStyleMenu extends AppCompatActivity {

    LifeStyleMenu thisInstance;
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

    public LifeStyleMenu()
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

        setContentView(R.layout.activity_life_style_menu);
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
                }
            }
        });
        threadPopulation2.start();
    }

    protected void populateMenuStep1() {
        text = new String[]{
                "Market",
                "BBK",
                "Phone number",
                "Bank Account",
                "Pubs",
                "Erasmus"
        };

        imageId = new Integer[] {
                R.drawable.market,
                R.drawable.bbk,
                R.drawable.phonenumber,
                R.drawable.bankaccount,
                R.drawable.pubs,
                R.drawable.erasmus
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
                    case "Market":
                        i.setClass(thisInstance, GenericMaps.class);
                        startActivity(i);
                        break;
                    case "BBK":
                        i.setClass(thisInstance, GenericMaps.class);
                        startActivity(i);
                        break;
                    case "Phone number":
                        i.setClass(thisInstance, GenericMaps.class);
                        startActivity(i);
                        break;
                    case "Bank Account":
                        i.setClass(thisInstance, GenericMaps.class);
                        startActivity(i);
                        break;
                    case "Pubs":
                        i.setClass(thisInstance, GenericMaps.class);
                        startActivity(i);
                        break;
                    case "Erasmus":
                        i.setClass(thisInstance, GenericMaps.class);
                        startActivity(i);
                        break;
                }
            }}
        );
    }
}