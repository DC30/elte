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
    //this is bundle is used to send bundle with the intent and it is initialized same as when intent is
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
                    Toast.makeText(getApplicationContext(), "Error", Toast.LENGTH_LONG).show();
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
                "Emergency Numbers"
        };

        imageId = new Integer[] {
                R.drawable.market,
                R.drawable.bbk,
                R.drawable.phonenumber,
                R.drawable.bankaccount,
                R.drawable.pubs,
                R.drawable.sos
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
                    case "Market":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Markets");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.494071);
                        extras.putDouble("LNG",19.0703975);
                        extras.putString("INFO", "<b>When you first arrive here you might not have a lot of information about the markets. So we recommend the following:</b>" +
                                "<p> <b>• Big markets. - </b>In Hungary there are big markets like SPAR, Tesco, CBA. In this markets you can find not just food, but also clothes, home items, etc.<br>" +
                                "They can be found in every corner of Budapest however the large ones are usually in malls like Arena Plaza, Allee Shopping Centre, etc. Be careful with the opening days and times, because most of them are closed on Sunday.</p> " +
                                "<p> <b>• Small markets. -</b>There are a small markets of the same brands but not big and there is less variety. Ask about the markets closer to you. Usually they are located in the main streets and near the squares. </p>" +
                                "<p> <b>• Exotic food. -</b>For the international students you will miss food from home. Therefore there are special market where you can find this kinds of food. </p>" +
                                "<p>Chinese Food.- Budapest, V&#225;mh&#225;zkrt. 5, 1093</p>" +
                                "<p>Indian Food.- Budapest, J&#243;zsefkrt. 23, 1085</p>"+
                                "<p>Latin Food.- Budapest, J&#243;zsefkrt. 23, 1085</p>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "BBK":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","BBK. ");
                        extras.putString("SHOWMAP","TRUE");
                        extras.putDouble("LAT", 47.498592);
                        extras.putDouble("LNG",19.0674548);
                        extras.putString("INFO", "This is the name of the company that control the public and local transportation in Budapest. The public transportation consists of buses, trains, trams, etc. You can buy individual tickets (Single use tickets that needs validation when used) or a card for one, three or six months. You will get discount if you buy a card" +
                                        "<p>Students have a special discounts only if when buying cards. You can buy this tickets in any BKK office usually found near metro stops, finding English speaking employees really depend on your luck but through our experience they usually understand what you need or in a machine also available near any transportation stop and there is English option in the machines.  To get the discount you need to provide the digit the number of your students card (is not necessary if it is the plastic student card or the temporary student card, you just need the number). </p>" +
                                        "<p>In the tram or in some buses you don’t need to show the ticket/card all the time. But there are BBK employees that check the card or the tickets and if you don’t have or you didn’t validate your ticket they will give you a ticket with penalty which you would need to pay directly in that moment. So please be careful, don’t cheat.</p>" +
                                        "<b>More information:</b><br>" +
                                        "<a href=\"http://www.bkk.hu/en/tickets-and-passes\">Tickets and passes </a>"+
                                        "<br>" +
                                        "Map: office<br> ");;
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Phone number":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Phone number");
                        extras.putString("SHOWMAP", "FALSE");
                        extras.putString("INFO", "Customers can choose to either prepay for their phone usage or purchase a monthly payment plan. Both payment methods have advantages and disadvantages and should be weighed based on a customer's need. Major providers are: "+
                        "<p><a href=\"http://www.telekom.hu\">T-Mobile </a> <br>"+
                        "<a href=\"https://www.telenor.hu\">Telenor </a> <br>"+
                        "<a href=\"http://www.vodafone.hu/magyar\">Vodafone </a></p>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Bank Account":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE", "Bank Account");
                        extras.putString("SHOWMAP", "FALSE");
                        extras.putString("INFO", "Most banks will allow you to open a basic account with just your passport or national ID card. If you have a rental agreement and proof of employment/earnings then it’s advisable to take these with you. A Hungarian phone number may also be requested to give you access to online banking. " +
                                        "<p>There are lots of banks to choose from:</p>" +
                                        "<p><a href=\"https://www.otpbank.hu/portal/en/Home\">OTP </a> <br>" +
                                        "<a href=\"https://www.mkb.hu\">MKB </a> <br>" +
                                        "<a href=\"https://www.citibank.hu/gcb/Lakossagi_ugyfelek/english/home/index.htm\">CitiBank </a> <br>" +
                                        "<a href=\"http://www.budapestbank.hu\">Budapest Bank </a> <br>" +
                                        "<a href=\"https://www.db.com/hungary/index_en.html\">Deutsche Bank</a> <br> " +
                                        "<a href=\"http://www.erstebank.hu/en/english\">Erste Bank </a> <br>" +
                                        "<a href=\"http://www.cib.hu/index.xml?defaultLanguage=english\">CIB Bank </a> <br>" +
                                        "<a href=\"http://en.fhb.hu/fhb-bank\">Fhb</a></p> <br>" +
                                        "<p>NOTE! Remember that you'll need your passport and address card</p>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Pubs":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE", "Pubs");
                        extras.putString("SHOWMAP", "FALSE");
                        extras.putString("INFO", "Now you’ve got through all the paperwork and hassles of moving to a new country, it’s time to make some friends. There are plenty of expat clubs and hangouts where you can feel less lonely in a new city." +
                                        "<p>To meet people, particularly English speakers and international students, many international students and some locals also hang out at these popular hot spots</p>" +
                                        "<b>When?</b> Every night <br>" +
                                        "<b>Where?</b> SzimplaKert, Kazinczy Street 14, VII District/ Instant, Nagymez&#240; Street 38 VI District <br>" +
                                        "<b>Cost?</b>Free <br>" +
                                        "<b>Tip: </b> If you’re looking to meet Hungarians rather than backpackers, then go to FogasH&#225;z nearby for a similar atmosphere. <br>" +
                                        "<p>Now you’re ready to enjoy a new life in one of Europe’s most exciting cities!</p>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Emergency Numbers":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE", "Emergency Numbers");
                        extras.putString("SHOWMAP", "FALSE");
                        extras.putString("INFO", "o Ambulance: 104. <br>" +
                                        "• Police: 107. <br>" +
                                        "• Fire service: 105. <br>" +
                                        "• Central help number: 112. <br>" +
                                        "• General enquiries: 197. <br>" +
                                        "• Domestic enquiries: 198. <br>" +
                                        "• International enquiries: 199. <br>"+
                                        "• Tourist Police (0-24): 06-1-438-8080. <br>" +
                                        "• 24-hour medical assistance in English (Falck SOS Hungary): 06-1-2400-475. <br>" +
                                        "• 24- hour pharmacy: District 6, Terézkrt 41. (telephone: 06-1-311-4439) near Oktogon. <br>" +
                                        "• Airport Ferihegy - general (flight information) number: 06-1-296-7155.<br>"+
                                        "• Information about local and international trains: M&#193;VDIREKT 06-40-49-4949.");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                }
            }}
        );
    }
}