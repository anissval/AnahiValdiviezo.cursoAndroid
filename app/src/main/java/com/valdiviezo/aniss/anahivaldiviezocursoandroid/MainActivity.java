package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.app.Activity;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.PowerManager;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui.CameraActivity;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui.ImageActivity;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui.ListViewActivity;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui.LocationActivity;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui.RestActivity;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui.ThreadsActivity;

public class MainActivity extends Activity{


    private Button button_1;
    private Button button_2;
    private Button button_3;
    private Button button_4;
    private Button button_5;
    private Button button_6;
    private Button button_7;
    private Button button_8;
    private Button button_9;
    int time= 1000;

    MyBroadcastReceiver mybroadcast;
    private final int PICTURE_REQUEST_CODE = 2;
    private String fileName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mybroadcast = new MyBroadcastReceiver();
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_ON));
        registerReceiver(mybroadcast, new IntentFilter(Intent.ACTION_SCREEN_OFF));

        button_1 = (Button) findViewById(R.id.button);
        button_1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                Intent intent_1 = new Intent(getApplicationContext(), ImageActivity.class);
                startActivity(intent_1);
            }
        });
        button_2 = (Button) findViewById(R.id.button2);
        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
             Intent intent_2 =new Intent(getApplicationContext(), CameraActivity.class);
                startActivity(intent_2);
            }
        });

        button_3 = (Button) findViewById(R.id.button3);
        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ThreadsActivity.class);
                startActivity(i);
            }
        });

        button_4 = (Button) findViewById(R.id.button4);
        button_4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        button_5 = (Button) findViewById(R.id.button5);
        button_5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ImageDownloaderActivity.class);
                startActivity(i);

            }
        });
        button_6 = (Button) findViewById(R.id.button6);
        button_6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),ListViewActivity.class);
                startActivity(i);
            }
        });


        button_7 = (Button) findViewById(R.id.button7);
        button_7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),LocationActivity.class);
                startActivity(i);

            }
        });

        button_8 = (Button) findViewById(R.id.button8);
        button_8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),RestActivity.class);
                startActivity(i);
            }
        });

        button_9 = (Button) findViewById(R.id.button9);
        button_9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),IntegrantesListActivity.class);
                startActivity(i);

            }
        });

    }

    @Override
    protected void onStop() {

        super.onStop();

    }

    @Override
    protected void onDestroy() {
        this.unregisterReceiver(mybroadcast);
        super.onDestroy();
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
