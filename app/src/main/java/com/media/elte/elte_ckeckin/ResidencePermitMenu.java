package com.media.elte.elte_ckeckin;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

public class ResidencePermitMenu extends AppCompatActivity {
    ResidencePermitMenu thisInstance;
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
        extras = new Bundle();
        listview = (ListView) findViewById(R.id.listView);
        CustomAdapter adapter = new CustomAdapter(this, text, imageId);
        listview = (ListView) findViewById(R.id.listView);
        listview.setAdapter(adapter);

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                i = new Intent();
                switch (text[+position]) {
                    case "Application Form":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Application form");
                        extras.putDouble("LAT",47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","<b>Just for the First Time and if you don’t have D-Visa</b>" +
                            "<p>You need fill it in the next form.</p>" +
                            "<a href=\"http://www.bmbah.hu/images/formanyomtatvanyok-teljes/ENG/pdf/Residence%20Permit%20for%20the%20Purpose%20of%20Studies.pdf\">Application Form</a>");
                       // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Data Sheet":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Data Sheet");
                        extras.putDouble("LAT", 47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","<p>If you have already applied for a residence permit and you have a D-visa.</p>" +
                             "<p>You need to fill the next form.</p>" +
                             "<a href=\"http://csfhungary.hu/wp-content/uploads/2014/08/Datasheet-for-residence-permit.pdf\">Residence Permit DataSheet</a>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Passport":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Passport");
                        extras.putDouble("LAT", 47.500061);
                        extras.putDouble("LNG",19.055139);
                        extras.putString("INFO", "Bring your passport and take a copy of it. ");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Photo":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Photo");
                        extras.putDouble("LAT", 47.500061);
                        extras.putDouble("LNG",19.080257);
                        extras.putString("INFO", "You must bring your photo or you can take the picture at the immigration office. Note that it is not free ");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "School Attend. Cert.":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","School attendance certificate");
                        extras.putDouble("LAT", 47.500061);
                        extras.putDouble("LNG",19.055139);
                        extras.putString("INFO", "This document must give you the coordinator of you faculty or coordinator of you bachelor or master degree. Please ask them for this document. ");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Accom. Report. Form":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Accom. Report. Form");
                        extras.putDouble("LAT",47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","<p style=\"text-align:justify\">Please ask for an empty " +
                                "form to your faculty coordinator or at ELTE International office. Please " +
                                "fill out the entire form and then you need to get the signature. If you live " +
                                "in a dormitory this form should be signed by the administrator of the dormitory, " +
                                "if you live in a flat the signature must be of the landlord.</p>" +
                                "<b>More information:</b><br>" +
                                "<a href=\"http://www.elte.hu/en/visa\"> http://www.elte.hu/en/visa</a>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Financial Condition":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Financial Condition");
                        extras.putDouble("LAT",47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","<p style=\"text-align:justify\"><b>Scholarship.- </b>" +
                                "You need to show them the certificate of your scholarship.</p>" +
                                "<p style=\"text-align:justify\"><b>Non-Scholarship.- </b>" +
                                "You need to show them your bank account statement, or a bank account " +
                                "statement of your guardian and a declaration from your parents that " +
                                "they support you in a financial way, etc.</p>" +
                                "<b>More information:</b><br>" +
                                "<a href=\"http://www.elte.hu/en/visa\"> http://www.elte.hu/en/visa</a>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Voluntary Leave Decl.":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Voluntary Leave Declaration");
                        extras.putDouble("LAT", 47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","Fill the form when you are at the Office of Immigration and Nationality");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Health Insurance":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Health Insurance");
                        extras.putDouble("LAT",47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","<p style=\"text-align:justify\">Any health insurance valid in the territory of EU.</p>" +
                                "<p style=\"text-align:justify\"><b>Scholarship.- </b>" +
                                "If you have scholarship, ELTE International office will give you this document.</p>" +
                                "<p style=\"text-align:justify\"><b>Non-Scholarship.- </b>" +
                                "You have to make an appointment with health insurance staff (She/He will come to the " +
                                "university) and talk to you, ask you some questions, then he/she will provide you the " +
                                "health insurance paper.</p>" +
                                "<p><b>NB.- You will need your passport.</b></p>" +
                                "<b>More information:</b><br>" +
                                "<a href=\"http://www.elte.hu/en/visa\"> http://www.elte.hu/en/visa</a>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Residence Permit Decl.":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Receive your residence permit ");
                        extras.putDouble("LAT", 47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO", "• This is a declaration of how would you like to receive your residence permit. " +
                                "<br>" +
                                "<b>Collection. – </b> have to go back to the office and collect it. For students that had the visa from their home country, generally the residence permit is ready, if not ready, you should pick it up later. <br>" +
                                "<b>Posta. -</b> You can receive a form and fill it at Office of Immigration and Nationality. Then when the resident card is ready, they send it your home.");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                    case "Fee":
                        i.setClass(thisInstance, GenericMaps.class);
                        extras.clear();
                        // this is used same as map key <=> value
                        extras.putString("TITLE","Fee");
                        extras.putDouble("LAT", 47.492356);
                        extras.putDouble("LNG",19.0560739);
                        extras.putString("INFO","<p style=\"text-align:justify\"><b>Scholarship.- </b>" +
                                "Students with Hungarian governmental scholarship, e.g. Stipendium " +
                                "Hungaricum, do not need to pay for the fee stamps, in this case please " +
                                "bring with you any scholarship certificate, e.g. Letter of Award " +
                                "as a Stipendium Hungaricum student!</p>" +
                                "<p style=\"text-align:justify\"><b>Non-scholarship.- </b>" +
                                "The fee stamps can be bought at any larger Hungarian post office. " +
                                "Please note that stamp duties cannot be purchased on location. Please " +
                                "request more information before you purchase the stamp.</p>");
                        // here the bundle is attached to the intent
                        i.putExtras(extras);
                        startActivity(i);
                        break;
                }
            }
        });
    }
}
