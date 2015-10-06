package com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import java.net.URL;
import java.sql.BatchUpdateException;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadsActivity extends Activity {

    private TextView threadTime;
    private TextView asynkTasktime;
    private Button buttonStartThread;
    private Button buttonStartAsynkT;
    private Button buttonStopThread;
    private Button buttonStopAsynkT;
    final static int iTime = 0;
    static boolean status=false;
    static Long x=0L;
    static int result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_threads);

        threadTime = (TextView) findViewById(R.id.textViewThreads);
        asynkTasktime = (TextView) findViewById(R.id.textViewAsynT);
        buttonStartAsynkT = (Button) findViewById(R.id.buttonStartAsynk);
        buttonStartThread = (Button) findViewById(R.id.buttonStartT);
        buttonStopAsynkT = (Button) findViewById(R.id.buttonStopAsynk);
        buttonStopThread= (Button) findViewById(R.id.buttonStopT);


        buttonStartThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

              threadTime.setText("");
              status=true;

                new Thread(new Runnable() {

                    public void run() {

                        int delay = 0; // delay for 5 sec.

                        int period = 1000; // repeat every sec.

                       final Timer timer = new Timer();

                        timer.scheduleAtFixedRate(new TimerTask() {

                            public void run() {

                                if(status==true) {
                                    //called every 1000 milliseconds, which could be used to make something
                                    x += 1;
                                    Log.e("Tread" ,"sumando segundos");
                                }
                                else {
                                   timer.cancel();
                                    Log.e("Tread", "cancelando timer "+x);

                                }

                            }


                            }, delay, period);

                        new Thread(new Runnable() {
                            public void run() {

                                runOnUiThread(new Runnable() {
                                    public void run() {
                                        Toast.makeText(getApplicationContext(), "Empieza Tarea!" +x,
                                                Toast.LENGTH_SHORT).show();
                                    x=0L;
                                    }
                                });
                            }
                        }).start();




                    }

                }).start();


            }
        });


        buttonStopThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;

                        Toast.makeText(getApplicationContext(), "Timer detenido a los "+x+" segundos",
                                Toast.LENGTH_SHORT).show();
                threadTime.setText(x.toString());
                x = 0L;
                Log.e("Tread", "Finalizando todo");



            }
        });


        buttonStartAsynkT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                asynkTasktime.setText("");
                MyTimer myTime = new MyTimer();
                myTime.execute();

            }
        });
        buttonStopAsynkT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                status=false;
                Toast.makeText(getApplicationContext(), "Timer detenido a los "+x+" segundos",
                        Toast.LENGTH_SHORT).show();
                asynkTasktime.setText(x.toString());
                x=0L;


            }
        });

    }

    public class MyTimer extends AsyncTask<URL, Integer, Long> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Long doInBackground(URL... params) {
            status = true;

           final int delay = 0; // delay for 5 sec.

            final int period = 1000; // repeat every sec.

            final Timer timer = new Timer();

            timer.scheduleAtFixedRate(new TimerTask() {

                @Override
                public void run() {

                    if(status==true) {
                        //called every 1000 milliseconds, which could be used to make something
                        x += 1;
                        Log.e("AsynkTask" ,"sumando segundos");
                    }
                    else {
                        timer.cancel();
                        Log.e("AsynkTask", "cancelando timer "+x);

                    }
                }

            }, delay, period);




                return null;
        }

        @Override
        protected void onPostExecute(Long aLong) {
            super.onPostExecute(aLong);


        }
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_threads, menu);
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
