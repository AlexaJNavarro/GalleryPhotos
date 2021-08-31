package com.example.galleryphotos;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.FileProvider;

import android.app.ActionBar;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.net.URI;

import javax.xml.transform.Result;

import static android.Manifest.permission.CAMERA;
import static android.Manifest.permission.WRITE_EXTERNAL_STORAGE;

public class MainActivity extends AppCompatActivity {

    ImageView image;
    private final String file = "myImagesTest/";
    private final String route_image = file + "myPictures";
    String path = "";
    Button btnSelect;

    final int code_selected = 10;
    final int code_take = 20;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();

        image = (ImageView) findViewById(R.id.id_image);
        btnSelect = findViewById(R.id.btn_select);

            if(validate_permissions()){
                btnSelect.setEnabled(true);
            }else{
                btnSelect.setEnabled(false);
        }

    }

    private boolean validate_permissions() {
        if(Build.VERSION.SDK_INT < Build.VERSION_CODES.M){
            return true;
        }
        if((checkSelfPermission(CAMERA) == PackageManager.PERMISSION_GRANTED) &&
                (checkSelfPermission(WRITE_EXTERNAL_STORAGE)) == PackageManager.PERMISSION_GRANTED){
            return true;
        }
        if((shouldShowRequestPermissionRationale(CAMERA))||
                (shouldShowRequestPermissionRationale(WRITE_EXTERNAL_STORAGE))){

            loadRecommendationDialog();

        }else{
            requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},100);
        }
        return false;
    }

    private void loadRecommendationDialog() {
        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
        dialog.setTitle("Permisos Desactivados");
        dialog.setMessage("Debe aceptar los permisos para el correcto funcionamiento de la App");

        dialog.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                requestPermissions(new String[]{WRITE_EXTERNAL_STORAGE, CAMERA},100);
            }
        });
        dialog.show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull  String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode == 100){
            if(grantResults.length == 2 && grantResults[0] == PackageManager.PERMISSION_GRANTED
               && grantResults[1] == PackageManager.PERMISSION_GRANTED){
                btnSelect.setEnabled(true);
            }else{
                requestManualPermissions();
            }
        }
    }

    private void requestManualPermissions() {

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
        startActivityForResult(intent.createChooser(intent,"Seleccione la Aplicación"),code_selected);
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

        File image_file = new File(path);
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, FileProvider.getUriForFile(MainActivity.this,BuildConfig.APPLICATION_ID + ".provider" , image_file));
        startActivityForResult(intent, code_take);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == RESULT_OK){
            switch (requestCode){
                case code_selected:
                    Uri upload_path = data.getData();
                    image.setImageURI(upload_path);
                    break;
                case code_take:
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