package com.omar.dardear.gpstest;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.Locale;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Button Enter=(Button) findViewById(R.id.button);
        Enter.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {


                GPSTracker mGPS = new GPSTracker(MainActivity.this);
                if (mGPS.canGetLocation()) {
                    mGPS.location = mGPS.getLocation();
                    if (mGPS.getLatitude() == 0.0) {
                        Toast.makeText(MainActivity.this, "GPS just Opend please wait few seconds and try again", Toast.LENGTH_SHORT).show();
                    } else {

                        String uri = String.format(Locale.ENGLISH, "geo:%f,%f", mGPS.getLatitude(), mGPS.getLongitude());
                        Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                        startActivity(i);


                    }
                } else {
                    Toast.makeText(MainActivity.this, "Unable to Find Your Location Please Open GPS", Toast.LENGTH_SHORT).show();

                }

            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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
}
