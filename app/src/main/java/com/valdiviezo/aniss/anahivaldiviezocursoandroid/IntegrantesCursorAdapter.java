package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

import java.sql.SQLException;

/**
 * Created by Aniss on 22/09/2015.
 */
public class IntegrantesCursorAdapter extends CursorAdapter {

    private IntegrantesDbAdapter dbAdapter = null ;

    public IntegrantesCursorAdapter(Context context, Cursor c) throws SQLException {
        super(context, c);
        dbAdapter = new IntegrantesDbAdapter(context);
        dbAdapter.abrir();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
        TextView tv = (TextView) view ;

        tv.setText(cursor.getString(cursor.getColumnIndex(IntegrantesDbAdapter.C_COLUMNA_NOMBRE)));
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent)
    {
        final LayoutInflater inflater = LayoutInflater.from(context);
        final View view = inflater.inflate(android.R.layout.simple_dropdown_item_1line, parent, false);

        return view;
    }

}
