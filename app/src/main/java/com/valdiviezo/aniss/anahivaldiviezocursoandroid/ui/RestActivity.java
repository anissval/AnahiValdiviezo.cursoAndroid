package com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.AdaptadorIntegrantes;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.Integrantes;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class RestActivity extends Activity {

    private ListView listaIntegrantes;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rest);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        listaIntegrantes = (ListView) findViewById(R.id.listViewIntegrantes);
       // if (isNetworkAvailable()){
        MyIntegrantesAsynk myA = new MyIntegrantesAsynk();
        myA.execute();
       // }

    }


    public class MyIntegrantesAsynk extends AsyncTask<String, String, ArrayList<Integrantes>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected ArrayList<Integrantes> doInBackground(String... params) {

            ArrayList<Integrantes> integList = new ArrayList<Integrantes>();
            String readStreamS = null;
            try {
                URL url = new URL("http://abelmartin.com.ar/mobilelab/people.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                readStreamS = readStream(con.getInputStream());
                // Give output for the command line

                Log.e("TAG", readStreamS);

                if (readStreamS != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(readStreamS);

                        for (int i = 0; i < 10; i++) {
                            Integrantes integrante= new Integrantes();

                            JSONObject c = jsonObj.getJSONObject(String.valueOf(i+1));

                            String id = c.getString("id");
                            String name = c.getString("name");
                            String lastName = c.getString("lastname");
                            String address = c.getString("address");
                            String profileImage = c.getString("profile_image");

                            integrante.setId(Integer.parseInt(id));
                            integrante.setNombre(name);
                            integrante.setApellido(lastName);
                            integrante.setDireccion(address);
                            integrante.setFoto(profileImage);

                            Log.e("Integrante : ", integrante.getNombre());
                            integList.add(integrante);
                            int e=integList.size();
                            Log.e("Tamaño arraylist :", String.valueOf(e));
                            Log.d("@", name + ""  +lastName );
                        }
                    } catch (JSONException e) {
                        //return null;
                    }
                } else {
                    Log.e("ServiceHandler", "Couldn't get any data from the url");
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            //null de la asyntask
            return integList;

        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //This line shows progressBar again for recycled view
            progressBar.setVisibility(View.VISIBLE);

        }

        @Override
        protected void onPostExecute(ArrayList<Integrantes> integList) {
            super.onPostExecute(integList);

            AdaptadorIntegrantes adaptador = new AdaptadorIntegrantes(getApplicationContext(), R.layout.integrantes_view,integList);
            listaIntegrantes.setAdapter(adaptador);
            progressBar.setVisibility(View.INVISIBLE);

        }
    }


       ///METODO PARA CHEQUEAR CONECTIVIDAD
    public boolean isNetworkAvailable() {
        ConnectivityManager cm = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        // if no network is available networkInfo will be null
        // otherwise check if we are connected
        if (networkInfo != null && networkInfo.isConnected()) {
            return true;
        }
        return false;
    }




    //metodo particular
    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        try  {
            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();


    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_rest, menu);
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
