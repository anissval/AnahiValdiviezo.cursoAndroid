package com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.Alumno;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import java.util.ArrayList;

public class ListViewActivity extends Activity {

    private ListView listViewSimple;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_view);

        listViewSimple = (ListView) findViewById(R.id.listViewSimple);


        String[] alumnos = new String[] {
                "Anahi Valdiviezo"
                ,"German Moyano"
                ,"Maximiliano Ambrosini"
                ,"Marcelo Fernandez"
                ,"Emiliano Hualpa"
                ,"Ever Valdes"
        };
        listViewSimple.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, alumnos));




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_list_view, menu);
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




    public ArrayList<Alumno> addAlumnos(){

        Alumno a = new Alumno("Anahi","Valdiviezo");
        Alumno b = new Alumno("German","Moyano");
        Alumno c = new Alumno("Maximiliano","Ambrosini");
        Alumno d = new Alumno("Marcelo","Fernandez");
        Alumno e = new Alumno("Emiliano","Hualpa");
        Alumno f = new Alumno("Ever","Valdes");
        Alumno g = new Alumno("Rodrigo","Moreno");
        Alumno h = new Alumno("Gabriel","Guzman");

        ArrayList<Alumno> alumnos = new ArrayList<Alumno>();
        alumnos.add(a);
        alumnos.add(b);
        alumnos.add(c);
        alumnos.add(d);
        alumnos.add(e);
        alumnos.add(f);
        alumnos.add(g);
        alumnos.add(h);

        return alumnos;
    }
}
