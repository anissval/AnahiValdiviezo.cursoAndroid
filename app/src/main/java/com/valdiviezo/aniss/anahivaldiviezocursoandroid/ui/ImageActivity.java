package com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.BitMapTransform;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class ImageActivity extends Activity {

    private ImageView imagen;
    private TextView texto;
    private final int SELECT_PHOTO = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);

        imagen = (ImageView) findViewById(R.id.imageView_imagen);
        texto = (TextView) findViewById(R.id.textView_texto);
        imagen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent pickImage = new Intent(Intent.ACTION_PICK);
                pickImage.setType("image/*");
                startActivityForResult(pickImage,SELECT_PHOTO);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case SELECT_PHOTO:
                if(resultCode==RESULT_OK){

                    final Uri imageUri = data.getData();

                    Picasso.with(getApplicationContext())
                            .load(imageUri)
                            //.fit()
                            .resize(800,800)
                            //.onlyScaleDown()
                            //.centerCrop()
                           .centerInside()
                            .into(imagen);

                    texto.setText(data.getDataString());


                }

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_image, menu);
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
