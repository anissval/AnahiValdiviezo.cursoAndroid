package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import com.squareup.picasso.Callback;

/**
 * Created by Aniss on 18/09/2015.
 */
public class AdaptadorIntegrantes extends ArrayAdapter<Integrantes>{

    ArrayList<Integrantes> integLista ;
    public AdaptadorIntegrantes(Context context, int textViewResourceId, ArrayList<Integrantes> integList) {
        super(context, textViewResourceId,integList);
        this.integLista =integList;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        View item = convertView;
        ViewHolder holder;
            if (item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.integrantes_view, null);
                holder = new ViewHolder();
                holder.nombre = (TextView) item.findViewById(R.id.nombreIntegrante);
                holder.ciudad = (TextView)item.findViewById(R.id.ciudadIntegrante);
                holder.foto_perfil = (ImageView) item.findViewById(R.id.foto_perfil);
                item.setTag(holder);

            }
            else {
                holder = (ViewHolder)item.getTag();
            }

            Integrantes persona = getItem(position);
        Log.e("TAG ", String.valueOf(persona));
             holder.nombre.setText(persona.getNombre());
             holder.ciudad.setText(persona.getDireccion());

 Picasso.with(getContext()).load(persona.getFoto()).into(holder.foto_perfil);



        return(item);
    }

    static class ViewHolder{
        TextView nombre;
        TextView ciudad;
        ImageView foto_perfil;

    }

}

