package com.valdiviezo.aniss.anahivaldiviezocursoandroid.sqliteExercise;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.Integrantes;

/**
 * Created by Aniss on 22/09/2015.
 */
public class IntegrantesDbHelper extends SQLiteOpenHelper {

    private static int version = 1;
    private static String name = "IntegrantesDb" ;
    private static SQLiteDatabase.CursorFactory factory = null;

    public IntegrantesDbHelper(Context context)
    {
        super(context,name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        /*
    * Declaramos el controlador de la BBDD y accedemos en modo escritura
    */

        Log.i(this.getClass().toString(), "Creando base de datos");

        db.execSQL("CREATE TABLE INTEGRANTES(" +
                " _id INTEGER PRIMARY KEY," +
                " int_nombre TEXT NOT NULL, " +
                " int_apellido TEXT, " +
                " int_direccion TEXT," +
                " int_foto TEXT)");

        db.execSQL( "CREATE UNIQUE INDEX int_nombre ON INTEGRANTES(int_nombre ASC)" );

          Log.i(this.getClass().toString(), "Tabla INTEGRANTES creada");

        Log.i(this.getClass().toString(), "Base de datos creada");


    }


    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {





    }



}
