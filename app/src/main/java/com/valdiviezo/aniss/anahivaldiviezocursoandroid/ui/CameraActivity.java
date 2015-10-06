package com.valdiviezo.aniss.anahivaldiviezocursoandroid.ui;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.valdiviezo.aniss.anahivaldiviezocursoandroid.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CameraActivity extends Activity {

    private static final int TAKE_PICTURE = 11;
    ImageView cameraImage;
    Button compartirButton;
    private Uri picUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_camera);
        cameraImage = (ImageView) findViewById(R.id.imageView_foto);
        cameraImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                picture();

            }
        });

        compartirButton = (Button) findViewById(R.id.buttonCompartir);
        compartirButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

             shareImage();
            }
        });


    }


    public void picture()
    {
        Intent cameraIntent = new Intent("android.media.action.IMAGE_CAPTURE");
        File photo = new File(Environment.getExternalStorageDirectory(), "pic.jpg");
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(photo));
        picUri = Uri.fromFile(photo);

        Log.e("FOOOTOO", String.valueOf(picUri));
        startActivityForResult(cameraIntent, TAKE_PICTURE);
    }

    private void shareImage() {

        Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        emailIntent.setType("application/image");
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{"anissval@gmail.com"});
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT,"Test Subject");
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, "From My App");
        emailIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(picUri.toString()));
        startActivity(Intent.createChooser(emailIntent, "Send mail..."));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case TAKE_PICTURE:
                if (resultCode == Activity.RESULT_OK) {
                    Uri mypic = picUri;
                    //Do something with the image.
                    Picasso.with(getApplicationContext())
                            .load(mypic)
                            .resize(2048,2048)
                            .onlyScaleDown()
                            .centerInside()
                            .into(cameraImage);


                    compartirButton.setVisibility(View.VISIBLE);
                }

        }

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_camera, menu);
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
