package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.sql.SQLException;

/**
 * Created by Aniss on 22/09/2015.
 */
public class IntegrantesDbAdapter {


    /**
     * Definimos constante con el nombre de la tabla
     */
    public static final String C_TABLA = "INTEGRANTES" ;

    /**
     * Definimos constantes con el nombre de las columnas de la tabla
     */
    public static final String C_COLUMNA_ID   = "_id";
    public static final String C_COLUMNA_NOMBRE = "int_nombre";
    public static final String C_COLUMNA_APELLIDO = "int_apellido";
    public static final String C_COLUMNA_DIRECCION = "int_direccion";
    public static final String C_COLUMNA_FOTO = "int_foto";


    private Context contexto;
    private IntegrantesDbHelper dbHelper;
    private SQLiteDatabase db;

    /**
     * Definimos lista de columnas de la tabla para utilizarla en las consultas a la base de datos
     */
    private String[] columnas = new String[]{ C_COLUMNA_ID, C_COLUMNA_NOMBRE, C_COLUMNA_APELLIDO, C_COLUMNA_DIRECCION, C_COLUMNA_FOTO} ;

    public IntegrantesDbAdapter(Context context)
    {
        this.contexto = context;
    }

    public IntegrantesDbAdapter abrir() throws SQLException
    {
        dbHelper = new IntegrantesDbHelper(contexto);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void cerrar()
    {
        dbHelper.close();
    }

    /**
     * Devuelve cursor con todos las columnas de la tabla
     */
    public Cursor getCursor() throws SQLException
    {
        Cursor c = db.query( true, C_TABLA, columnas, null, null, null, null, null, null);

        return c;
    }



}
