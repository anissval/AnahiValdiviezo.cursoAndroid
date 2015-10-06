package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.services.ImageDownloaderService;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Aniss on 12/09/2015.
 */
public class MyBroadcastReceiver extends BroadcastReceiver {

    private DateFormat dateFormat;
    private String date;

    //When Event is published, onReceive method is called
    @Override
    public void onReceive(Context context, Intent intent) {
        // TODO Auto-generated method stub
        Log.i("[BroadcastReceiver]", "MyReceiver");

        if(intent.getAction().equals(Intent.ACTION_SCREEN_ON)){
            Log.i("[BroadcastReceiver]", "Screen ON");
            dateFormat = new SimpleDateFormat("HH:mm:ss");
            date = dateFormat.format(new Date());
            Toast.makeText(context, "Hora de encendido : " + date, Toast.LENGTH_SHORT).show();


        }
        else if(intent.getAction().equals(Intent.ACTION_SCREEN_OFF)){
            Log.i("[BroadcastReceiver]", "Screen OFF");

        }
        //http://www.sgoliver.net/blog/tareas-en-segundo-plano-en-android-ii-intentservice/
       // else if(intent.getAction().equals(ImageDownloaderService.ACTION_PROGRESO)) {
        //    int prog = intent.getIntExtra("progreso", 0);
         //   pbarProgreso.setProgress(prog);
       // }

    }


}
