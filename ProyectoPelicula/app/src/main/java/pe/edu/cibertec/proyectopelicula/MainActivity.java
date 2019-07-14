package pe.edu.cibertec.proyectopelicula;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    Button btBuscar,btAgregar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        btBuscar = findViewById(R.id.btBuscar);
        btAgregar = findViewById(R.id.btAgregar);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentb = new Intent(MainActivity.this,PeliculasBActivity.class);
                startActivity(intentb);
            }
        });

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intenta = new Intent(MainActivity.this,PeliculasFActivity.class);
                startActivity(intenta);
            }
        });


    }
}
