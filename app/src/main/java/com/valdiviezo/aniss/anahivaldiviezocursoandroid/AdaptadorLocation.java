package com.valdiviezo.aniss.anahivaldiviezocursoandroid;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Aniss on 06/10/2015.
 */
public class AdaptadorLocation extends ArrayAdapter<MyLocation>{

        ArrayList<MyLocation> locationList;

        public AdaptadorLocation(Context context, int textViewResourceId, ArrayList<MyLocation> locationList) {
            super(context, textViewResourceId,locationList);
            this.locationList =locationList;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            View item = convertView;
            ViewHolder holder;
            if (item == null) {
                LayoutInflater inflater = LayoutInflater.from(getContext());
                item = inflater.inflate(R.layout.location_view, null);
                holder = new ViewHolder();
                holder.latitude = (TextView) item.findViewById(R.id.textViewLatitude);
                holder.latitudeText = (TextView) item.findViewById(R.id.textViewLatitudeText);

                holder.longitude = (TextView)item.findViewById(R.id.textViewLongitude);
                holder.longitudeText = (TextView)item.findViewById(R.id.textViewLongitudeText);
                item.setTag(holder);

            }
            else {
                holder = (ViewHolder)item.getTag();
            }

            MyLocation myLoc = getItem(position);
            Log.e("TAG ", String.valueOf(myLoc));
            holder.latitudeText.setText("LATITUDE : ");
            holder.latitude.setText(myLoc.getLatitude());
            holder.longitudeText.setText("LONGITUDE : ");
            holder.longitude.setText(myLoc.getLongitude());


            return(item);
        }

        static class ViewHolder{
            TextView latitudeText;
            TextView latitude;
            TextView longitudeText;
            TextView longitude;

        }



}
