package com.iternull.secureif;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    private Switch SwitchCharge, SwitchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        SwitchCharge = (Switch) findViewById(R.id.switch_usbcharge);
        SwitchData = (Switch) findViewById(R.id.switch_usbdata);

        SwitchCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SwitchCharge.isChecked();
                // java.lang.Process p = null;
                if(check){
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "echo 0 > /sys/class/power_supply/battery/charging_enabled"});
                        Toast.makeText(MainActivity.this, "USB Power is Disabled", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "echo 1 > /sys/class/power_supply/battery/charging_enabled"});
                        Toast.makeText(MainActivity.this, "USB Power is Enabled", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        SwitchData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SwitchData.isChecked();
                // java.lang.Process p = null;
                if(check){
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "echo 0 > /sys/devices/virtual/android_usb/android0/enable"});
                        Toast.makeText(MainActivity.this, "USB Port is Disabled", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }else {
                    try {
                        Runtime.getRuntime().exec(new String[]{"su", "-c", "echo 1 > /sys/devices/virtual/android_usb/android0/enable"});
                        Toast.makeText(MainActivity.this, "USB Port is Enabled", Toast.LENGTH_SHORT).show();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
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
