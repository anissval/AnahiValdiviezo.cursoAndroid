package com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.valdiviezo.aniss.anahivaldiviezocursoandroid.AdaptadorIntegrantes;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.AdaptadorLocation;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.MyLocation;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import java.util.ArrayList;
import java.util.Locale;

public class LocationActivity extends Activity implements LocationListener {
    private LocationManager locationManager;
    private ListView myLocationList;
    ArrayList<MyLocation> listItems = new ArrayList<MyLocation>();
    AdaptadorLocation adaptador;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        locationManager = (LocationManager)getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 1, this);
        myLocationList = (ListView) findViewById(R.id.listViewLocation);
        adaptador = new AdaptadorLocation(getApplicationContext(), R.layout.location_view,listItems);
        myLocationList.setAdapter(adaptador);

         myLocationList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             @Override
             public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                 //Muestra ubicacion en el mapa
                 MyLocation entry = (MyLocation) parent.getAdapter().getItem(position);
                 Double lat = Double.parseDouble(entry.getLatitude());
                 Double longit = Double.parseDouble(entry.getLongitude());

                 String uri = String.format(Locale.ENGLISH, "geo:%f,%f", lat, longit);
                 Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(uri));
                 startActivity(intent);
             }
         });



    }

    @Override
    protected void onResume() {
        super.onResume();


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_location, menu);
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

    @Override
    public void onLocationChanged(Location location) {
        //AGREGAR AL LIST VIEW
        String latitude = Double.toString(location.getLatitude());
        String longitude = Double.toString(location.getLongitude());

        MyLocation myLocation = new MyLocation();
        myLocation.setLatitude(latitude);
        myLocation.setLongitude(longitude);

        listItems.add(myLocation);
        adaptador.notifyDataSetChanged();

       // String msg = "New Latitude: " + location.getLatitude()
          //      + "New Longitude: " + location.getLongitude();
      //  Toast.makeText(getBaseContext(), msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {
        Toast.makeText(getBaseContext(), "Gps is turned on!! ",
                Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onProviderDisabled(String provider) {
        Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
        startActivity(intent);
        Toast.makeText(getBaseContext(), "Gps is turned off!! ",
                Toast.LENGTH_SHORT).show();
    }
}
