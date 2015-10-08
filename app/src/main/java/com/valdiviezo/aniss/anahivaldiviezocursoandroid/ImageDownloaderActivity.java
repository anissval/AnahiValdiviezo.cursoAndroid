package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.services.ImageDownloaderService;

public class ImageDownloaderActivity extends Activity {
    private EditText editTextUrl;
    private Button descarga;
    private ImageView imagenDescargada;
    boolean mIsReceiverRegistered = false;
    MyBroadcastReceiver mReceiver = null;

    @Override
    protected void onResume() {
        super.onResume();
        if (!mIsReceiverRegistered) {
            if (mReceiver == null)
                mReceiver = new MyBroadcastReceiver();
            registerReceiver(mReceiver, new IntentFilter("ImageDownloaderService.ACTION_FIN"));
            mIsReceiverRegistered = true;
        }



    }

    @Override
    protected void onPause() {
        super.onPause();

        if (mIsReceiverRegistered) {
            unregisterReceiver(mReceiver);
            mReceiver = null;
            mIsReceiverRegistered = false;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_downloader);
        editTextUrl = (EditText) findViewById(R.id.editTextUrl);
        imagenDescargada = (ImageView) findViewById(R.id.imageViewImagenDescargada);
        descarga = (Button) findViewById(R.id.buttonDescarga);
        descarga.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String textIngresado = editTextUrl.getText().toString();
                Intent i = new Intent(getApplicationContext(), ImageDownloaderService.class);
                i.putExtra("descargar", textIngresado);
                startService(i);
            }
        });


        IntentFilter filter = new IntentFilter();
        filter.addAction(ImageDownloaderService.ACTION_FIN);
        MyBroadcastReceiver rcv = new MyBroadcastReceiver();
        registerReceiver(rcv, filter);

    }



    private void updateUI(Intent intent) {

        // Do what you need to do
       String location = intent.getStringExtra("location");
        Picasso.with(getApplicationContext()).load(location).into(imagenDescargada);


    }
    ////My broadcast receiver
    private class MyBroadcastReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent.getAction().equals(ImageDownloaderService.ACTION_FIN)) {
                Toast.makeText(context, "Tarea finalizada!", Toast.LENGTH_SHORT).show();
                updateUI(intent);

            }


        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image_downloader, menu);
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
