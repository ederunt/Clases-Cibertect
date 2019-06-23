package pe.edu.cibertec.animate;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {


    Button btZoom,btClockWide,btFade,btMove;
    ImageView ivImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ivImage = findViewById(R.id.ivImagen);
        btZoom = findViewById(R.id.btZoom);
        btClockWide = findViewById(R.id.btClockWide);
        btFade = findViewById(R.id.btFade);
        btMove = findViewById(R.id.btMove);


        btZoom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.zoom);
                ivImage.startAnimation(animation);

            }
        });


        btClockWide.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.clockwide);
                ivImage.startAnimation(animation);
            }
        });

        btFade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.fade);
                ivImage.startAnimation(animation);

            }
        });

        btMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation animation = AnimationUtils.loadAnimation(MainActivity.this,R.anim.move);
                ivImage.startAnimation(animation);
            }
        });



    }
}
