package com.example.galleryphotos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

import javax.xml.transform.Result;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    private final String file = "myImagesTest/";
    private final String route_image = file + "my pictures";
    String path = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        image = (ImageView) findViewById(R.id.id_image);

    }

    public void BtnSelected(View view) {
        final CharSequence[] options = {"Tomar Foto", "Cargar Imagen", "Cancelar"};
        final AlertDialog.Builder alertOptions = new AlertDialog.Builder(MainActivity.this);
        alertOptions.setTitle("Seleccionar una Opción");
        alertOptions.setItems(options, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                if (options[i].equals("Tomar Foto")){

                    takePhoto();

                }else if(options[i].equals("Cargar Imagen")){

                    uploadImage();

                }else if(options[i].equals("Cancelar")){

                    dialogInterface.dismiss();

                }
            }
        });
        alertOptions.show();
    }

    public void uploadImage(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),10);
    }

    public void takePhoto(){
        File file_image = new File(Environment.getExternalStorageDirectory(),route_image);
        boolean isCreated = file_image.exists();
        String name_image = "";

            if(isCreated == false){
                //Toast.makeText(MainActivity.this, "FALSE", Toast.LENGTH_SHORT).show();
                isCreated = file_image.mkdirs();
            }else{
                //Toast.makeText(MainActivity.this, "TRUE", Toast.LENGTH_SHORT).show();
                name_image = (System.currentTimeMillis()/1000) + ".jpg";
            }

        path = Environment.getExternalStorageDirectory()+
                File.separator + route_image + File.separator + name_image;

        File image = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(image));

        startActivityForResult(intent, 20);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case 10:
                    Uri upload_path = data.getData();
                    image.setImageURI(upload_path);
                    break;
                case 20:
                    MediaScannerConnection.scanFile(this, new String[]{path}, null,
                            new MediaScannerConnection.OnScanCompletedListener() {
                            @Override
                            public void onScanCompleted(String s, Uri uri) {
                                Log.i("Ruta de almacenamiento","Path: " + path);
                            }
                    });
                    Bitmap bitmap = BitmapFactory.decodeFile(path);
                    image.setImageBitmap(bitmap);
                    break;
            }

        }
    }
}