package pe.edu.cibertec.camare;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    Button btCamera;
    ImageView ibFoto;  //ivPhoto

    static final int REQUEST_CAMERA = 1;
    static final int REQUEST_TAKE_PICTURE = 2;
    static final int REQUEST_TAKE_CONTACT = 3;

    String currentPathImage;  //Ruta Absoluta de la imagen

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btCamera = findViewById(R.id.btCamera);
        ibFoto = findViewById(R.id.ibFoto);

        btCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Toast.makeText(MainActivity.this,"Se dio permiso",Toast.LENGTH_SHORT).show();
                try {
                    takePicture();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void takePicture() throws IOException {
        Intent cameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);


        //Validar que la camara este disponible
        if (cameraIntent.resolveActivity(getPackageManager()) != null) {
            //Verificar que se dispongan de permisos
            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                requestCameraPermission();
               // requestContactPermission();
            } else {

                File photfile = null;
                photfile = createImage();

                startActivityForResult(cameraIntent, REQUEST_TAKE_PICTURE);
            }

//            if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
//                requestCameraPermission();
//                // requestContactPermission();
//            } else {
//
//                File photfile = null;
//                photfile = createImage();
//
//
//                startActivityForResult(cameraIntent, REQUEST_TAKE_CONTACT);
//            }

        }


    }

    private File createImage() throws IOException {



        // Asignar un nombre
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG"+timeStamp+"_";

        //Asignarle un directorio de almacenamiento

        File storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        //Crear el Archivo
        File image = File.createTempFile(imageFileName, //nombre
                        "JPEG",
                        storageDir
        );

        //Grabar la Imagen

        currentPathImage = image.getAbsolutePath();

        return image;

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {   //ctrl+o
        //super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CAMERA) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                try {
                    takePicture();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                //Toast.makeText(MainActivity.this, "Se dio permiso", Toast.LENGTH_SHORT).show();
                //finish();
            }
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent information) {
        if(requestCode==REQUEST_TAKE_PICTURE && resultCode==RESULT_OK)
        {
            Bitmap bitmap = (Bitmap) information.getExtras().get("data");
            ibFoto.setImageBitmap(bitmap);
        }
    }

    private void requestCameraPermission() {

        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.CAMERA/*,Manifest.permission.READ_CONTACTS*/}, REQUEST_CAMERA);
    }

//    private void requestContactPermission() {
//
//        ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.READ_CONTACTS}, REQUEST_TAKE_CONTACT);
//    }
}
