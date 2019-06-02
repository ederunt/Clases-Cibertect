package pe.edu.cibertec.recordaudio;

import android.Manifest;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.media.MediaRecorder;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
Button btRecord,btPlay;
static final int REQUEST_RECORD_AUDIO = 1;
MediaRecorder recorder=null;
MediaPlayer player = null;
static final String LOG_TAG = "AudioRecorder";
String fileName = null;
boolean recording = false;
boolean playing = false;
boolean permissionGranted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btRecord = findViewById(R.id.btRecord);
        btPlay = findViewById(R.id.btPlay);
       // btPlay.setEnabled(false);


        fileName = getExternalCacheDir().getAbsolutePath();
        fileName = fileName + "/audiorecorder.3gp";

        checkPermissionRecord();

        btRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!recording)
                {
                    startRecording();
                    btRecord.setText("Detener");
                }
                else
                {
                    stopRecording();
                    btRecord.setText("Grabar");
                }
                recording = !recording;

            }
        });

        btPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!playing)
                {
                    startPlaying();
                    btPlay.setText("Detener");
                }
                else
                {
                    stopPlaying();
                    btPlay.setText("Play");
                }
                playing = !playing;

            }
        });
    }

    private void stopPlaying() {
        player.start();
        player.release();
        player= null;

    }

    private void stopRecording() {
        recorder.stop();
        recorder.release();
        recorder = null;
    }

    private void startPlaying() {
        player = new MediaPlayer();
        player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                stopPlaying();
                btPlay.setText("Play");
                playing = !false;
            }
        });
        try {
            player.setDataSource(fileName);
            player.prepare();
            player.start();
        } catch (IOException e) {
            //e.printStackTrace();
            Log.e(LOG_TAG,e.toString());
        }
        btRecord.setText("Grabando");
    }

    private void checkPermissionRecord() {
       ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.RECORD_AUDIO},REQUEST_RECORD_AUDIO);

//        ActivityCompat.requestPermissions(this, Manifest.permission.RECORD_AUDIO,REQUEST_RECORD_AUDIO);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if(requestCode==REQUEST_RECORD_AUDIO)
        {
            permissionGranted = grantResults.length>0 && grantResults[0] == PackageManager.PERMISSION_GRANTED;
            //vaidamos si hay permisos
//            if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
//            {
//                startRecording();
//            }
        }
        if(!permissionGranted)
        {
            finish();
        }

    }

    //Iniciar a grabar
    private void startRecording() {
        //Genera una instancia para grabar
        recorder = new MediaRecorder();
        //Indica la fuente del medio es el microfono
        recorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //Indica el formato de la salida
        recorder.setOutputFormat(MediaRecorder.OutputFormat.THREE_GPP);
        //Indica el archivo donde se grabo el audio
        recorder.setOutputFile(fileName);
        //Encodear/COMPRIMIR EL AUDIO
        recorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);

        try {
            recorder.prepare();
        } catch (IOException e) {
            Log.e(LOG_TAG,e.toString());
            //e.printStackTrace();
        }
        //Inicia la grabacion
        recorder.start();


    }

    @Override
    protected void onStop() {
        super.onStop();
        if(recorder!=null)
        {
            recorder.release();
            recorder = null;
        }
        if(player!=null)
        {
            player.release();
            player = null;
        }
    }
}
