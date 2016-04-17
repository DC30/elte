package com.media.elte.elte_ckeckin;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainMenu extends AppCompatActivity {

    Intent i = new Intent();
    protected static ProgressDialog mProgressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickAnyButton(View button)
    {
        mProgressDialog = ProgressDialog.show(this, "Please wait", "Loading menu...", true);
        switch (button.getId())
        {
            case R.id.btnResidencePermit:
                i.setClass(this,ResidencePermitMenu.class);
                startActivity(i);
                break;
            case R.id.btnUniversity:
                i.setClass(this,UniversityMenu.class);
                startActivity(i);
                break;
            case R.id.btnLifeStyle:
                i.setClass(this,LifeStyleMenu.class);

                startActivity(i);
                break;
            default:
                mProgressDialog.dismiss();
        }
    }
}
