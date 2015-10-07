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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes_list);
        progressBar = (ProgressBar) findViewById(R.id.progressBar2);
        MyIntegrantesAsynk myA = new MyIntegrantesAsynk();
        myA.execute();

   /*
    * Declaramos el controlador de la BBDD y accedemos en modo escritura
    */
        IntegrantesDbHelper dbHelper = new IntegrantesDbHelper(getBaseContext());

       db = dbHelper.getWritableDatabase();

        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();

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
        //trae consulta
        cursor = dbAdapter.getCursor();
        startManagingCursor(cursor);
        integrantesAdapter = new IntegrantesCursorAdapter(this, cursor);
        lista.setAdapter(integrantesAdapter);
    }


    public class MyIntegrantesAsynk extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

        }

        @Override
        protected String doInBackground(String... params) {

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

                          //  db.execSQL("INSERT INTO INTEGRANTES(_id, int_nombre,int_apellido,int_direccion,int_foto) VALUES('"+id+"','"+name+"','"+lastName+"','"+address+"','"+profileImage+"')");
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
            progressBar.setVisibility(View.INVISIBLE);

        }
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
        getMenuInflater().inflate(R.menu.menu_integrantes_list, menu);
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
