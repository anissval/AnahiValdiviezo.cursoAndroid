package com.valdiviezo.aniss.anahivaldiviezocursoandroid.sqliteExercise;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.Integrantes;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import java.sql.SQLException;

/**
 * Created by Aniss on 22/09/2015.
 */
public class IntegrantesCursorAdapter extends CursorAdapter {

    private IntegrantesDbAdapter dbAdapter = null ;

    public IntegrantesCursorAdapter(Context context, Cursor c) throws SQLException {
        super(context, c);
        //abre la db
        dbAdapter = new IntegrantesDbAdapter(context);
        dbAdapter.abrir();
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor)
    {
            TextView nombre = (TextView) view.findViewById(R.id.nombreIntegrante);
             nombre.setText(cursor.getString(cursor.getColumnIndex(IntegrantesDbAdapter.C_COLUMNA_NOMBRE)));
            TextView ciudad = (TextView)view.findViewById(R.id.ciudadIntegrante);
             ciudad.setText( cursor.getString(cursor.getColumnIndex(IntegrantesDbAdapter.C_COLUMNA_DIRECCION)) );
            ImageView foto_perfil = (ImageView)view.findViewById(R.id.foto_perfil);

              Picasso.with(context)
                      .load(cursor.getString(cursor.getColumnIndex(IntegrantesDbAdapter.C_COLUMNA_FOTO)))
                      .into(foto_perfil);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {

        final LayoutInflater inflater = LayoutInflater.from(context);

        final View view = inflater.inflate(R.layout.integrantes_view, parent, false);

        return view;
    }

}
