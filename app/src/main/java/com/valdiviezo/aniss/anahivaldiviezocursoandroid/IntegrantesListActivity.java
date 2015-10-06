package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.app.Activity;
import android.app.ListActivity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.SQLException;

public class IntegrantesListActivity extends ListActivity{



    private IntegrantesDbAdapter dbAdapter;
    private Cursor cursor;
    private IntegrantesCursorAdapter integrantesAdapter ;
    private ListView lista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_integrantes_list);

   /*
    * Declaramos el controlador de la BBDD y accedemos en modo escritura
    */
        IntegrantesDbHelper dbHelper = new IntegrantesDbHelper(getBaseContext());

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        Toast.makeText(getBaseContext(), "Base de datos preparada", Toast.LENGTH_LONG).show();



        lista = (ListView) findViewById(android.R.id.list);

        dbAdapter = new IntegrantesDbAdapter(this);
        try {
            dbAdapter.abrir();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try {
            consultar();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void consultar() throws SQLException {
        cursor = dbAdapter.getCursor();
        startManagingCursor(cursor);
        integrantesAdapter = new IntegrantesCursorAdapter(this, cursor);
        lista.setAdapter(integrantesAdapter);
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
