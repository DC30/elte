package com.media.elte.elte_ckeckin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class UniversityMenu extends AppCompatActivity {
    UniversityMenu thisInstance;
    Intent i;
    Bundle extras;
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
                "Tax Number",
                "Questura Office",
                "Student Card"
        };

        imageId = new Integer[] {
                R.drawable.neptun,
                R.drawable.nek,
                R.drawable.wifi,
                R.drawable.taxservices,
                R.drawable.registrationoffice,
                R.drawable.studentcard
        };
    }

    protected void populateMenuStep2() {
        i = new Intent();
        extras = new Bundle();
        listview = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, text, imageId);
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (text[+position]) {
                    case "Neptun Code":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Neptun code");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","This code is very important, because you will use it to register your subjects for each semester and the professors give you the grades. <br>" +
                                "<br>" +
                                "To get it you must:<br>" +
                                "\t \u2022 Go Questura office <br>" +
                                "\t • Give them your passport<br>" +
                                " \t • Then you receive the document with your code and password.<br>" +
                                "<br>" +
                                "If you have some problems, go to the faculty coordinator and ask him/her, because he/she is responsible for registration in this system before your arrival.<br>" +
                                "This process is the same to students with scholarship or not.<br>" +
                                "<br>" +
                                "Map: questura office<br>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                    //    startActivity(i);
                        break;
                    case "Nek Number":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Nek Number");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.4895511);
                        extras.putDouble("LNG",19.0721877);
                        // here the bundle is attached to the intent
                        extras.putString("INFO","This number is necessary to have so you can get the permanent student card for students who stay more than 12 months. <br>" +
                                "To get it you must:<br>" +
                                "\t • Choose the Government office<br>" +
                                "\t • You need your  passport<br>" +
                                "\t • They will take your photo <br>" +
                                "\t • They will request some information.<br>" +
                                "\t • Then you get a form with a unique code in the top right corner called NEK identifier<br>" +
                                "Please double check your information on the issued NEK-document! The information on the NEK document have to be exactly the same as the information registered in the Neptun system (if not, your student card request will be rejected).<br>" +
                                "\t <b>More information:</b><br><a href=\"http://www.kormanyhivatal.hu/hu/budapest/jarasok/bfkh-okmanyirodai\">" +
                                "http://www.kormanyhivatal.hu/hu/budapest/jarasok/bfkh-okmanyirodai</a> <br> Map: Government office 1073 Budapest VII., Erzsébet krt. 6");
                        i.putExtras(extras);
                        startActivity(i);

                        break;
                    case "Wifi Connection":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Wifi Connection");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.4750299);
                        extras.putDouble("LNG",19.0611472);
                        extras.putString("INFO","When you are at ELTE you have free wifi. But the website you need to connect to the Wifi is only in Hungarian Language, so for that we recommend you to go directly to the office where there are Hungarian student that can help you. This office is in the Faculty of Science in the North Building in the room (6.126).<br>" +
                                "<br>" +
                                "But if you want to try for yourself go to the next steps in this website.<br>" +
                                "<br>" +
                                "<b>More information.-</b><br>" +
                                "<a href=\"http://iig.elte.hu/file/Setting_WIFI_connection.pdf\"> " +
                                "http://iig.elte.hu/file/Setting_WIFI_connection.pdf</a><br>" +
                                "<br>" +
                                "Map: office connection<br> ");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);

                        break;
                    case "Tax Number":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Tax Number");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.474957);
                        extras.putDouble("LNG",19.0733125);
                        extras.putString("INFO", "This number is about the tax in Hungary. To get this number you need to do the following steps:<br>" +
                                "\t • Go to the office of National Tax and Customs Administration of Hungary<br>" +
                                "\t • Give them your passport<br>" +
                                "\t • You need to fill the form in Hungarian. Obviously somebody will help you. You just need to give them your information.<br>" +
                                "\t • Immediately you receive this code and now please send this code to Quaestura office. You can send an email (quaestura@elte.hu) or go personally to the office.<br>" +
                                "Please check the opening hours. For more information please check <br>" +
                                "<a href=\"http://en.nav.gov.hu/contact/tax_contact/chrd.htm\"> " +
                                "http://en.nav.gov.hu/contact/tax_contact/chrd.htm</a><br>" +
                                "Map:  office tax<br>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);

                        startActivity(i);
                        break;
                    case "Questura Office":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Questura Office");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO", "In this office you can do several processes, like student card, question about neptun account, etc.<br>" +
                                "In this application we show you the main Questura office, but don’t forget to ask about the branches of the Questura office, because there are some in some faculties.<br>" +
                                "<br>" +
                                "<b>More information.-</b><br>" +
                                "<a href=\"https://qter.elte.hu/default.aspx\"> " +
                                "https://qter.elte.hu/default.aspx</a><br>" +
                                "<br>" +
                                "<br>" +
                                "Map: principal questiura office<br>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);

                        startActivity(i);
                        break;
                    case "Student Card":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Student Card");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.4895511);
                        extras.putDouble("LNG",19.0721877);
                        extras.putString("INFO","<b>Short term </b>- Students staying less than 12 months receive a temporary student certificate. You need to go to Quaestura Office and make your request there. You receive your temporary student certificate right there immediately. <br>" +
                                "<br>" +
                                "<b>Long term </b>- Students staying more than 12 months can request a plastic student card. It takes a few months until you receive this plastic student card (around 6 months). Therefore, we advise you to request also a temporary student certificate (valid for 60 days, each 60 days you need to renew it) at Quaestura Office. Please note that you have to pay a certain amount of money.<br>" +
                                "•\t<b>step 1</b>: Go to an Office of Government Issued Documents (short term: Registration Office; in Hungarian: Okmányiroda) and apply for a student card <br>" +
                                "•\t<b>step 2</b>: You need to register your application electronically in the Neptun system <br>" +
                                "•\tGo to Administration -> Student Card request ->  Add new then <br>" +
                                "•\tType in your NEK code correctly without any hyphens.<br>" +
                                "•\tSelect the reason of your request (e.g. first application, due to data change, lost, new request due to false data).<br>" +
                                "•\tSelect your permanent address in your home country. <br>" +
                                "<b>More information:</b><br>" +
                                "<a href=\"http://www.elte.hu/file/Student_card_20160128_NI.pdf\"> " +
                                "http://www.elte.hu/file/Student_card_20160128_NI.pdf</a><br>" +
                                "<br>" +
                                "Map: office<br> ");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);

                        break;
                }
            }
        });
    }

}
