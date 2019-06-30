package pe.edu.cibertec.dependencyinyection;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        /* Base


        AppPoolWacher appPoolWacher = new AppPoolWacher();



        appPoolWacher.notify("Ocurrio el Evento");

        */

        //Inyeccion de constructor

        /*
        EmailSender action =new EmailSender();
        AppPoolWacher appPoolWacher = new AppPoolWacher(action);
        appPoolWacher.notify("Ocurrio el evento");
        */

        //Inyeccion de metodo
//        SmsSender smsAction = new SmsSender();
//        AppPoolWacher appPoolWacher = new AppPoolWacher(smsAction);
//        appPoolWacher.notify(action,"Ocurrio el evento");


        //Inyeccion de propiedad
        EventLogWriter action =  new EventLogWriter();
        AppPoolWacher appPoolWacher = new AppPoolWacher();

        appPoolWacher.setAction(action);
        appPoolWacher.getAction().write("Ocurrio el evento");


    }
}
