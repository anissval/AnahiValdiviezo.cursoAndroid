package com.valdiviezo.aniss.anahivaldiviezocursoandroid.sqliteExercise;

import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.ref.PhantomReference;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;

public class IntegrantesListActivity extends ListActivity{



    private IntegrantesDbAdapter dbAdapter;
    private Cursor cursor;
    private IntegrantesCursorAdapter integrantesAdapter ;
    private ListView lista;
    SQLiteDatabase db;
    private ProgressBar progressBar;
    MyIntegrantesAsynk2 myA2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes_list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
         //saque ejecucion asynktask

   /*
    * Declaramos el controlador de la BBDD y accedemos en modo escritura
    */
        IntegrantesDbHelper dbHelper = new IntegrantesDbHelper(getBaseContext());

         db = dbHelper.getWritableDatabase();

        lista = (ListView) findViewById(android.R.id.list);

        dbAdapter = new IntegrantesDbAdapter(this);
        dbAdapter.abrir();

        try {
            consultar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void consultar() throws SQLException {

        try {
            cursor = dbAdapter.getCursor();
            startManagingCursor(cursor);
            integrantesAdapter = new IntegrantesCursorAdapter(getApplicationContext(), cursor);
            lista.setAdapter(integrantesAdapter);
            if (cursor.getCount() == 0 || !cursor.moveToFirst()) {
                Log.e("Datos", "Lista vacia ... llenando desde webService");
                getDatafromWebService();
            }
            else {
                Log.e("Datos", "recuperando datos desde DB");
                wasUpdatedDB();
           }

        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }

    }

    public void getDatafromWebService(){
        myA2 = new MyIntegrantesAsynk2();
        myA2.execute();
    }

    public class MyIntegrantesAsynk2 extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {
            Log.e("TAG", "Executing Asynktask");
            String readStreamS = null;
            try {
                URL url = new URL("http://abelmartin.com.ar/mobilelab/people.php");
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                readStreamS = readStream(con.getInputStream());
                // Give output for the command line

                //  Log.e("TAG", readStreamS);

                if (readStreamS != null) {
                    try {
                        JSONObject jsonObj = new JSONObject(readStreamS);

                        for (int i = 0; i < 5; i++) {
                            Integrantes integrante= new Integrantes();

                            JSONObject c = jsonObj.getJSONObject(String.valueOf(i+1));

                            String id = c.getString("id");
                            String name = c.getString("name");
                            String lastName = c.getString("lastname");
                            String address = c.getString("address");
                            String profileImage = c.getString("profile_image");

                            db.execSQL("INSERT INTO INTEGRANTES(_id, int_nombre,int_apellido,int_direccion,int_foto) VALUES('" + id + "','" + name + "','" + lastName+"','"+address+"','"+profileImage+"');");

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
           return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);
            //This line shows progressBar again for recycled view
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            Log.e("TAG", "onPostExecute");
            wasUpdatedDB();
            progressBar.setVisibility(View.INVISIBLE);

        }
    }

        public void wasUpdatedDB(){
            integrantesAdapter.swapCursor(dbAdapter.getCursor());
            integrantesAdapter.notifyDataSetChanged();
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
    protected void onDestroy() {
        super.onDestroy();
        dbAdapter.cerrar();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_integrantes_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            // action with ID action_refresh was selected
            case R.id.action_refresh:
                Log.e("TAG", "updating data");
               // Toast.makeText(this, "Refresh selected", Toast.LENGTH_SHORT)
                   //     .show();
                dbAdapter.borrar();
                wasUpdatedDB();
                getDatafromWebService();
                break;
            // action with ID action_settings was selected
            case R.id.action_settings:
                Toast.makeText(this, "Settings selected", Toast.LENGTH_SHORT)
                        .show();
                break;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
