package com.iternull.secureif;

/*
 *         ___
 *         ',_`""\        .---,
 *            \   :-""``/`    |
 *             `;'     //`\   /
 *             /   __     |   ('.
 *            |_ ./O)\     \  `) \
 *           _/-.    `      `"`  |`-.
 *       .-=; `                  /   `-.
 *      /o o \   ,_,           .        '.
 *      L._._;_.-'           .            `'-.
 *        `'-.`             '                 `'-.
 *            `.         '                        `-._
 *              '-._. -'                              '.
 *                 \                                    `\
 *                  |                                     \
 *                  |    |                                 ;   _.
 *                  \    |           |                     |-.((
 *                   ;.  \           /    /                |-.`\)
 *                   | '. ;         /    |                 |(_) )
 *                   |   \ \       /`    |                 ;'--'
 *                    \   '.\    /`      |                /
 *                     |   /`|  ;        \               /
 *                     |  |  |  |-._      '.           .'
 *                     /  |  |  |__.`'---"_;'-.     .-'
 *                    //__/  /  |    .-'``     _.-'`
 *                  jgs     //__/   //___.--''`
 */

import android.content.DialogInterface;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static java.lang.Runtime.*;

public class MainActivity extends AppCompatActivity {

    private Switch SwitchCharge, SwitchConnection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        SwitchCharge = (Switch) findViewById(R.id.switch_usbcharge);
        SwitchConnection = (Switch) findViewById(R.id.switch_usbdata);

        SwitchCharge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SwitchCharge.isChecked();
                if(check) try {
                    Process exec = getRuntime().exec(new String[]{"su", "-c", "echo 0 > /sys/class/power_supply/battery/charging_enabled"});
                    Toast.makeText(MainActivity.this, R.string.usb_charging_is_disabled, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                else try {
                    Process exec = getRuntime().exec(new String[]{"su", "-c", "echo 1 > /sys/class/power_supply/battery/charging_enabled"});
                    Toast.makeText(MainActivity.this, R.string.usb_charging_is_enabled, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        SwitchConnection.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean check = SwitchConnection.isChecked();
                if(check) try {
                    Process exec = getRuntime().exec(new String[]{"su", "-c", "echo 0 > /sys/devices/virtual/android_usb/android0/enable"});
                    Toast.makeText(MainActivity.this, R.string.usb_port_is_disabled, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                else try {
                    Process exec = getRuntime().exec(new String[]{"su", "-c", "echo 1 > /sys/devices/virtual/android_usb/android0/enable"});
                    Toast.makeText(MainActivity.this, R.string.usb_port_is_enabled, Toast.LENGTH_SHORT).show();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void onShowHelpDialog() {
        CharSequence styledText = Html.fromHtml(getString(R.string.text_help));
        AlertDialog ad = new AlertDialog.Builder(this)
                .setTitle(R.string.action_help)
                .setMessage(styledText)
                .setPositiveButton(R.string.action_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing.
                            }
                        }).create();
        ad.show();
        // Make links clickable.
        ((TextView)ad.findViewById(android.R.id.message)).setMovementMethod(
                LinkMovementMethod.getInstance());
    }

    private void onShowAboutDialog() {
        CharSequence styledText = Html.fromHtml(getString(R.string.text_about));
        AlertDialog ad = new AlertDialog.Builder(this)
                .setTitle(R.string.action_about)
                .setMessage(styledText)
                .setPositiveButton(R.string.action_ok,
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                // Do nothing.
                            }
                        }).create();
        ad.show();
        // Make links clickable.
        ((TextView)ad.findViewById(android.R.id.message)).setMovementMethod(
                LinkMovementMethod.getInstance());
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
        if (id == R.id.action_help) {
            onShowHelpDialog();
            return true;
        } else if (id == R.id.action_about) {
            onShowAboutDialog();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
