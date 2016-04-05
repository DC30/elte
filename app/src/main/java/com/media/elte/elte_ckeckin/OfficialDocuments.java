package com.media.elte.elte_ckeckin;

import android.app.Activity;
import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.TabHost;

import java.util.logging.Level;

/**
 * Created by wstev on 4/5/2016.
 */
public class OfficialDocuments extends TabActivity {
    TabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.official_documents);

        //tabHost = (TabHost)findViewById(android.R.id.tabhost);
        tabHost = getTabHost();

        TabHost.TabSpec tsNeptunCode = tabHost.newTabSpec("neptunCode");
        tsNeptunCode.setIndicator("Neptun Code");
        tsNeptunCode.setContent(new Intent(this, NeptunCode.class));
        tabHost.addTab(tsNeptunCode);

        TabHost.TabSpec tsNekNumber = tabHost.newTabSpec("nekNumber");
        tsNekNumber.setIndicator("Nek Number");
        tsNekNumber.setContent(new Intent(this,NekNumber.class));
        tabHost.addTab(tsNekNumber);

        TabHost.TabSpec tsWifiConnection = tabHost.newTabSpec("wifiConnection");
        tsWifiConnection.setIndicator("Wifi Connection");
        tsWifiConnection.setContent(new Intent(this, WifiConnection.class));
        tabHost.addTab(tsWifiConnection);

        TabHost.TabSpec tsRegistrationOffice = tabHost.newTabSpec("registrationOffice");
        tsRegistrationOffice.setIndicator("Registration Office");
        tsRegistrationOffice.setContent(new Intent(this, RegistrationOffice.class));
        tabHost.addTab(tsRegistrationOffice);

        TabHost.TabSpec tsStudentCard = tabHost.newTabSpec("studentCard");
        tsStudentCard.setIndicator("Stundet Card");
        tsStudentCard.setContent(new Intent(this, StudentCard.class));
        tabHost.addTab(tsStudentCard);
    }

}
