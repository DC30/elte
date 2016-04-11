package com.media.elte.elte_ckeckin;

import android.annotation.TargetApi;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResidencePermitMenu extends AppCompatActivity {
    ResidencePermitMenu thisInstance;
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

    public ResidencePermitMenu()
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

        setContentView(R.layout.activity_residence_permit_menu);
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
        text = new String[] {
                "Application Form",
                "Data Sheet",
                "Passport",
                "Photo",
                "School Attend. Cert.",
                "Accom. Report. Form",
                "Financial Condition",
                "Health Insurance",
                "Voluntary Leave Decl.",
                "Residence Permit Decl.",
                "Fee"
        };
        imageId = new Integer[] {
                R.drawable.appform,
                R.drawable.datasheet,
                R.drawable.passport,
                R.drawable.photo,
                R.drawable.attendancecertificate,
                R.drawable.accommodation,
                R.drawable.financialcondition,
                R.drawable.healthinsurance,
                R.drawable.voluntaryleave,
                R.drawable.residencepermit,
                R.drawable.fee
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
                    case "Application Form":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Data-Sheet":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Passport":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Photo":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "School Attend. Cert.":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Accom. Report. Form":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Financial Condition":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Voluntary Leave Decl.":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Health Insurance":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Residence Permit Decl.":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                    case "Fee":
                        i.setClass(thisInstance, ApplicationForm.class);
                        startActivity(i);
                        break;
                }
            }
        });
    }
}
