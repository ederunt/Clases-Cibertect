package pe.edu.notification;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.arch.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register();
        createNotificationchannel();

    }

    private void createNotificationchannel() {

        if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){

            String name = "pe.edu.cibertec.notification.CHANNEL";
            String description = "Descripcion";

            int importance = NotificationManager.IMPORTANCE_DEFAULT;

            NotificationChannel channel = new NotificationChannel("Canal",name,importance);

            channel.setDescription(description);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);


        }

    }

    private void register()
    {
        MyBroadCastReceiver receiver = new MyBroadCastReceiver();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("pe.edu.cibertec.broadcast.ACTION");
        intentFilter.addAction("android.intent.action.AIRPLANE_MODE");

        this.registerReceiver(receiver,intentFilter);
    }
}
