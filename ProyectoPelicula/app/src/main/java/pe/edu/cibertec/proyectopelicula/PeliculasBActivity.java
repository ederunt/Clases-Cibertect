package pe.edu.cibertec.proyectopelicula;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PeliculasBActivity extends AppCompatActivity {

    EditText txtFiltro;
    Button btBuscar,btAgregar;
    Retrofit retrofit;

    TextView texttitle, txtAnio, textresumen;
    PeliculaAdapter adapter;

    List<Pelicula> items;

    RecyclerView rvPeliculas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_peliculas_b);

        txtFiltro = findViewById(R.id.txtFiltro);
        btBuscar = findViewById(R.id.btBuscar);

        texttitle = findViewById(R.id.texttitle);
        txtAnio = findViewById(R.id.txtAnio);
        textresumen = findViewById(R.id.textresumen);
        btAgregar = findViewById(R.id.btAgregar);


        texttitle.setVisibility(View.INVISIBLE);
        txtAnio.setVisibility(View.INVISIBLE);
        textresumen.setVisibility(View.INVISIBLE);
        btAgregar.setVisibility(View.INVISIBLE);
        rvPeliculas = findViewById(R.id.rvPeliculas);

        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Toast.makeText(PeliculasBActivity.this,"Entro aqui",Toast.LENGTH_SHORT).show();
                retrofit = new Retrofit.Builder()
                        .baseUrl("https://www.omdbapi.com/")
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();

                ObtenerDatos();
            }
        });

        btAgregar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Pelicula peli = new Pelicula();
                peli.setTitle(texttitle.getText().toString());
                peli.setYear (txtAnio.getText().toString());
                peli.setPlot (textresumen.getText().toString());
                new TaskAddPelicula().execute(peli);
                //new TaskGetPelicula().execute();
            }
        });


    }


    @Override
    protected void onResume() {
        super.onResume();
        new TaskGetPelicula().execute();
    }

    private class TaskAddPelicula extends AsyncTask<Pelicula,Void,Void> {
        @Override
        protected Void doInBackground(Pelicula... peliculas) {
            AppDatabase.nuevaInstancia(PeliculasBActivity.this).getPeliculaDao().insertContacts(peliculas);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            adapter.notifyDataSetChanged();
        }
    }

    private class TaskGetPelicula extends AsyncTask<Void, Void,List<Pelicula>>{
        @Override
        protected List<Pelicula> doInBackground(Void... voids) {
            return AppDatabase.nuevaInstancia(PeliculasBActivity.this).getPeliculaDao().getAll();
        }


        @Override
        protected void onPostExecute(List<Pelicula> contacts) {
            super.onPostExecute(contacts);
            items = contacts;
            adapter = new PeliculaAdapter(items);
            rvPeliculas.setAdapter(adapter);

            rvPeliculas.setLayoutManager(new LinearLayoutManager(PeliculasBActivity.this));
        }
    }

    private void ObtenerDatos() {
        //Toast.makeText(PeliculasBActivity.this,"Entro aqui",Toast.LENGTH_SHORT).show();
        String apikey = "6fc43ba7";
        String filtro = ""+txtFiltro.getText().toString();

        final PeliculaInterface peliculaInterface = retrofit.create(PeliculaInterface.class);

        Call<Pelicula> call = peliculaInterface.searchPelicula(apikey,filtro);

        call.enqueue(new Callback<Pelicula>() {
            @Override
            public void onResponse(Call<Pelicula> call, Response<Pelicula> response) {
                if(response.isSuccessful())
                {
                    Pelicula pe = response.body();
                    texttitle.setText(pe.getTitle());
                    txtAnio.setText(pe.getYear());
                    textresumen.setText(pe.getPlot());

                    texttitle.setVisibility(View.VISIBLE);
                    txtAnio.setVisibility(View.VISIBLE);
                    textresumen.setVisibility(View.VISIBLE);
                    btAgregar.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<Pelicula> call, Throwable t) {

            }
        });

    }
}
